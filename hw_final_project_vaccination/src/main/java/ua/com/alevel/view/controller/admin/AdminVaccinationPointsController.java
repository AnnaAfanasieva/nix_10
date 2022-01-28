package ua.com.alevel.view.controller.admin;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.item.VaccinationPointFacade;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.VaccinationPointRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.VaccinationPointResponseDto;

@Controller
@RequestMapping("/admin/vaccination_points")
public class AdminVaccinationPointsController extends BaseController {

    private long update_id;
    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("№", null, null),
            new HeaderName("Локація", "address", "address"),
            new HeaderName("Деталі", null, null),
            new HeaderName("Видалити", null, null)
    };

    private final VaccinationPointFacade vaccinationPointFacade;

    public AdminVaccinationPointsController(VaccinationPointFacade vaccinationPointFacade) {
        this.vaccinationPointFacade = vaccinationPointFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<VaccinationPointResponseDto> response = vaccinationPointFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/vaccination_points/all");
        model.addAttribute("createNew", "/admin/vaccination_points/new");
        model.addAttribute("cardHeader", "Усі пункти вакцинації");
        return "pages/admin/vaccination_points/vaccination_points_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/vaccination_points");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("vaccinationPoint", vaccinationPointFacade.findById(id));
        return "pages/admin/vaccination_points/vaccination_points_details";
    }

    @GetMapping("/update/{id}")
    public String updateDoctorPage(@PathVariable Long id, Model model) {
        update_id = id;
        model.addAttribute("vaccination_point", vaccinationPointFacade.findById(id));
        return "pages/admin/vaccination_points/vaccination_point_update";
    }

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute("vaccination_point") VaccinationPointRequestDto dto) {
        vaccinationPointFacade.update(dto, update_id);
        return "redirect:/admin/vaccination_points/details/" + update_id;
    }

    @GetMapping("/new")
    public String redirectToNewPointPage(Model model) {
        model.addAttribute("vaccinationPoint", new VaccinationPointRequestDto());
        return "pages/admin/vaccination_points/vaccination_point_new";
    }

    @PostMapping("/new")
    public String createNewPoint(@ModelAttribute("vaccinationPoint") VaccinationPointRequestDto dto) {
        vaccinationPointFacade.create(dto);
        return "redirect:/admin/vaccination_points";
    }
}
