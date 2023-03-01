package com.safepay.fr.safepaySecure.BML.Transaction;

import com.safepay.fr.safepaySecure.BML.Users.MUser;
import jakarta.persistence.*;

import java.io.Serializable;
@Table(name = "transaction")
@Entity
public class MTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    public MTransactionType transactionType;
    @ManyToOne()
    @JoinColumn(name = "user_id" , nullable = false )
    public MUser user;
}
