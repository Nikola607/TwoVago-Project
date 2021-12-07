package personal.project.two_vago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.two_vago.models.entities.Category;
import personal.project.two_vago.models.entities.Offer;
import personal.project.two_vago.models.entities.User;
import personal.project.two_vago.models.entities.enums.CategoryNameEnum;
import personal.project.two_vago.models.entities.view.OfferSummaryView;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByCategory(Category category);
    List<Offer> findAllByUser(User user);
}
