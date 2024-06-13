package tn.gti.Notifications;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface INotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findAllByIdU(Long idU);
}
