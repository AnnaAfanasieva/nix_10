package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CategoryFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.CategoryRequestDto;
import ua.com.alevel.view.dto.response.CategoryResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryService categoryService;

    public CategoryFacadeImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void create(CategoryRequestDto categoryRequestDto) {
        Category category = createCategoryEntity(categoryRequestDto, new Category());
        categoryService.create(category);
    }

    @Override
    public void update(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            category = createCategoryEntity(categoryRequestDto, category);
            categoryService.update(category);
        }
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return new CategoryResponseDto(categoryService.findById(id));
    }

    @Override
    public PageData<CategoryResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Category> tableResponse = categoryService.findAll(dataTableRequest);

        List<CategoryResponseDto> categories = tableResponse.getItems().stream().
                map(CategoryResponseDto::new).
                collect(Collectors.toList());

        PageData<CategoryResponseDto> pageData = (PageData<CategoryResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(categories);

        return pageData;
    }

    private Category createCategoryEntity(CategoryRequestDto categoryRequestDto, Category category) {
        category.setCategoryName(categoryRequestDto.getCategoryName());
        category.setCategoryType(categoryRequestDto.getCategoryType());
        return category;
    }

    @Override
    public List<Category> findMapCategories() {
        return categoryService.findMapCategories();
    }
}
