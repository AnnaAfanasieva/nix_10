package ua.com.alevel.view.controller.doctor;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.item.VaccinationPointFacade;
import ua.com.alevel.view.controller.BaseController;

@Controller
@RequestMapping("/doctor/vaccination_points")
public class DoctorController extends BaseController {

    private final VaccinationPointFacade vaccinationPointFacade;

    public DoctorController(VaccinationPointFacade vaccinationPointFacade) {
        this.vaccinationPointFacade = vaccinationPointFacade;
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("vaccinationPoint", vaccinationPointFacade.findById(id));
        return "pages/doctor/vaccination_points/vaccination_points_details";
    }
}
