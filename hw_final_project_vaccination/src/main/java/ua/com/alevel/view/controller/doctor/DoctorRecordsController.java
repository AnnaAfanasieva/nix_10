package ua.com.alevel.view.controller.doctor;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

@Controller
@RequestMapping("/doctor/records")
public class DoctorRecordsController extends BaseController {

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

    public DoctorRecordsController(RecordFacade recordFacade, DoctorRepository doctorRepository) {
        this.recordFacade = recordFacade;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping()
    public String findAllByDoctor(Model model, WebRequest request) {
        Doctor doctor = getCurrentUser();
        PageData<RecordResponseDto> response = recordFacade.findAllByDoctor(doctor, request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/doctor/records/all");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "Усі записи");
        return "pages/doctor/records/records_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllByDoctorRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "doctor/records");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("record", recordFacade.findById(id));
        return "pages/doctor/records/records_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        recordFacade.delete(id);
        return "redirect:/doctor/records";
    }

    public Doctor getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        return doctorRepository.findByEmail(userEmail);
    }
}
