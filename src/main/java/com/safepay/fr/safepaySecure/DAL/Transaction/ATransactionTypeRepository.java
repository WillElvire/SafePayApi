package com.safepay.fr.safepaySecure.DAL.Transaction;

import com.safepay.fr.safepaySecure.BML.Transaction.MTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ATransactionTypeRepository extends JpaRepository<MTransactionType , String> {
    Optional<MTransactionType> findFirstByName(String name);
}
