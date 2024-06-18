package tn.gti.Authentification.Auth;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.gti.Authentification.Config.JwtService;
import tn.gti.Authentification.IUserRepository;
import tn.gti.Authentification.ROLE;
import tn.gti.Authentification.User;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final IUserRepository iUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;


  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
            .nom(request.getNom())
            .prenom(request.getPrenom())
            .email(request.getEmail())
            .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
            .role(ROLE.CLIENT)
            .build();
    iUserRepository.save(user);

    Map<String, Object> claims = new HashMap<>();
    String jwtToken = jwtService.generateToken(claims, user, /* expirationTime */ 1000 * 60 * 60 * 24); // For example, 1 day

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getMotDePasse()
            )
    );
    var user = iUserRepository.findFirstByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

    Map<String, Object> claims = new HashMap<>();
    String jwtToken = jwtService.generateToken(claims, user, /* expirationTime */ 1000 * 60 * 60 * 24); // For example, 1 day

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .build();
  }




}
