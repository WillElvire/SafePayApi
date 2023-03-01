package com.safepay.fr.safepaySecure.Controllers.Users;

import com.safepay.fr.safepaySecure.BML.Users.MUser;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class userController {
    @Autowired
    private AUserRepository auserRepository;

    @GetMapping()
    public List<MUser> gets() {
        List<MUser> all = (List<MUser>) auserRepository.findAll();
        return all;
    }

    @PostMapping()
    public MUser add(@Validated @RequestBody MUser mUser) {
        return auserRepository.save(mUser);
    }
}
