package personal.project.two_vago.models.entities.view;

import personal.project.two_vago.models.entities.User;
import personal.project.two_vago.models.entities.enums.RoleNameEnum;
import personal.project.two_vago.repository.UserRepository;

public class UserViewModel {
    private Long id;

    private String profilePic;
    private String username;
    private String fullName;
    private Integer age;
    private String email;
    private RoleNameEnum role;


    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
