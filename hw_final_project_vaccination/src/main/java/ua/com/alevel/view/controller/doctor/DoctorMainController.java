package ua.com.alevel.view.controller.doctor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.user.DoctorRepository;

@Controller
@RequestMapping("/doctor/dashboard")
public class DoctorMainController {

    private final DoctorRepository doctorRepository;

    public DoctorMainController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("doctor", getCurrentUser());
        return "pages/doctor/dashboard";
    }

    public Doctor getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        return doctorRepository.findByEmail(userEmail);
    }
}
