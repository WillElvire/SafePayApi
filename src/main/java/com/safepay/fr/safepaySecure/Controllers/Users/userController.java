package com.safepay.fr.safepaySecure.Controllers.Users;

import com.safepay.fr.safepaySecure.BLL.Users.LUserService;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Payload.MLoginPayload;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class userController {
    @Autowired
    LUserService lUserService;
    @GetMapping()
    public List<MUser> gets() {
        List<MUser> all = (List<MUser>) lUserService.findAll();
        return all;
    }

    @PostMapping()
    public ResponseEntity<ReturnMessage> add(@Validated @RequestBody MUser mUser) {
        var  message = lUserService.save(mUser);
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }

    }

    @PostMapping("login")
    public ResponseEntity<ReturnMessage> login(@Validated @RequestBody MLoginPayload mLoginPayload) {
        var  message = lUserService.login(mLoginPayload);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }
}
