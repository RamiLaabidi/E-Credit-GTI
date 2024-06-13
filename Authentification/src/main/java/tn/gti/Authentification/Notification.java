package tn.gti.Authentification;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    String titre;
    String message;
    Date dateCreation;
}
