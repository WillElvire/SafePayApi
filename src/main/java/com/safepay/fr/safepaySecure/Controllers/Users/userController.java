package com.safepay.fr.safepaySecure.Controllers.Users;

import com.safepay.fr.safepaySecure.BLL.Seeder.LUserSeederService;
import com.safepay.fr.safepaySecure.BLL.Users.LUserService;
import com.safepay.fr.safepaySecure.BML.Dto.MRegisterDto;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Payload.MLoginPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class userController {
    @Autowired
    LUserService lUserService;
    @Autowired
    LUserSeederService lUserSeederService;

    userController(LUserSeederService lUserSeederService){
        this.lUserSeederService = lUserSeederService;
        this.lUserSeederService.CreateAdmin();
    }
    @GetMapping()
    public ResponseEntity<ReturnMessage> gets() {
        var  message = lUserService.findAll();
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnMessage> get(@PathVariable(value = "id") String id){
        var  message = lUserService.findById(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @PostMapping()
    public ResponseEntity<ReturnMessage> add(@Validated @RequestBody MRegisterDto mRegisterDto) {
        var  message = lUserService.save(mRegisterDto);
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.status(message.getCode()).body(message);
    }


    @GetMapping("{id}/report")
    public ResponseEntity<ReturnMessage> getUserReport(@PathVariable("id") String id) {
        var message = lUserService.getUserReport(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.status(message.getCode()).body(message);
    }
    @PostMapping("login")
    public ResponseEntity<ReturnMessage> login( @RequestBody MLoginPayload mLoginPayload) {
        var  message = lUserService.login(mLoginPayload);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.status(message.getCode()).body(message);

    }
}
