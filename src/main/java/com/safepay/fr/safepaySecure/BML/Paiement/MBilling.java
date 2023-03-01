package com.safepay.fr.safepaySecure.BML.Paiement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Date;

@Table(name = "billing")
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


}
