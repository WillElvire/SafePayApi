package com.safepay.fr.safepaySecure.DAL.Transaction;

import com.safepay.fr.safepaySecure.BML.Transaction.MTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATransactionDetailRepository extends JpaRepository<MTransactionDetail , String> {
}
