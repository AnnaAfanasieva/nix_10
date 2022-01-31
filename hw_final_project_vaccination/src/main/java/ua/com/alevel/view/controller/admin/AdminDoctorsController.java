package ua.com.alevel.view.controller.admin;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.user.DoctorFacade;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/admin/doctors")
public class AdminDoctorsController extends BaseController {

    private long update_id;
    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("№", null, null),
            new HeaderName("Прізвище", "surname", "surname"),
            new HeaderName("Ім'я", "name", "name"),
            new HeaderName("По батькові", "patronymic", "patronymic"),
            new HeaderName("Локація", "vaccination_point_id", "vaccinationPoint"),
            new HeaderName("Деталі", null, null),
            new HeaderName("Видалити", null, null)
    };

    private final DoctorFacade doctorFacade;
    private final VaccinationPointRepository vaccinationPointRepository;
    private final DoctorRepository doctorRepository;
    private final BCryptPasswordEncoder encoder;


    public AdminDoctorsController(DoctorFacade doctorFacade, VaccinationPointRepository vaccinationPointRepository, DoctorRepository doctorRepository, BCryptPasswordEncoder encoder) {
        this.doctorFacade = doctorFacade;
        this.vaccinationPointRepository = vaccinationPointRepository;
        this.doctorRepository = doctorRepository;
        this.encoder = encoder;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<DoctorResponseDto> response = doctorFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/doctors/all");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "Усі лікарі");
        return "pages/admin/doctors/doctors_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/doctors");
    }

    @GetMapping("/point/{id}")
    public String findAllByVaccinationPoint(@PathVariable @NotNull() Long id, Model model, WebRequest request) {
        VaccinationPoint vaccinationPoint = vaccinationPointRepository.getById(id);
        update_id = id;
        PageData<DoctorResponseDto> response = doctorFacade.findAllByVaccinationPoint(vaccinationPoint, request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/doctors/point");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "Усі лікарі");
        return "pages/admin/doctors/doctors_all";
    }

    @PostMapping("/point")
    public ModelAndView findAllByVaccinationPointRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/doctors/point/" + update_id);
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("doctor", doctorFacade.findById(id));
        return "pages/admin/doctors/doctors_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        doctorFacade.delete(id);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/new/{id}")
    public String redirectToNewDoctorPage(Model model, @PathVariable Long id) {
        Doctor doctor = new Doctor();
        VaccinationPoint vaccinationPoint = vaccinationPointRepository.getById(id);
        doctor.setVaccinationPoint(vaccinationPoint);
        model.addAttribute("doctor", doctor);
        return "pages/admin/doctors/doctor_new";
    }

    @PostMapping("/new")
    public String createNewDoctor(@ModelAttribute("doctor") Doctor dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        doctorRepository.save(dto);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/update/{id}")
    public String updateDoctorPage(@PathVariable Long id, Model model) {
        update_id = id;
        model.addAttribute("vaccinationPoints", vaccinationPointRepository.findAll());
        model.addAttribute("doctor", doctorFacade.findById(id));
        return "pages/admin/doctors/doctor_update";
    }

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute("doctor") DoctorRequestDto dto) {
        doctorFacade.update(dto, update_id);
        return "redirect:/admin/doctors/details/" + update_id;
    }
}
