package ua.com.alevel.view.controller.admin;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.user.DoctorFacade;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/admin/doctors")
public class AdminDoctorsController extends BaseController {

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


    public AdminDoctorsController(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<DoctorResponseDto> response = doctorFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/doctors/all");
        model.addAttribute("createNew", "/admin/doctors/new");
        model.addAttribute("cardHeader", "Усі лікарі");
        return "pages/admin/doctors/doctors_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/doctors");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("doctor", doctorFacade.findById(id));
        return "pages/admin/doctors/doctors_details";
    }
}
