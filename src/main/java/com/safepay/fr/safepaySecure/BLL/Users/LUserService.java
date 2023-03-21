package com.safepay.fr.safepaySecure.BLL.Users;

import com.safepay.fr.safepaySecure.BML.Commande.Dto.MRegisterDto;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.BML.Payload.MLoginPayload;
import com.safepay.fr.safepaySecure.BML.Payload.MUserBillingPlan;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import com.safepay.fr.safepaySecure.DAL.Commandes.AProductRepository;
import com.safepay.fr.safepaySecure.DAL.Transaction.ATransactionRepository;
import com.safepay.fr.safepaySecure.DAL.Users.AAddressRepository;
import com.safepay.fr.safepaySecure.DAL.Users.ARoleRepository;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LUserService implements IService<MUser> {
    AUserRepository aUserRepository;
    PasswordEncoder passwordEncoder;
    AAddressRepository addressRepository;
    AProductRepository aProductRepository;
    ATransactionRepository aTransactionRepository;
    ARoleRepository aRoleRepository;
    /*
     * dependency injection
     */
    LUserService(ARoleRepository aRoleRepository, AUserRepository aUserRepository,AAddressRepository addressRepository , AProductRepository aProductRepository,ATransactionRepository aTransactionRepository){
        this.passwordEncoder    = new BCryptPasswordEncoder();
        this.aUserRepository    = aUserRepository;
        this.addressRepository  = addressRepository;
        this.aProductRepository = aProductRepository;
        this.aRoleRepository    = aRoleRepository;
        this.aTransactionRepository = aTransactionRepository;
    }

    //get list
    public ReturnMessage findAll() {

        ReturnMessage message = new ReturnMessage();
        try
        {
            var users = this.aUserRepository.findAll();
            message.setCode(HttpStatus.ACCEPTED);
            message.setReturnObject(users);

        }catch (Exception e) {

            message.setMessage(e.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }
        return  message;
    }

    @Override
    public ReturnMessage findById(String id) {
        ReturnMessage message = new ReturnMessage();
        try
        {
            var user = this.aUserRepository.findById(id);
            message.setCode(HttpStatus.ACCEPTED);
            message.setReturnObject(user);

        }catch (Exception e) {

             message.setMessage(e.getMessage());
             message.setCode(HttpStatus.BAD_REQUEST);
        }
        return  message;
    }

    @Override
    public Optional<MUser> findById(int id) {
        //return this.aUserRepository.findById(id);
        return null;
    }

    public ReturnMessage save(MUser mUser) {
        return null;
    }
    @Transactional
    public ReturnMessage save(MRegisterDto mRegisterDto) {

        ReturnMessage message = new ReturnMessage();
        var phone = mRegisterDto.getCountryCode() + mRegisterDto.getPhone();
        try {
            boolean existsByEmail =  aUserRepository.existsByEmail(mRegisterDto.getEmail());
            if(!existsByEmail)
            {
                boolean existsByPhone = aUserRepository.existsByPhone(phone);
                if(!existsByPhone)
                {
                    var role = aRoleRepository.findById(mRegisterDto.getRoleId());

                    if(role.isPresent()) {
                        MUser mUser = new MUser();
                        String encodedPassword = this.passwordEncoder.encode(mRegisterDto.getPassword());
                        mUser.setPassword(encodedPassword);
                        mUser.setPhone(phone);
                        mUser.setFirstname(mRegisterDto.getFirstname());
                        mUser.setLastname(mRegisterDto.getLastname());
                        mUser.setEmail(mRegisterDto.getEmail());
                        mUser.setIsActive(false);
                        mUser.setIsCertifed(false);
                        mUser.setUseWeb3(true);
                        mUser.setRole(role.get());
                        this.aUserRepository.save(mUser);
                        message.setCode(HttpStatus.ACCEPTED);
                        message.setMessage("Utilisateur crée");
                    }else{

                        message.setCode(HttpStatus.BAD_REQUEST);
                        message.setMessage("Erreur lors de la creation");
                    }
                }
                else
                {
                    message.setCode(HttpStatus.BAD_REQUEST);
                    message.setMessage("Ce numero de téléphone est deja utilisé");
                }
            }else
            {
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
    @Transactional
    public ReturnMessage login(MLoginPayload mLoginPayload) {

        ReturnMessage message =  new ReturnMessage();
        try
        {
            MUser user = this.aUserRepository.findByEmail(mLoginPayload.getEmail());

            if(user != null)
            {
                // check if password matches
                Boolean isPasswordMatches = passwordEncoder.matches(mLoginPayload.getPassword(),user.getPassword());
                //return the user
                if(isPasswordMatches)
                {
                    message.setCode(HttpStatus.ACCEPTED);
                    message.setReturnObject(user);
                }else
                {
                    message.setCode(HttpStatus.BAD_REQUEST);
                    message.setMessage("Vous avez renseigné un mauvais mot de passe veuillez ressayer.");
                }
            }else
            {
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





    @Transactional
    public ReturnMessage getUserReport(String id) {
        ReturnMessage message = new ReturnMessage();
        try {
            Map<String , Number > report = new HashMap<>();
            report.put("u_p_attente", this.aProductRepository.countMProductsByPosterIdAndIsActive(id , false));
            report.put("u_p_confirmer", this.aProductRepository.countMProductsByPosterIdAndIsActive(id , true));
            report.put("u_address", this.addressRepository.countMAddressByUserId(id));
            report.put("u_p_close",this.aProductRepository.countMProductsByPosterIdAndIsVerify(id , true));
            report.put("u_t_count",this.aTransactionRepository.countMTransactionByUserId(id));
            message.setCode(HttpStatus.ACCEPTED);
            message.setReturnObject(report);
        }catch(Exception ex) {
            message.setMessage(ex.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }
        return message;
    }



}
