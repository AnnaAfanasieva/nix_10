package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.view.dto.request.CategoryRequestDto;
import ua.com.alevel.view.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryFacade extends BaseFacade<CategoryRequestDto, CategoryResponseDto> {

    List<Category> findMapCategories();
}
