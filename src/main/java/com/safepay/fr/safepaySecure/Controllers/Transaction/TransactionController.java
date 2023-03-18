package com.safepay.fr.safepaySecure.Controllers.Transaction;

import com.safepay.fr.safepaySecure.BLL.Seeder.LTransactionTypeSeederService;
import com.safepay.fr.safepaySecure.BLL.Transactions.LTransactionService;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Payload.MUserBillingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/transactions")
@Service
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionController {

    @Autowired
    LTransactionTypeSeederService lTransactionTypeSeederService;
    @Autowired
    LTransactionService lTransactionService;

    TransactionController(LTransactionTypeSeederService lTransactionTypeSeederService){
        this.lTransactionTypeSeederService = lTransactionTypeSeederService;
        lTransactionTypeSeederService.seedTransactionType();
    }

    @PostMapping("plan/subscription")
    public ResponseEntity<ReturnMessage> planSubscription(@RequestBody MUserBillingPlan mUserBillingPlan) {
        ReturnMessage message = lTransactionService.addUserBillingPlan(mUserBillingPlan);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<ReturnMessage> getAllTransactionById(@PathVariable("id") String id) {
        ReturnMessage message = lTransactionService.getAllTransactionById(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

}
