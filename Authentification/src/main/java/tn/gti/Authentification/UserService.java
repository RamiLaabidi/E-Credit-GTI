package tn.gti.Authentification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.gti.Authentification.Client.DemandeCreditClient;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository iUserRepository;
    private final DemandeCreditClient client;




    @Override
    public User addUser(User user) {
        return iUserRepository.save(user);     }

    @Override
    public User updateUser(User user) {

        return iUserRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Long idU) {
        Optional<User> UserOptional = iUserRepository.findById(idU);
        if (UserOptional.isPresent()) {
            iUserRepository.deleteById(idU);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) iUserRepository.findAll();
    }

    @Override
    public User getUserById(Long idU) {
        return iUserRepository.findById(idU).orElse(null);
    }


    public FullUserResponse  findUserWithdemandeCredits(Long idU) {
        var user = iUserRepository.findById(idU)
                .orElse(
                        User.builder()
                                .nom("NOT_FOUND")
                                .prenom("NOT_FOUND")
                                .build()
                );
        var demandeCredits = client.findAlldemandeCreditsByUser(idU);
        return FullUserResponse.builder()
                .name(user.getNom())
                .prenom(user.getPrenom())
                .demandeCredits(demandeCredits)
                .build();
    }

    }

