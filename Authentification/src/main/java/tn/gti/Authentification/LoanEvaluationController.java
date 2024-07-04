package tn.gti.Authentification;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluation")
@RequiredArgsConstructor
public class LoanEvaluationController {

    private final LoanEvaluationServiceImpl loanEvaluationService;

    @PostMapping("/calculateScore")
    public ResponseEntity<Double> calculateScore(@RequestBody LoanEvaluationRequest request) {
        double score = loanEvaluationService.calculateScore(request.getUser(), request.getCompteBancaire(), request.getDemandeCredit());
        return ResponseEntity.ok(score);
    }

    @PostMapping("/evaluateRisk")
    public ResponseEntity<String> evaluateRisk(@RequestBody LoanEvaluationRequest request) {
        String riskLevel = loanEvaluationService.evaluateRisk(request.getUser(), request.getCompteBancaire(), request.getDemandeCredit());
        return ResponseEntity.ok(riskLevel);
    }

    @PostMapping("/approveCredit")
    public ResponseEntity<String> approveCredit(@RequestBody LoanEvaluationRequest request) {
        String approvalStatus = loanEvaluationService.approveCredit(request.getUser(), request.getCompteBancaire(), request.getDemandeCredit());
        return ResponseEntity.ok(approvalStatus);
    }
}
