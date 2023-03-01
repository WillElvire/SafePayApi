package com.safepay.fr.safepaySecure.BML.Transaction;

import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "transaction_type")
@Entity
public class MTransactionType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String libelle;
}
