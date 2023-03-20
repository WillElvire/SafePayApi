package com.safepay.fr.safepaySecure.DAL.Transaction;

import com.safepay.fr.safepaySecure.BML.Transaction.MTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ATransactionRepository  extends JpaRepository<MTransaction,String> {

    List<MTransaction> findMTransactionByUserId(String id);
    int countMTransactionByUserId(String id);
}
