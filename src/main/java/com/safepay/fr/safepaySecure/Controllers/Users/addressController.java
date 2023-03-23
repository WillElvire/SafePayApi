package com.safepay.fr.safepaySecure.Controllers.Users;

import com.safepay.fr.safepaySecure.BLL.Users.LAddressService;
import com.safepay.fr.safepaySecure.BML.Dto.MAddressDto;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/address")
public class addressController {
    @Autowired
    LAddressService lAddressService;

    @PostMapping()
    public ResponseEntity<ReturnMessage> save(@Validated @RequestBody MAddressDto mAddressDto) {
        ReturnMessage message = lAddressService.saveAddress(mAddressDto);
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<ReturnMessage> get(@PathVariable(value = "id") String id) {
        ReturnMessage message = lAddressService.findById(id);
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }


    @GetMapping()
    public ResponseEntity<ReturnMessage> get() {
        ReturnMessage message = lAddressService.findAll();
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<ReturnMessage> getAddressByUserId(@PathVariable(value = "id") String id) {
        ReturnMessage message = lAddressService.findAddressByUserId(id);
        if(message.getCode() == HttpStatus.OK) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReturnMessage> delete(@PathVariable(value = "id") String id) {
        ReturnMessage message = lAddressService.removeById(id);
        if(message.getCode() == HttpStatus.ACCEPTED) {
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(message.getCode()).body(message);
        }
    }
}
