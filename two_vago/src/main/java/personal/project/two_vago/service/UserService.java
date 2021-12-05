package personal.project.two_vago.service;

import org.springframework.stereotype.Service;
import personal.project.two_vago.models.entities.User;
import personal.project.two_vago.models.service.UserServiceModel;

@Service
public interface UserService {

    void initializeRoles();

    UserServiceModel findByUsernameAndPassword(String username, String password);

    User findById(Long id);

    void registerAndLoginUser(UserServiceModel userRegistrationServiceModel);

    void initializeUsers();
}
