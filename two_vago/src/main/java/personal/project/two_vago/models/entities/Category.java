package personal.project.two_vago.models.entities;

import personal.project.two_vago.models.entities.enums.CategoryNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "categories")
public class Category extends BaseEntity{

    private CategoryNameEnum categoryName;

    public Category() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryNameEnum categoryName) {
        this.categoryName = categoryName;
    }
}
