package ua.com.alevel.view.controller;

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
import ua.com.alevel.facade.UserFacade;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    //TODO realize UserController
    private long update_id;
    private final UserFacade userFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Name", "user_name", "userName"),
            new HeaderName("Email", "email", "email"),
            new HeaderName("Details", null, null),
            new HeaderName("Delete", null, null)
    };

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(userFacade.findAll(request), columnNames, model);
        model.addAttribute("createUrl", "/users/all");
        model.addAttribute("createNew", "/users/new");
        model.addAttribute("cardHeader", "All Users");
        return "pages/users/users_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "users");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("user", userFacade.findById(id));
        //TODO add model with all accounts
        return "pages/users/user_details";
    }
}
