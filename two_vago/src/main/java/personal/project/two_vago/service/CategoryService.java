package personal.project.two_vago.service;

import org.springframework.stereotype.Service;
import personal.project.two_vago.models.entities.Category;
import personal.project.two_vago.models.entities.enums.CategoryNameEnum;

@Service
public interface CategoryService {
    void initializeRoles();

    Category findByCategoryName(CategoryNameEnum category);
}
