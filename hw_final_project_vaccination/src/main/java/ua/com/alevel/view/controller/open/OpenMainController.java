package ua.com.alevel.view.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.RecordTime;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.persistence.util.Vaccine;
import ua.com.alevel.util.ConvertString;
import ua.com.alevel.view.dto.request.RecordNewRequestDto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/open")
public class OpenMainController {

    private final DoctorRepository doctorRepository;
    private final VaccinationPointRepository vaccinationPointRepository;
    private final RecordRepository recordRepository;
    private final RecordFacade recordFacade;
    private long vaccinationPointId;

    public OpenMainController(DoctorRepository doctorRepository, VaccinationPointRepository vaccinationPointRepository, RecordRepository recordRepository, RecordFacade recordFacade) {
        this.doctorRepository = doctorRepository;
        this.vaccinationPointRepository = vaccinationPointRepository;
        this.recordRepository = recordRepository;
        this.recordFacade = recordFacade;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("vaccinationPoints", vaccinationPointRepository.findAll());
        model.addAttribute("record", new RecordNewRequestDto());
        return "pages/open/choose_vaccination_point";
    }

    @GetMapping("/second")
    public String secondPage(
            Model model,
            @ModelAttribute("record") RecordNewRequestDto dto) {
        model.addAttribute("record", dto);
        if (dto.getVaccinationPoint() != null) {
            vaccinationPointId = dto.getVaccinationPoint().getId();
        }
        VaccinationPoint vaccinationPoint = vaccinationPointRepository.getById(vaccinationPointId);
        model.addAttribute("doctors", doctorRepository.findAllByVaccinationPoint(vaccinationPoint));
        model.addAttribute("vaccines", Vaccine.values());
        return "pages/open/start_registration";
    }

    @GetMapping("/third")
    public String thirdPage(Model model, @ModelAttribute("record") RecordNewRequestDto dto) {
        model.addAttribute("record", dto);
        List<RecordTime> freeRecordTime = new ArrayList<>();
        try {
            freeRecordTime = recordRepository.findAllRecordTimesByDoctorAndVaccineDate(dto.getDoctor(), ConvertString.convertStringToDate(dto.getVaccineDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("freeRecordTime", freeRecordTime);
        return "pages/open/choose_time";
    }

    @GetMapping("/success")
    public String endRegistration(Model model, @ModelAttribute("record") RecordNewRequestDto dto) {
        List<Record> records = recordRepository.findAllRecordsBeforeInsert();
        Record record = null;
        if (records.size() != 0) {
            record = records.get(0);
        }
        if(record != null && record.getPhone().equals(dto.getPhone())
                && record.getSurname().equals(dto.getSurname())
                && record.getName().equals(dto.getName())
                && record.getPatronymic().equals(dto.getPatronymic())) {
            return "redirect:/open";
        }
        recordFacade.create(dto);
        Pattern pattern = Pattern.compile("-");
        String[] dateArray = pattern.split(dto.getVaccineDate());
        String dateString = dateArray[2] + "/" + dateArray[1] + "/" + dateArray[0];
        dto.setVaccineDate(dateString);
        model.addAttribute("record", dto);
        return "pages/open/end_registration";
    }
}