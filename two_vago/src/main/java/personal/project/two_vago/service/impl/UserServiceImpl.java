package personal.project.two_vago.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import personal.project.two_vago.models.entities.Role;
import personal.project.two_vago.models.entities.User;
import personal.project.two_vago.models.entities.enums.RoleNameEnum;
import personal.project.two_vago.models.service.UserServiceModel;
import personal.project.two_vago.repository.RoleRepository;
import personal.project.two_vago.repository.UserRepository;
import personal.project.two_vago.service.UserService;

import java.util.List;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TwoVagoUserServiceImpl twoVagoUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TwoVagoUserServiceImpl twoVagoUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.twoVagoUserService = twoVagoUserService;
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
                    .setFullName("Admin");
            admin
                    .setAge(999);
            admin
                    .setEmail("admin@admin.com");
            admin
                    .setRole(adminRole);


            admin.setRole(adminRole);

            userRepository.save(admin);
        }
    }

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
