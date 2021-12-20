package personal.project.two_vago.service;

import org.springframework.stereotype.Service;
import personal.project.two_vago.models.entities.User;
import personal.project.two_vago.models.entities.view.UserViewModel;
import personal.project.two_vago.models.service.UserServiceModel;

import java.util.List;

@Service
public interface UserService {

    void initializeRoles();

    UserServiceModel findByUsernameAndPassword(String username, String password);

    User findById(Long id);

    boolean registerAndLoginUser(UserServiceModel userRegistrationServiceModel);

    void initializeUsers();

    UserViewModel getViewModelByUsername(String name);

    UserViewModel changeProfilePic(String name);

    void setLoggedIn();

    void loginPointSystem(String name);

    void initializeRanks();

    boolean isUserNameFree(String userName);

    List<UserViewModel> getAllUsers();

    void updateUser(UserServiceModel serviceModel);

    UserViewModel findByIdViewModel(Long id);
}
