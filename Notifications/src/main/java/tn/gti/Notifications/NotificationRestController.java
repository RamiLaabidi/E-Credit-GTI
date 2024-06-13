package tn.gti.Notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notification")
@RestController
@RequiredArgsConstructor
public class NotificationRestController {
    private final INotificationService iNotificationService;

    @PostMapping("/add")
    public Notification addNotification(@RequestBody Notification notification) {

        return iNotificationService.addNotification(notification);

    }

    @PutMapping("/update")
    public Notification updateNotification(@RequestBody Notification notification) {

        return iNotificationService.updateNotification(notification);
    }


    @DeleteMapping("/delete/{iN}")
    public ResponseEntity<String> deleteNotification(@PathVariable("idN") Long idN) {
        boolean deleted = iNotificationService.deleteNotification(idN);
        if (deleted) {
            return ResponseEntity.ok("Notification deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification with ID " + idN + " does not exist.");
        }
    }

    @GetMapping("/get/{idN}")
    public Notification getNotificationById(@PathVariable("idN") Long idN) {
        return iNotificationService.getNotificationById(idN);
    }


    @GetMapping("/getAllNotification")
    public List<Notification> getAllNotification() {
        return ResponseEntity.ok().body(iNotificationService.getAllNotification()).getBody();

    }


    @GetMapping("/user/{user-idU}")
    public ResponseEntity<List<Notification>> findAllNotifications(
            @PathVariable("user-idU") Long idU
    ) {
        return ResponseEntity.ok(iNotificationService.findAllNotificationsByUser(idU));
    }


}





