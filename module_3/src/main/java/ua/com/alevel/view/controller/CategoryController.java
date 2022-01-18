package ua.com.alevel.view.controller;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.CategoryFacade;
import ua.com.alevel.persistence.util.CategoryType;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.view.dto.request.CategoryRequestDto;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

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

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotNull() Long id, Model model) {
        model.addAttribute("category", categoryFacade.findById(id));
        return "pages/categories/category_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryFacade.delete(id);
        return "redirect:/categories";
    }

    @GetMapping("/new")
    public String redirectToNewCategoryPage(Model model) {
        model.addAttribute("category", new CategoryRequestDto());
        model.addAttribute("types", CategoryType.values());
        return "pages/categories/category_new";
    }

    @PostMapping("/new")
    public String createNewCategory(@ModelAttribute("category") CategoryRequestDto dto) {
        categoryFacade.create(dto);
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String updateCategoryPage(@PathVariable Long id, Model model) {
        update_id = id;
        model.addAttribute("category", categoryFacade.findById(id));
        return "pages/categories/category_update";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") CategoryRequestDto dto) {
        categoryFacade.update(dto, update_id);
        return "redirect:/categories";
    }
}
