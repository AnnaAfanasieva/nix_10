package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.util.CategoryType;

public class CategoryRequestDto extends RequestDto {

    private String categoryName;
    private String categoryType;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
