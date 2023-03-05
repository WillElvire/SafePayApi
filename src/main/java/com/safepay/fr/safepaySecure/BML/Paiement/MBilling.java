package com.safepay.fr.safepaySecure.BML.Paiement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "billing")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MBilling implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;
    private boolean isActive;
    private Date expriationDate;
    private  int  status ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "plan_id" , nullable = false )
    private MPlan plan;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;


}
