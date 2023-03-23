package com.safepay.fr.safepaySecure.BLL.Seeder;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Users.MRole;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import com.safepay.fr.safepaySecure.DAL.Users.ARoleRepository;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import com.safepay.fr.safepaySecure.BLL.Utils.PasswordService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LUserSeederService {
    @Autowired
    AUserRepository aUserRepository;
    @Autowired
    ARoleRepository aRoleRepository;
    @Autowired
    PasswordService passwordService;

    LUserSeederService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Transactional
        public ReturnMessage CreateAdmin() {
        ReturnMessage message = new ReturnMessage();
        try {
            MUser mUser = new MUser();
            var role = aRoleRepository.findMRoleByName("Admin");

            if(role.isPresent()) {

                if(!aUserRepository.existsByEmail("admin@safepay.com") && !aUserRepository.existsByPhone("+225(XXX)") ) {
                    MRole mRole = role.get();
                    mUser.setRole(mRole);
                    mUser.setEmail("admin@safepay.com");
                    mUser.setPassword( this.passwordService.encode("Messi007"));
                    mUser.setFirstname("Admin");
                    mUser.setLastname("SafePay");
                    mUser.setPhone("+225(XXX)");
                    mUser.setIsActive(true);
                    mUser.setIsCertifed(true);
                    aUserRepository.save(mUser);
                    message.setCode(HttpStatus.ACCEPTED);
                    message.setMessage("Utilisateur crée");
                }else{
                    message.setCode(HttpStatus.BAD_REQUEST);
                    message.setMessage("Erreur lors de l'initialisation de l'admin");
                }

            }

        }catch (Exception ex) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(ex.getMessage());
        }
        return message;
    }
}
