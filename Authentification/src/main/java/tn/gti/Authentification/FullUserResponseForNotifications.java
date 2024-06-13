package tn.gti.Authentification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserResponseForNotifications {
    private String name;
    private String prenom;
    List<Notification> notifications;


}
