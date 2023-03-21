package com.safepay.fr.safepaySecure.BLL.Utils;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    PasswordEncoder passwordEncoder;
    public PasswordService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encode(String dataToBeEncoded) {
        return this.passwordEncoder.encode(dataToBeEncoded);
    }


    public boolean compare(String dataCrypted , String dataNotCrypted) {
        return this.passwordEncoder.matches(dataNotCrypted,dataCrypted);
    }
}
