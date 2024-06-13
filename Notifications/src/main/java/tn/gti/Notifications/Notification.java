package tn.gti.Notifications;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idN;
     String titre;
     String message;
     Date dateCreation;
     @Enumerated(EnumType.STRING)
     StatutN statutN;
     @Enumerated(EnumType.STRING)
     TypeNotification type;
     String url;

     Long idU;



}
