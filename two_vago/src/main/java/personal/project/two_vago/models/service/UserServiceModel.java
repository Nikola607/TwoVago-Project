package personal.project.two_vago.models.service;

import personal.project.two_vago.models.entities.Rank;
import personal.project.two_vago.models.entities.Role;

public class UserServiceModel {
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private Integer age;
    private String number;
    private String email;
    private Role role;
    private boolean wasLoggedInToday = false;
    private int loginDays;
    private Rank rank;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
