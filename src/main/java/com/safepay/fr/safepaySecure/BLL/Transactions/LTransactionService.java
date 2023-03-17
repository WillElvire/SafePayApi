package com.safepay.fr.safepaySecure.BLL.Transactions;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import com.safepay.fr.safepaySecure.BML.Payload.MUserBillingPlan;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransaction;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransactionDetail;
import com.safepay.fr.safepaySecure.DAL.Paiement.ABillingRepository;
import com.safepay.fr.safepaySecure.DAL.Paiement.APlanRepository;
import com.safepay.fr.safepaySecure.DAL.Transaction.ATransactionDetailRepository;
import com.safepay.fr.safepaySecure.DAL.Transaction.ATransactionRepository;
import com.safepay.fr.safepaySecure.DAL.Transaction.ATransactionTypeRepository;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class LTransactionService {
    @Autowired
    AUserRepository aUserRepository;
    @Autowired
    APlanRepository aPlanRepository;
    @Autowired
    ATransactionTypeRepository aTransactionTypeRepository;
    @Autowired
    ATransactionRepository aTransactionRepository;
    @Autowired
    ATransactionDetailRepository aTransactionDetailRepository;
    @Autowired
    ABillingRepository aBillingRepository;

    @Transactional
    public ReturnMessage addUserBillingPlan(MUserBillingPlan mUserBillingPlan){
        ReturnMessage message = new ReturnMessage();
        try {
            var transactionType = aTransactionTypeRepository.findFirstByName(mUserBillingPlan.getTypeTransaction());
            var plan = aPlanRepository.findById(mUserBillingPlan.getPlanId());
            var user = aUserRepository.findById(mUserBillingPlan.getUserId());
            Boolean condition = transactionType.isPresent() && plan.isPresent() && user.isPresent();
            if(condition) {

                try {

                    MBilling billing = new MBilling();
                    billing.setPlan(plan.get());
                    billing.setExpriationDate(LocalDate.now().plusDays(30).toString());
                    billing.setActive(true);
                    var bill = aBillingRepository.save(billing);

                    try {

                        MTransactionDetail detailTransaction = new MTransactionDetail();
                        detailTransaction.setAmount(mUserBillingPlan.getAmount());
                        detailTransaction.setReason(mUserBillingPlan.getReason());
                        detailTransaction.setBilling(bill);
                        var detailTransac = aTransactionDetailRepository.save(detailTransaction);

                        try {

                            MTransaction transaction = new MTransaction();
                            transaction.setUser(user.get());
                            transaction.setTransactionType(transactionType.get());
                            transaction.setTransactionDetail(detailTransac);
                             aTransactionRepository.save(transaction);
                            message.setCode(HttpStatus.ACCEPTED);
                            message.setMessage("Transaction effectuée avec succes !");

                        }catch (Exception ex) {

                            message.setMessage(ex.getMessage());
                            message.setCode(HttpStatus.BAD_REQUEST);
                        }
                    }catch (Exception ex) {
                        message.setMessage(ex.getMessage());
                        message.setCode(HttpStatus.BAD_REQUEST);
                    }
                }catch (Exception ex) {
                    message.setMessage(ex.getMessage());
                    message.setCode(HttpStatus.BAD_REQUEST);
                }
            }else{
              message.setMessage("Une erreur est survenue veuillez contacter l'administrateur");
              message.setCode(HttpStatus.BAD_REQUEST);
            }
        }
       catch (Exception ex) {
            message.setMessage(ex.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
       }
        return message;
    }
}
