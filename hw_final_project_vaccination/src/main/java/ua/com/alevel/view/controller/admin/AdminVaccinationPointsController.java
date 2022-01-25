package ua.com.alevel.view.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.item.VaccinationPointFacade;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.VaccinationPointResponseDto;

@Controller
@RequestMapping("/admin/vaccination_points")
public class AdminVaccinationPointsController extends BaseController {

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
}
