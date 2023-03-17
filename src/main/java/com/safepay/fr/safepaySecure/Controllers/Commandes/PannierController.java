package com.safepay.fr.safepaySecure.Controllers.Commandes;

import com.safepay.fr.safepaySecure.BLL.Commande.LPannierService;
import com.safepay.fr.safepaySecure.BML.Commande.Dto.MPannierDto;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/cart")
public class PannierController {
    @Autowired
    LPannierService lPannierService;

    @PostMapping()
    public ResponseEntity<ReturnMessage> save(@Validated @RequestBody MPannierDto pannierDto){
        ReturnMessage message = lPannierService.addProductToPannier(pannierDto);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping()
    public ResponseEntity<ReturnMessage> gets(){
        ReturnMessage message = lPannierService.findAll();
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<ReturnMessage> getUserCart(@PathVariable("id") String id){
        ReturnMessage message = lPannierService.findByUserId(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<ReturnMessage> get(@PathVariable("id") String id ){
        ReturnMessage message = lPannierService.findById(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }


}
