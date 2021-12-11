package personal.project.two_vago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.two_vago.models.entities.Category;
import personal.project.two_vago.models.entities.enums.CategoryNameEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(CategoryNameEnum categoryName);
    Category findAllByCategoryName(CategoryNameEnum categoryName);
}
