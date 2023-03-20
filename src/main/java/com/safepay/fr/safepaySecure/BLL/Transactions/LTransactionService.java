package com.safepay.fr.safepaySecure.BLL.Transactions;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import com.safepay.fr.safepaySecure.BML.Paiement.MPlan;
import com.safepay.fr.safepaySecure.BML.Payload.MUserBillingPlan;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransaction;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransactionDetail;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransactionType;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public Boolean compareDate(String date1 , String date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-mm");
        var start = formatter.parse(date1, new ParsePosition(0)) ;
        var stop = formatter.parse(date2,new ParsePosition(1)) ;
        return start.before(stop) ;
    }

    @Transactional
    public ReturnMessage addTransactionDetail(MUserBillingPlan mUserBillingPlan , MBilling bill){
        ReturnMessage message = new ReturnMessage();
        try {
            MTransactionDetail detailTransaction = new MTransactionDetail();
            detailTransaction.setAmount(mUserBillingPlan.getAmount());
            detailTransaction.setReason(mUserBillingPlan.getReason());
            detailTransaction.setBilling(bill);
            var detailTransac = aTransactionDetailRepository.save(detailTransaction);
            message.setReturnObject(detailTransac);
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception ex) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(ex.getMessage());
        }
       return message;
    }


    @Transactional
    public ReturnMessage addTransaction(MUser user , MTransactionType transactionType,MTransactionDetail detailTransac){
        ReturnMessage message = new ReturnMessage();
        try {
            MTransaction transaction = new MTransaction();
            transaction.setUser(user);
            transaction.setTransactionType(transactionType);
            transaction.setTransactionDetail(detailTransac);
            var billTransaction = aTransactionRepository.save(transaction);
            Map<String, Object> response = new HashMap<>();
            response.put("User",user);
            response.put("Ref",billTransaction.getId());
            message.setReturnObject(response);
            message.setMessage("Transaction effectuée avec succes !");
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception ex) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(ex.getMessage());
        }
        return message;
    }


    @Transactional
    public ReturnMessage addBillingPlan(MBilling billing, MUserBillingPlan mUserBillingPlan, MPlan plan ,MUser user){
        ReturnMessage message = new ReturnMessage();
        var currentDate = LocalDate.now();
        var newExpDate  = currentDate.plusDays(30).toString();
        try {
            if(user.getBilling() == null) {

                billing.setPlan(plan);
                billing.setMean_of_payment(mUserBillingPlan.getMean_of_payment());
                billing.setAddress(mUserBillingPlan.getAddress());
                billing.setExpriationDate(newExpDate);
                billing.setActive(true);
                var bill = aBillingRepository.save(billing);
                message.setReturnObject(bill);
                message.setCode(HttpStatus.ACCEPTED);
                return message;
            }

            if(this.compareDate(user.getBilling().getExpriationDate() ,currentDate.toString())){
                billing.setPlan(plan);
                billing.setMean_of_payment(mUserBillingPlan.getMean_of_payment());
                billing.setAddress(mUserBillingPlan.getAddress());
                billing.setExpriationDate(newExpDate);
                billing.setActive(true);
                var bill = aBillingRepository.save(billing);
                message.setReturnObject(bill);
                message.setCode(HttpStatus.ACCEPTED);
                return message;
            }

            message.setMessage("Vous avez deja un plan de souscription actif !");
            message.setCode(HttpStatus.BAD_REQUEST);
            return message;

        }catch (Exception ex) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(ex.getMessage());
            return message;
        }
    }


    @Transactional
    public ReturnMessage addUserBilling(MBilling billing,MUser user){
        ReturnMessage message = new ReturnMessage();
        var currentDate = LocalDate.now();
        try {
            var bill = aBillingRepository.save(billing);
            user.setBilling(bill);
            user.setIsCertifed(true);
            user.setIsActive(true);
            message.setReturnObject(aUserRepository.save(user));
            message.setCode(HttpStatus.ACCEPTED);
            return message;
        }catch (Exception ex) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(ex.getMessage());
            return message;
        }
    }



    public ReturnMessage addUserBillingPlan(MUserBillingPlan mUserBillingPlan) {
        ReturnMessage message = new ReturnMessage();
        try {
            var transactionType = aTransactionTypeRepository.findFirstByName(mUserBillingPlan.getTypeTransaction());
            var plan = aPlanRepository.findById(mUserBillingPlan.getPlanId());
            var user = aUserRepository.findById(mUserBillingPlan.getUserId());
            MBilling billing = new MBilling();
            Boolean condition = transactionType.isPresent() && plan.isPresent() && user.isPresent();
            if (condition) {
                message = this.addBillingPlan(billing, mUserBillingPlan, plan.get(),user.get());

                if (message.getCode() != HttpStatus.ACCEPTED) {
                    return message;
                }

                var bill = (MBilling) message.getReturnObject();

                message = this.addUserBilling(bill, user.get());
                if (message.getCode() != HttpStatus.ACCEPTED) {
                    return message;
                }
                var currentUser = (MUser) message.getReturnObject();
                message = this.addTransactionDetail(mUserBillingPlan, bill);
                if (message.getCode() != HttpStatus.ACCEPTED) {
                    return message;
                }
                message = this.addTransaction(currentUser, transactionType.get(), (MTransactionDetail) message.getReturnObject());
                return message;
            }
            message.setMessage("Une erreur est survenue veuillez contacter l'administrateur");
            message.setCode(HttpStatus.BAD_REQUEST);
            return  message;
        } catch (Exception ex) {
            message.setMessage(ex.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
            return message;
        }
    }


    public ReturnMessage getAllTransactionById(String id ) {
        ReturnMessage message = new ReturnMessage();
        try {
            message.setReturnObject(aTransactionRepository.findMTransactionByUserId(id));
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception ex) {
            message.setMessage(ex.getMessage());
            message.setCode(HttpStatus.BAD_REQUEST);
        }

        return  message;
    }
}
