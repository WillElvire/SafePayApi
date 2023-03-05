package com.safepay.fr.safepaySecure.BLL.Users;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.BML.Users.MRole;
import com.safepay.fr.safepaySecure.DAL.Users.ARoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LRoleService implements IService<MRole> {

    ARoleRepository aRoleRepository;
    public LRoleService(ARoleRepository aRoleRepository) {
        this.aRoleRepository = aRoleRepository;
    }

    @Override
    public ReturnMessage save(MRole mRole) {
        ReturnMessage message = new ReturnMessage();
        try  {
            aRoleRepository.save(mRole);
            message.setCode(HttpStatus.ACCEPTED);
            message.setMessage("Operation effectué");
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public ReturnMessage findAll() {
        ReturnMessage message = new ReturnMessage();
        try  {
            message.setReturnObject(aRoleRepository.findDistinctByNameIsNotNull());
            message.setCode(HttpStatus.OK);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public ReturnMessage findById(String id) {

        ReturnMessage message = new ReturnMessage();
        try  {
            message.setReturnObject(aRoleRepository.findById(id));
            message.setCode(HttpStatus.OK);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public Optional<MRole> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        //aRoleRepository.deleteById(id);
    }

    @Override
    public void deleteById(String id) {
       aRoleRepository.deleteById(id);
    }
}
