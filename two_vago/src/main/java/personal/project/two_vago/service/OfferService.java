package personal.project.two_vago.service;

import org.springframework.stereotype.Service;
import personal.project.two_vago.models.binding.OfferAddBindingModel;
import personal.project.two_vago.models.entities.enums.CategoryNameEnum;
import personal.project.two_vago.models.entities.view.OfferDetailsView;
import personal.project.two_vago.models.entities.view.OfferSummaryView;
import personal.project.two_vago.models.service.OfferServiceModel;

import java.util.List;

@Service
public interface OfferService {

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView findById(Long id, String name);

    boolean isOwner(String userName, Long id);

    boolean isOwnerUpdate(String userName, Long id);

    void initializeOffers();

    OfferServiceModel addOffer(OfferAddBindingModel offerAddBindingModel, String ownerId);

    void deleteOffer(Long id);

    void updateOffer(OfferServiceModel offerModel);

    List<OfferSummaryView> getOffersByUser(String name);

    List<OfferSummaryView> getAllOffersByCategory(CategoryNameEnum name);

    void updateRank(String name);
}
