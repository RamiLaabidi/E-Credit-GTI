package tn.gti.Authentification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService iUserService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {

        return iUserService.addUser(user);

    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {

        return iUserService.updateUser(user);
    }


    @DeleteMapping("/delete/{idU}")
    public ResponseEntity<String> deleteUser(@PathVariable("idU") Long idU) {
        boolean deleted = iUserService.deleteUser(idU);
        if (deleted) {
            return ResponseEntity.ok("User deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + idU + " does not exist.");
        }
    }

    @GetMapping("/get/{idU}")
    public User getUserById(@PathVariable("idU") Long idU) {
        return iUserService.getUserById(idU);
    }


    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return ResponseEntity.ok().body(iUserService.getAllUser()).getBody();

    }


   //////////////////////////////////////////////////////////////////////////////

    @GetMapping("/with-demandeCredit/{user-idU}")
    public ResponseEntity<FullUserResponse> findAllUsers(@PathVariable("user-idU") Long idU) {
        return ResponseEntity.ok(iUserService.findUserWithdemandeCredits(idU));
    }

    @GetMapping("/with-notification/{user-idU}")
    public ResponseEntity<FullUserResponseForNotifications> findAllUsersNotif(@PathVariable("user-idU") Long idU) {
        return ResponseEntity.ok(iUserService.findUserWithnotifications(idU));
    }


    /*@PutMapping("/assignInvestmentToVenture/{id}/{idV}")
    public User assignCreditToClient(@PathVariable("id") Long id, @PathVariable("idC") Long idC) {

        return iUserService.assignCreditToClient(id, idC);

    }*/


    @PostMapping("/CalculRatioDendettement")
    public double CalculRatioDendettement(@RequestBody User user) {
        double chargesMensuelles = user.getChargesMensuelles();
        double revenuMensuel = user.getRevenuMensuel();
        double salaire = user.getSalaire();
        return (chargesMensuelles / revenuMensuel + salaire) * 100;
    }
    @PostMapping("/calculateRepaymentCapacity")
    public double calculateRepaymentCapacity(@RequestBody User user) {
        double chargesMensuelles = user.getChargesMensuelles();
        double revenuMensuel = user.getRevenuMensuel();
        double salaire = user.getSalaire();
        return revenuMensuel + salaire - chargesMensuelles;
    }
}
    






