package ua.com.alevel.view.controller;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.util.CategoryType;
import ua.com.alevel.view.dto.request.CategoryRequestDto;
import ua.com.alevel.view.dto.request.UserRequestDto;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

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
        model.addAttribute("accounts", userFacade.findSetAccountsByUserId(id));
        return "pages/users/user_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userFacade.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String redirectToNewUserPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/users/user_new";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") UserRequestDto dto) {
        userFacade.create(dto);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String updateUserPage(@PathVariable Long id, Model model) {
        update_id = id;
        model.addAttribute("user", userFacade.findById(id));
        return "pages/users/user_update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserRequestDto dto) {
        userFacade.update(dto, update_id);
        return "redirect:/users";
    }
}
