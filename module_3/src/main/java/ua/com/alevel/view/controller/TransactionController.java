package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.TransactionFacade;

@Controller
@RequestMapping("/transactions")
public class TransactionController extends BaseController {

    //TODO realize TransactionController
    private long update_id;
    private final TransactionFacade transactionFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("User", "account_id", "account"),
            new HeaderName("Category", "category_id", "category"),
            new HeaderName("Sum", "sum", "sum"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public TransactionController(TransactionFacade transactionFacade) {
        this.transactionFacade = transactionFacade;
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
}
