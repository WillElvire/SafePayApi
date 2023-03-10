package com.safepay.fr.safepaySecure.Controllers.Paiement;

import com.safepay.fr.safepaySecure.BLL.Paiement.LPlanService;
import com.safepay.fr.safepaySecure.BLL.Seeder.LPlanSeederService;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Paiement.MPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/plan")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PlanController {
    @Autowired
    LPlanSeederService lPlanSeederService;
    @Autowired
    LPlanService lPlanService;
    PlanController(LPlanSeederService lPlanSeederService){
        this.lPlanSeederService = lPlanSeederService;
        this.lPlanSeederService.seedPlanTable();
    }

    @GetMapping()
    public ResponseEntity<ReturnMessage> gets() {
        ReturnMessage message = lPlanService.findAll();
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @PostMapping()
    public ResponseEntity<ReturnMessage> save(@Validated @RequestBody MPlan mPlan) {
        ReturnMessage message = lPlanService.save(mPlan);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<ReturnMessage> get(@PathVariable("id") String id) {
        ReturnMessage message = lPlanService.findById(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }
}
