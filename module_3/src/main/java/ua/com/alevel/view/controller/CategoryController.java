package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.CategoryFacade;
import ua.com.alevel.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    //TODO realize CategoryController
    private long update_id;
    private final CategoryFacade categoryFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Name", "category_name", "categoryName"),
            new HeaderName("Type", "category_type", "categoryType"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public CategoryController(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(categoryFacade.findAll(request), columnNames, model);
        model.addAttribute("createUrl", "/categories/all");
        model.addAttribute("createNew", "/categories/new");
        model.addAttribute("cardHeader", "All Categories");
        return "pages/categories/categories_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "categories");
    }
}
