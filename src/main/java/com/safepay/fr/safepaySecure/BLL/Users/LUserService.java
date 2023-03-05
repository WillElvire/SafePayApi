package com.safepay.fr.safepaySecure.BLL.Users;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.BML.Payload.MLoginPayload;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LUserService implements IService<MUser> {
    AUserRepository aUserRepository;
    PasswordEncoder passwordEncoder;

    /*
     * dependency injection
     */
    LUserService(AUserRepository aUserRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.aUserRepository = aUserRepository;
    }

    //get list
    public List<MUser> findAll() {
        return (List<MUser>) this.aUserRepository.findAll();
    }

    @Override
    public Optional<MUser> findById(String id) {
        return this.aUserRepository.findById(id);
    }

    @Override
    public Optional<MUser> findById(int id) {
        //return this.aUserRepository.findById(id);
        return null;
    }

    public ReturnMessage save(MUser mUser) {

        ReturnMessage message = new ReturnMessage();
        var phone = mUser.getCountryCode() + mUser.getPhone();
        try {

            int userEmailCount =  aUserRepository.countByEmail(mUser.getEmail());
            if(userEmailCount == 0 ) {
                int userNumberCount = aUserRepository.countByPhone(phone);
                if(userNumberCount == 0) {

                    String encodedPassword = this.passwordEncoder.encode(mUser.getPassword());
                    mUser.setPassword(encodedPassword);
                    mUser.setPhone(phone);
                    mUser.setIsActive(false);
                    mUser.setIsCertifed(false);
                    mUser.setUseWeb3(false);
                    this.aUserRepository.save(mUser);
                    message.setCode(HttpStatus.ACCEPTED);
                    message.setMessage("Utilisateur crée");
                }
                else{
                    message.setCode(HttpStatus.BAD_REQUEST);
                    message.setMessage("Ce numero de téléphone est deja utilisé");
                }

            }else {
                message.setCode(HttpStatus.BAD_REQUEST);
                message.setMessage("Cette Adresse email est deja utilisé");
            }

        }
        catch(Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    /**
     * @author Wilfried koua
     * @param mLoginPayload
     * @return
     */
    public ReturnMessage login(MLoginPayload mLoginPayload) {

        ReturnMessage message =  new ReturnMessage();
        try
        {
            MUser user = this.aUserRepository.findByEmail(mLoginPayload.getEmail());

            if(user != null) {
                // check if password matches
                Boolean isPasswordMatches = passwordEncoder.matches(mLoginPayload.getPassword(),user.getPassword());
                //return the user
                if(isPasswordMatches) {
                    message.setCode(HttpStatus.ACCEPTED);
                    message.setReturnObject(user);
                }else{
                    message.setCode(HttpStatus.BAD_REQUEST);
                    message.setMessage("Vous avez renseigné un mauvais mot de passe veuillez ressayer.");
                }
            }else{
                message.setCode(HttpStatus.BAD_REQUEST);
                message.setMessage("Veuillez vérifier les informations fournies car elle semble erronée.");
            }

        }
        catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        //get the use with the entered email;
        return message;

    }
}
