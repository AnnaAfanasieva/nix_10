package ua.com.alevel.view.controller.admin;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.RecordTime;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.persistence.util.Vaccine;
import ua.com.alevel.util.ConvertString;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/records")
public class AdminRecordsController extends BaseController {

    private long update_id;
    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("№", null, null),
            new HeaderName("Прізвище", "surname", "surname"),
            new HeaderName("Ім'я", "name", "name"),
            new HeaderName("По батькові", "patronymic", "patronymic"),
            new HeaderName("Вакцина", "vaccine", "vaccine"),
            new HeaderName("Прізвище лікаря", "doctor_id", "doctor"),
            new HeaderName("Дата","vaccine_date","vaccineDate"),
            new HeaderName("Час","record_time_id","recordTime"),
            new HeaderName("Деталі", null, null),
            new HeaderName("Видалити", null, null)
    };

    private final RecordFacade recordFacade;
    private final DoctorRepository doctorRepository;
    private final VaccinationPointRepository vaccinationPointRepository;
    private final RecordRepository recordRepository;

    public AdminRecordsController(RecordFacade recordFacade, DoctorRepository doctorRepository, VaccinationPointRepository vaccinationPointRepository, RecordRepository recordRepository) {
        this.recordFacade = recordFacade;
        this.doctorRepository = doctorRepository;
        this.vaccinationPointRepository = vaccinationPointRepository;
        this.recordRepository = recordRepository;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<RecordResponseDto> response = recordFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/records/all");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "Усі записи");
        return "pages/admin/records/records_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/records");
    }

    @GetMapping("/point/{id}")
    public String findAllByVaccinationPoint(@PathVariable @NotNull() Long id, Model model, WebRequest request) {
        VaccinationPoint vaccinationPoint = vaccinationPointRepository.getById(id);
        update_id = id;
        PageData<RecordResponseDto> response = recordFacade.findAllByVaccinationPoint(vaccinationPoint, request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/records/point");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "Усі записи");
        return "pages/admin/records/records_all";
    }

    @PostMapping("/point")
    public ModelAndView findAllByVaccinationPointRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/records/point/" + update_id);
    }

    @GetMapping("/doctor/{id}")
    public String findAllByDoctor(@PathVariable @NotNull() Long id, Model model, WebRequest request) {
        Doctor doctor = doctorRepository.getById(id);
        update_id = id;
        PageData<RecordResponseDto> response = recordFacade.findAllByDoctor(doctor, request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/records/doctor");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "Усі записи");
        return "pages/admin/records/records_all";
    }

    @PostMapping("/doctor")
    public ModelAndView findAllByDoctorRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/records/doctor/" + update_id);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("record", recordFacade.findById(id));
        return "pages/admin/records/records_details";
    }

    @GetMapping("/update/{id}")
    public String updateRecordPage(@PathVariable Long id, Model model) {
        update_id = id;
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("record", recordFacade.findById(id));
        model.addAttribute("vaccines", Vaccine.values());
        return "pages/admin/records/record_update";
    }

    @GetMapping("/update/next")
    public String updateRecordNextPage(@ModelAttribute("record") RecordRequestDto dto, Model model) {
        List<RecordTime> freeRecordTime = new ArrayList<>();
        try {
            freeRecordTime = recordRepository.findAllRecordTimesByDoctorAndVaccineDate(dto.getDoctor(), ConvertString.convertStringToDate(dto.getVaccineDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("freeRecordTime", freeRecordTime);
        return "pages/admin/records/record_update_second_page";
    }

    @PostMapping("/update")
    public String updateRecord(@ModelAttribute("record") RecordRequestDto dto) {
        recordFacade.update(dto, update_id);
        return "redirect:/admin/records/details/" + update_id;
    }
}
