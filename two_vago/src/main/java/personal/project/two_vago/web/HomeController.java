package personal.project.two_vago.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import personal.project.two_vago.service.OfferService;
import personal.project.two_vago.service.UserService;

import java.security.Principal;

@Controller
public class HomeController {
    private final OfferService offerService;
    private final UserService userService;

    public HomeController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        model.addAttribute("offers",
                offerService.getAllOffers());
        return "index";
    }

    @GetMapping("/{id}/details")
    public String showOffer(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("offer", this.offerService.findById(id, principal.getName()));
        return "details";
    }
}
