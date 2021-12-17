package personal.project.two_vago.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import personal.project.two_vago.models.binding.UserLoginBindingModel;
import personal.project.two_vago.models.binding.UserRegisterBindingModel;
import personal.project.two_vago.models.entities.view.OfferSummaryView;
import personal.project.two_vago.models.entities.view.UserViewModel;
import personal.project.two_vago.models.service.UserServiceModel;
import personal.project.two_vago.service.OfferService;
import personal.project.two_vago.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final OfferService offerService;

    public UserController(UserService userService, ModelMapper modelMapper, OfferService offerService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }

    @GetMapping("/profile")
    private String profile(Principal principal, Model model) {
        UserViewModel viewModel = this.userService.getViewModelByUsername(principal.getName());
        List<OfferSummaryView> offersByUser = this.offerService.getOffersByUser(principal.getName());

        model.addAttribute("userViewModel", viewModel);
        model.addAttribute("userOffers", offersByUser);
        offerService.updateRank(principal.getName());

        return "profile";
    }

//    @PostMapping("/profile")
//    private String changeProfilePic(Principal principal, Model model) {
//        UserViewModel newPic = this.userService.changeProfilePic(principal.getName());
//        model.addAttribute("changePicture", newPic);
//
//        return "profile";
//    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirect) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {

            redirect.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirect.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        userService.registerAndLoginUser(modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}
