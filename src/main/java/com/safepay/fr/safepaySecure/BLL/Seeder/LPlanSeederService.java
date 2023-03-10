package com.safepay.fr.safepaySecure.BLL.Seeder;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Paiement.MPlan;
import com.safepay.fr.safepaySecure.DAL.Paiement.APlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LPlanSeederService {
    @Autowired
    APlanRepository aPlanRepository;


    @Transactional
    public ReturnMessage seedPlanTable() {
        ReturnMessage message = new ReturnMessage();
        List<MPlan> mPlanList = new ArrayList<>();
        MPlan basic = new MPlan();
        MPlan standard = new MPlan();
        MPlan premium = new MPlan();
        basic.setPrice(10000);
        basic.setDuration(30);
        basic.setName("Plan basic");
        basic.setDescription("Vos posts seront placés dans le top favoris afin de vous permettre d'avoir rapidement des acheteurs lorsque vous postez une offre");
        mPlanList.add(basic);
        standard.setPrice(20000);
        standard.setDuration(30);
        standard.setName("Plan Standard");
        standard.setDescription("1) Publication en favori" +
                "2) Un badge de compte certifié afin de prouver a vos acheteur que vous etes dans les regles" );
        mPlanList.add(standard);
        premium.setPrice(50000);
        premium.setDuration(30);
        premium.setName("Plan Prenium");
        premium.setDescription("Cette offre est reservé au profil business et vous permet d'avoir en premier temps toutes les offres lucratives sur la plateforme");
        mPlanList.add(premium);
        try {
            if(aPlanRepository.findAll().size() == 0){
                mPlanList.forEach((mPlan) -> aPlanRepository.save(mPlan));
                message.setMessage("Plan ajouté avec succes");
                message.setCode(HttpStatus.ACCEPTED);
            }
        }catch (Exception ex) {
            message.setMessage(ex.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }
        return message;
    }
}
