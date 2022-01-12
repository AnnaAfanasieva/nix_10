package ua.com.alevel.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category_table")
public class Category extends BaseEntity {

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_type")
    private Boolean categoryType;
    //true - доход      false - расходы

    public Category() {
        super();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Boolean categoryType) {
        this.categoryType = categoryType;
    }
}
