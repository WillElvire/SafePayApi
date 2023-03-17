package com.safepay.fr.safepaySecure.BLL.Users;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.BML.Users.MAddress;
import com.safepay.fr.safepaySecure.DAL.Users.AAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LAddressService  implements IService<MAddress> {

    @Autowired
    AAddressRepository aAddressRepository;
    @Override
    public ReturnMessage save(MAddress address) {
        ReturnMessage message = new ReturnMessage();
        try
        {
            var countAddressExisting= aAddressRepository.countByAddress(address.getAddress());
            if(countAddressExisting > 0) {
                message.setMessage("Adresse déja existante");
                message.setCode(HttpStatus.BAD_REQUEST);
            }else{
                aAddressRepository.save(address);
                message.setMessage("Enregistrement effectué avec succes");
                message.setCode(HttpStatus.ACCEPTED);
            }
        }
        catch (Exception e){
            message.setMessage(e.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }

        return message;
    }

    public ReturnMessage findAddressByUserId(String id) {
        ReturnMessage message = new ReturnMessage();
        try
        {
            message.setReturnObject(aAddressRepository.findByUserId(id));
            message.setCode(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            message.setMessage(e.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }
        return message;
    }
    @Override
    public ReturnMessage findAll() {
        ReturnMessage message = new ReturnMessage();
        try
        {
            message.setReturnObject(aAddressRepository.findAll());
            message.setCode(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            message.setMessage(e.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }

        return message;
    }

    @Override
    public ReturnMessage findById(String id) {
        ReturnMessage message = new ReturnMessage();
        try
        {
            var address = aAddressRepository.findById(id);
            if(address.isPresent()) {
                message.setReturnObject(address);
                message.setCode(HttpStatus.ACCEPTED);
            }else{
                message.setMessage("Aucune address trouvé !");
                message.setCode(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e){
            message.setMessage(e.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }

        return message;
    }

    @Override
    public ReturnMessage findById(int id) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        aAddressRepository.deleteById(id);
    }

    public ReturnMessage removeById(String id) {
        ReturnMessage message = new ReturnMessage();
        try
        {
            if(aAddressRepository.findById(id).isPresent()){
                aAddressRepository.deleteById(id);
                message.setMessage("Adresse supprimé avec succes");
                message.setCode(HttpStatus.ACCEPTED);
            }else{
                aAddressRepository.deleteById(id);
                message.setMessage("Adresse introuvable");
                message.setCode(HttpStatus.NOT_FOUND);
            }

        }catch(Exception e) {
            message.setMessage(e.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }
       return message;
    }
}
