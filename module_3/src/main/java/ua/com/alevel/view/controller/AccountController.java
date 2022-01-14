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
import ua.com.alevel.facade.AccountFacade;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {

    //TODO realize AccountController
    private long update_id;
    private final AccountFacade accountFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("User", "user_id", "user"),
            new HeaderName("Balance", "balance", "balance"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(accountFacade.findAll(request), columnNames, model);
        model.addAttribute("createUrl", "/accounts/all");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "All Accounts");
        return "pages/accounts/accounts_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "accounts");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("account", accountFacade.findById(id));
        //TODO add button "download csv file"
        return "pages/accounts/account_details";
    }
}
