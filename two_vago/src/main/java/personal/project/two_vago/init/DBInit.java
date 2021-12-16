package personal.project.two_vago.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import personal.project.two_vago.service.*;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final OfferService offerService;

    public DBInit(UserService userService, CategoryService categoryService, CityService cityService, OfferService offerService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeRanks();
        userService.initializeRoles();
        categoryService.initializeRoles();
        cityService.initializeRoles();
        userService.initializeUsers();
        offerService.initializeOffers();
    }
}
