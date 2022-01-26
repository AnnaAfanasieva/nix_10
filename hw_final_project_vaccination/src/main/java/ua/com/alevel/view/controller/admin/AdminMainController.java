package ua.com.alevel.view.controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminMainController {

    private final AdminRepository adminRepository;

    public AdminMainController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("admin", getCurrentUser());
        return "pages/admin/dashboard";
    }

    public Admin getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        return adminRepository.findByEmail(userEmail);
    }
}
