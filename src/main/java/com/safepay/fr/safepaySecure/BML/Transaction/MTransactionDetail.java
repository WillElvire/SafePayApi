package com.safepay.fr.safepaySecure.BML.Transaction;

import com.safepay.fr.safepaySecure.BML.Commande.MPannier;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "transaction_detail")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MTransactionDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String amount;
    public String reason;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id",nullable = true)
    public MPannier cart;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_id",nullable = true)
    public MBilling billing;
    public boolean isValid = false;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
