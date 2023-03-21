package com.safepay.fr.safepaySecure.Controllers.Commandes;

import com.safepay.fr.safepaySecure.BLL.Commande.LProductService;
import com.safepay.fr.safepaySecure.BML.Commande.Dto.MFullProductDetailDto;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/publication")
public class ProductController {

    @Autowired
    LProductService lProductService;

    @PostMapping()
    public ResponseEntity<ReturnMessage> save(@Validated @RequestBody MFullProductDetailDto mFullProductDetailDto){
        ReturnMessage message = lProductService.savePublication(mFullProductDetailDto);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping()
    public ResponseEntity<ReturnMessage> gets(){
        ReturnMessage message = lProductService.getAllTransactionAndUser(true,false);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping("{id}/active")
    public ResponseEntity<ReturnMessage> activeProduct(@PathVariable("id") String id) {
        ReturnMessage message = lProductService.activePublication(id);
        if(message.getCode() == HttpStatus.ACCEPTED)  {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @GetMapping("notification")
    public ResponseEntity<ReturnMessage> notification(){
        ReturnMessage message = lProductService.getAllTransactionAndUser(false,false);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping("/last")
    public ResponseEntity<ReturnMessage> gets10Publication(){
        ReturnMessage message = lProductService.findTop10Publication();
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<ReturnMessage> get(@PathVariable("id") String id ){
        ReturnMessage message = lProductService.findById(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping("poster/{id}")
    public ResponseEntity<ReturnMessage> getByUserId(@PathVariable("id") String id ){
        ReturnMessage message = lProductService.findMProductsByPosterIds(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReturnMessage> delete(@PathVariable("id") String id){
        ReturnMessage message = lProductService.deleteByProductId(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }
}
