package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.util.CategoryType;

public class CategoryRequestDto extends RequestDto {

    private String categoryName;
    private CategoryType categoryType;

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
