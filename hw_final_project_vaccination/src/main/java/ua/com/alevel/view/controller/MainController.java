package ua.com.alevel.view.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;

@Controller
@RequestMapping("/dashboard")
public class MainController {

    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;

    public MainController(DoctorRepository doctorRepository, AdminRepository adminRepository) {
        this.doctorRepository = doctorRepository;
        this.adminRepository = adminRepository;
    }

    @GetMapping
    public String dashboard(Model model) {
        return redirectUser();
    }

    public String redirectUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        Doctor doctor = doctorRepository.findByEmail(userEmail);
        Admin admin = adminRepository.findByEmail(userEmail);
        if (doctor != null) {
            return "redirect:/doctor/dashboard";
        } else if (admin != null) {
            return "redirect:/admin/dashboard";
        } else {
            return "";
        }
    }
}
