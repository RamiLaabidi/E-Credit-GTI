package tn.gti.Authentification;


import java.util.List;

public interface IUserService {
    User addUser(User user);

    User updateUser(User user);

    Boolean deleteUser(Long idU);

    List<User> getAllUser();

    User getUserById(Long idU);
    FullUserResponse  findUserWithdemandeCredits(Long idU);
}
