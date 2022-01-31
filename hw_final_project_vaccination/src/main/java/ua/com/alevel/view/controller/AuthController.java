package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.util.RoleType;
import ua.com.alevel.util.SecurityUtil;

@Controller
public class AuthController extends BaseController {

    private final SecurityService securityService;

    public AuthController(
            SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);
        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
                return "redirect:/admin/dashboard";
            }
            if (SecurityUtil.hasRole(RoleType.ROLE_DOCTOR.name())) {
                return "redirect:/doctor/dashboard";
            }
        }
        if (error != null) {
            showError(model, "Невірний логін або пароль");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }
}
