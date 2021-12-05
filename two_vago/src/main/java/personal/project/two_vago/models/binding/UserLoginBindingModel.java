package personal.project.two_vago.models.binding;

import personal.project.two_vago.models.entities.Role;

public class UserLoginBindingModel {
    private String username;
    private String password;
    private Role role;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
