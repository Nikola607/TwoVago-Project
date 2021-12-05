package personal.project.two_vago.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import personal.project.two_vago.models.entities.User;
import personal.project.two_vago.repository.UserRepository;

import java.util.Collections;

@Service
public class TwoVagoUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public TwoVagoUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + "not found!"));

       return mapByUserDetails(user);
    }

    private static UserDetails mapByUserDetails(User user){

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName());

        // User is the spring implementation of UserDetails interface.
        return new TwoVagoUser(
                user.getUsername(),
                user.getPassword(),
                authority
        );
    }
}
