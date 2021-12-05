package personal.project.two_vago.service.impl;

import org.springframework.stereotype.Component;
import personal.project.two_vago.models.entities.Category;
import personal.project.two_vago.models.entities.enums.CategoryNameEnum;
import personal.project.two_vago.repository.CategoryRepository;
import personal.project.two_vago.service.CategoryService;

import java.util.Arrays;

@Component
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeRoles() {
        if (categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                            Category category = new Category();
                            category.setCategoryName(categoryNameEnum);

                            categoryRepository.save(category);
                        }
                );
    }

    @Override
    public Category findByCategoryName(CategoryNameEnum category) {

        return categoryRepository.findByCategoryName(category)
                .orElse(null);
    }
}
