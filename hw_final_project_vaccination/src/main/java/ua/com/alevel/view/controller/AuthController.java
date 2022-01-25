package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.auth.AuthValidatorFacade;
import ua.com.alevel.util.RoleType;
import ua.com.alevel.util.SecurityUtil;

@Controller
public class AuthController extends BaseController {

//    private final AuthValidatorFacade authValidatorFacade;
    private final SecurityService securityService;

    public AuthController(
//            AuthValidatorFacade authValidatorFacade,
            SecurityService securityService) {
//        this.authValidatorFacade = authValidatorFacade;
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
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

//    @GetMapping("/registration")
//    public String registration(Model model) {
//        if (securityService.isAuthenticated()) {
//            return redirectProcess(model);
//        }
//        model.addAttribute("authForm", new AuthDto());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("authForm") AuthDto authForm, BindingResult bindingResult, Model model) {
//        showMessage(model, false);
//        authValidatorFacade.validate(authForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        registrationFacade.registration(authForm);
//        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
//        return redirectProcess(model);
//    }

//    private String redirectProcess(Model model) {
//        showMessage(model, false);
//        if (SecurityUtil.hasRole(RoleType.ADMIN.name())) {
//            return "redirect:/admin/dashboard";
//        }
//        if (SecurityUtil.hasRole(RoleType.DOCTOR.name())) {
//            return "redirect:/doctor/dashboard";
//        }
//        return "redirect:/login";
//    }
}
