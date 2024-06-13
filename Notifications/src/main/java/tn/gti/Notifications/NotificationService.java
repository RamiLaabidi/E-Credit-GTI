package tn.gti.Notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationService implements INotificationService{

    @Autowired
    private final INotificationRepository iNotificationRepository;
    @Override
    public Notification addNotification(Notification notification) {
        return iNotificationRepository.save(notification);    }

    @Override
    public Notification updateNotification(Notification notification) {

        return iNotificationRepository.save(notification);
    }

    @Override
    public Boolean deleteNotification(Long idN) {
        Optional<Notification> NotificationOptional = iNotificationRepository.findById(idN);
        if (NotificationOptional.isPresent()) {
            iNotificationRepository.deleteById(idN);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
    }

    @Override
    public List<Notification> getAllNotification() {

        return (List<Notification>) iNotificationRepository.findAll();

    }

    @Override
    public Notification getNotificationById(Long idN) {
        return iNotificationRepository.findById(idN).orElse(null);

    }

    public List<Notification> findAllNotificationsByUser(Long idU) {
        return iNotificationRepository.findAllByIdU(idU);
    }
}
