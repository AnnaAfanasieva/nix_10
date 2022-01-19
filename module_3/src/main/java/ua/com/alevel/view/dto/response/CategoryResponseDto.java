package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.util.CategoryType;

public class CategoryResponseDto extends ResponseDto {

    private String categoryName;
    private CategoryType categoryType;

    public CategoryResponseDto() {
    }

    public CategoryResponseDto(Category category) {
        super.setId(category.getId());
        super.setCreated(category.getCreated());
        super.setUpdated(category.getUpdated());
        this.categoryName = category.getCategoryName();
        this.categoryType = category.getCategoryType();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
