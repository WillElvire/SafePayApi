package com.safepay.fr.safepaySecure.BML.Paiement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Table(name = "plan")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MPlan implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name =  "plan_id")
    private String id;
    private String name;
    private String description;
    private  int  duration ;

    private  float price;
    @OneToMany(mappedBy = "plan")
    private List<MBilling> billing;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
