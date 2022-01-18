package ua.com.alevel.view.controller;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.CategoryFacade;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.util.CategoryType;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.request.TransactionRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.CategoryResponseDto;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController extends BaseController {

    private final TransactionFacade transactionFacade;
    private final AccountFacade accountFacade;
    private final CategoryFacade categoryFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("User", "account_id", "account"),
            new HeaderName("Category", "category_id", "category"),
            new HeaderName("Sum", "sum", "sum"),
            new HeaderName("details", null, null)
    };

    public TransactionController(TransactionFacade transactionFacade, AccountFacade accountFacade, CategoryFacade categoryFacade) {
        this.transactionFacade = transactionFacade;
        this.accountFacade = accountFacade;
        this.categoryFacade = categoryFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(transactionFacade.findAll(request), columnNames, model);
        model.addAttribute("createUrl", "/transactions/all");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "All Transactions");
        return "pages/transactions/transactions_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "transactions");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("transaction", transactionFacade.findById(id));
        return "pages/transactions/transaction_details";
    }

    @GetMapping("/account/{id}")
    public String findAllTransactionsByAccount(Model model, WebRequest request, @PathVariable @NotNull() Long id) {
        initDataTable(transactionFacade.findAllTransactionsByAccount(request, id), columnNames, model);
        String userName = accountFacade.findById(id).getUser().getUserName();
        model.addAttribute("createUrl", "/transactions/account");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", userName + " Transactions");
        return "pages/transactions/account_transactions";
    }

    @PostMapping("/account")
    public ModelAndView findAllTransactionsByAccountRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "transactions");
    }

    @GetMapping("/download/account/{id}")
    public String downloadAllTransactionsByAccount(@PathVariable @NotNull() Long id) {
        transactionFacade.downloadAllTransactionsByAccount(id);
        return "redirect:/accounts/details/" + id;
    }

    @GetMapping("/new/{id}")
    public String redirectToNewTransactionPage(Model model, @PathVariable Long id) {
        TransactionRequestDto transaction = new TransactionRequestDto();
        transaction.setAccount_id(id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("categories", categoryFacade.findMapCategories());
        return "pages/transactions/transaction_new";
    }

    @PostMapping("/new")
    public String createNewTransaction(@ModelAttribute("transaction") TransactionRequestDto dto) {
        if (dto.getSum() > 0) {
            transactionFacade.create(dto);
            AccountResponseDto accountResponseDto = accountFacade.findById(dto.getAccount_id());
            CategoryResponseDto categoryResponseDto = categoryFacade.findById(dto.getCategory_id());

            long newBalance = accountResponseDto.getBalance();
            if(categoryResponseDto.getCategoryType().equals(CategoryType.expense)) {
                newBalance -= dto.getSum();
            } else if (categoryResponseDto.getCategoryType().equals(CategoryType.income)) {
                newBalance += dto.getSum();
            }

            AccountRequestDto accountRequestDto = new AccountRequestDto();
            accountRequestDto.setUserId(accountResponseDto.getUser().getId());
            accountRequestDto.setBalance(newBalance);
            accountFacade.update(accountRequestDto, dto.getAccount_id());
        }
        return "redirect:/transactions";
    }
}
