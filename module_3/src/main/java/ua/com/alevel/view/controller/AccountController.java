package ua.com.alevel.view.controller;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.request.CategoryRequestDto;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {

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
        return "pages/accounts/account_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        accountFacade.delete(id);
        return "redirect:/accounts";
    }

    @GetMapping("/new/{id}")
    public String redirectToNewAccountPage(Model model, @PathVariable Long id) {
        AccountRequestDto account = new AccountRequestDto();
        account.setUserId(id);
        model.addAttribute("account", account);
        return "pages/accounts/account_new";
    }

    @PostMapping("/new")
    public String createNewAccount(@ModelAttribute("account") AccountRequestDto dto) {
        accountFacade.create(dto);
        String url = "redirect:/users/details/" + dto.getUserId();
        return url;
    }
}
