package tn.gti.Authentification;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "\"users\"")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idU;
    @Enumerated(EnumType.STRING)
    ROLE role;
    String nom;
    String prenom;
    Date dateDeNaissance;
    @Enumerated(EnumType.STRING)
    TypeSF situationFamiliale;
    File photo;
    Long numCin;
    String email;
    String motDePasse;
    Double revenuMensuel;
    Double chargesMensuelles;
    Double salaire;
    @Enumerated(EnumType.STRING)
    EmploymentType employmentType;
    Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CompteBancaire> compteBancaires;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




}
