package com.safepay.fr.safepaySecure.BLL.Paiement;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.BML.Paiement.MPlan;
import com.safepay.fr.safepaySecure.DAL.Paiement.APlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LPlanService implements IService<MPlan> {

    @Autowired
    APlanRepository aPlanRepository;
    @Override
    public ReturnMessage save(MPlan mPlan) {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aPlanRepository.save(mPlan));
            message.setMessage("Plan ajouté avec succes");
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public ReturnMessage findAll() {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aPlanRepository.findAll());
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public ReturnMessage findById(String id) {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aPlanRepository.findById(id));
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public Object findById(int id) {
        return null;
    }
}
