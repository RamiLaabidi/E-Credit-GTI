package tn.gti.Notifications;


import java.util.List;

public interface INotificationService {
    Notification addNotification(Notification notification);

    Notification updateNotification(Notification notification);

    Boolean deleteNotification(Long idN);

    List<Notification> getAllNotification();

    Notification getNotificationById(Long idN);

    List<Notification> findAllNotificationsByUser(Long idU);
}
