package com.safepay.fr.safepaySecure.BML.Paiement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "billing")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MBilling implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;
    private boolean isActive;
    private String expriationDate;
    private  int  status ;
    @Column(nullable = true)
    private String mean_of_payment;
    @Column(nullable = true)
    private  String address;
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "plan_id" , nullable = false )
    private MPlan plan;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;


}
