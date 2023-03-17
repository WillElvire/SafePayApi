package com.safepay.fr.safepaySecure.BLL.Seeder;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransactionType;
import com.safepay.fr.safepaySecure.DAL.Transaction.ATransactionTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LTransactionTypeSeederService {

   @Autowired
   ATransactionTypeRepository aTransactionTypeRepository;
    @Transactional
    public ReturnMessage seedTransactionType() {
        ReturnMessage message = new ReturnMessage();
        if(aTransactionTypeRepository.findAll().size() == 0) {
            List<MTransactionType> types = new ArrayList<>();
            MTransactionType deposit = new MTransactionType();
            deposit.setName("Deposit");
            deposit.setLibelle("Fund deposit");
            MTransactionType withdrall = new MTransactionType();
            withdrall.setName("Withdrall");
            withdrall.setLibelle("Fund withdrall");
            MTransactionType billing = new MTransactionType();
            billing.setName("Billing");
            billing.setLibelle("Plan billing");
            types.add(deposit);
            types.add(withdrall);
            types.add(billing);
            types.forEach((type)-> {
                aTransactionTypeRepository.save(type);
            });
        }
      return message;
    }
}
