package personal.project.two_vago.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import personal.project.two_vago.models.entities.Offer;
import personal.project.two_vago.models.entities.Role;
import personal.project.two_vago.models.entities.User;
import personal.project.two_vago.models.entities.enums.RoleNameEnum;
import personal.project.two_vago.models.entities.view.OfferSummaryView;
import personal.project.two_vago.models.entities.view.UserViewModel;
import personal.project.two_vago.models.service.UserServiceModel;
import personal.project.two_vago.repository.OfferRepository;
import personal.project.two_vago.repository.RoleRepository;
import personal.project.two_vago.repository.UserRepository;
import personal.project.two_vago.service.UserService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TwoVagoUserServiceImpl twoVagoUserService;
    private final OfferRepository offerRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TwoVagoUserServiceImpl twoVagoUserService, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.twoVagoUserService = twoVagoUserService;
        this.offerRepository = offerRepository;
    }

    @Override
    public void registerAndLoginUser(UserServiceModel userRegistrationServiceModel) {
        Role userRole = roleRepository.findByRoleName(RoleNameEnum.USER);

        User newUser = new User();

        newUser.
                setUsername(userRegistrationServiceModel.getUsername());
        newUser
                .setFullName(userRegistrationServiceModel.getFullName());
        newUser
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()));
        newUser
                .setRole(userRole);
        newUser
                .setEmail(userRegistrationServiceModel.getEmail());
        newUser
                .setAge(userRegistrationServiceModel.getAge());

        newUser
                .setProfilePic(getRandomProfilePic());
        newUser
                .setNumber(userRegistrationServiceModel.getNumber());

        newUser = userRepository.save(newUser);

        // this is the Spring representation of a user
        UserDetails principal = twoVagoUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }

    private String getRandomProfilePic() {
        int random = new Random().nextInt(6);
        return "/images/profilePictures/" + random + ".png";
    }


    @Override
    public void initializeRoles() {
        if (roleRepository.count() != 0) {
            return;
        }

        if (userRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRoleName(RoleNameEnum.ADMIN);

            Role userRole = new Role();
            userRole.setRoleName(RoleNameEnum.USER);

            roleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    public void initializeUsers() {
        if (userRepository.count() == 0) {

            Role adminRole = roleRepository.findByRoleName(RoleNameEnum.ADMIN);

            User admin = new User();
            admin
                    .setUsername("admin");
            admin
                    .setPassword(passwordEncoder.encode("test"));
            admin
                    .setFullName("Jesus");
            admin
                    .setAge(999);
            admin
                    .setEmail("admin@admin.com");
            admin
                    .setRole(adminRole);
            admin
                    .setProfilePic(getRandomProfilePic());
            admin
                    .setNumber("088420420");

            admin.setRole(adminRole);

            userRepository.save(admin);
        }
    }

    @Override
    public UserViewModel getViewModelByUsername(String name) {
        return this.userRepository
                .findByUsername(name)
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .orElseThrow();
    }

    @Override
    public UserViewModel changeProfilePic(String name) {
        User user = userRepository.findByUsername(name).orElse(null);
        user.setProfilePic(getRandomProfilePic());

        return modelMapper.map(user, UserViewModel.class);
    }

//    @Override
//    public List<UserViewModel> getOffersByUser(String name) {
//        User user = userRepository.findByUsername(name).orElse(null);
//
//        return offerRepository.findAllByUser(user).stream()
//                .map(this::map)
//                .collect(Collectors.toList());
//    }
//
//    private UserViewModel map(Offer offerEntity) {
//
//        return this.modelMapper
//                 .map(offerEntity, UserViewModel.class);
//    }


    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id)
                .orElse(null);
    }
}
