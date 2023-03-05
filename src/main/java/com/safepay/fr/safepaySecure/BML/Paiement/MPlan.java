package com.safepay.fr.safepaySecure.BML.Paiement;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
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
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
