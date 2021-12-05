package personal.project.two_vago.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class TwoVagoUser extends User {
    public TwoVagoUser(String username, String password,
                       GrantedAuthority authorities) {
        super(username, password, Collections.singleton(authorities));
    }

    public TwoVagoUser(String username, String password, boolean enabled,
                       boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getUserIdentifier() {
        return getUsername();
    }
}
