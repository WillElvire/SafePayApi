package com.safepay.fr.safepaySecure.BML.Transaction;

import com.safepay.fr.safepaySecure.BML.Commande.MPannier;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "transaction_detail")
@Entity
public class MTransactionDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String amount;
    public String reason;
    @OneToOne
    @JoinColumn(name = "cart_id",nullable = true)
    public MPannier cart;
    @OneToOne
    @JoinColumn(name = "billing_id",nullable = true)
    public MBilling billing;
    public boolean isValid;

}
