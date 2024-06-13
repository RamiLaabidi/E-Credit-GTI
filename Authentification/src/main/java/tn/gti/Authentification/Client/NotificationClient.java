package tn.gti.Authentification.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.gti.Authentification.Notification;

import java.util.List;

@FeignClient(name = "notification-service", url = "${application.config.notification-url}")


public interface NotificationClient {

    @GetMapping("/user/{user-idU}")
    List<Notification> findAllNotificationsByUser(@PathVariable("user-idU") Long idU);
}