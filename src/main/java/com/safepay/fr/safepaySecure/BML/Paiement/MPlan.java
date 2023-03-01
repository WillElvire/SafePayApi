package com.safepay.fr.safepaySecure.BML.Paiement;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "plan")
@Entity
public class MPlan implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name =  "plan_id")
    private String id;
    private String name;
    private String description;
    private  int  duration ;

    @OneToMany(mappedBy = "plan")
    private List<MBilling> billing;
}
