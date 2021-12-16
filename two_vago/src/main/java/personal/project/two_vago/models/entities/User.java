package personal.project.two_vago.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "users")
public class User extends BaseEntity {

    private String username;
    private String profilePic;
    private String password;
    private String fullName;
    private Integer age;
    private String email;
    private Role role;
    private List<Offer> offers;
    private String number;
    private boolean wasLoggedInToday = false;
    private int loginDays;
    private Rank rank;

    public User() {
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
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

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Column
    public boolean isWasLoggedInToday() {
        return wasLoggedInToday;
    }

    public void setWasLoggedInToday(boolean wasLoggedInToday) {
        this.wasLoggedInToday = wasLoggedInToday;
    }

    public int getLoginDays() {
        return loginDays;
    }

    public void setLoginDays(int loginDays) {
        this.loginDays = loginDays;
    }

    @ManyToOne
    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
