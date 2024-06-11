package tn.gti.Authentification.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.gti.Authentification.DemandeCredit;

import java.util.List;
@FeignClient(name = "demandeCredit-service", url = "${application.config.demandeCredit-url}")

//@FeignClient(name = "demandeCredits", url = "http://localhost:8888")

public interface DemandeCreditClient {

    @GetMapping("/user/{user-idU}")
    List<DemandeCredit> findAlldemandeCreditsByUser(@PathVariable("user-idU") Long idU);
}