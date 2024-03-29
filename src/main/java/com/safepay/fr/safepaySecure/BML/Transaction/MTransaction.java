package com.safepay.fr.safepaySecure.BML.Transaction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type_id")
    public MTransactionType transactionType;
    @JoinColumn(name = "transaction_detail_id")
    @OneToOne(cascade = CascadeType.ALL)
    public MTransactionDetail transactionDetail;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id" , nullable = false )
    public MUser user;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
