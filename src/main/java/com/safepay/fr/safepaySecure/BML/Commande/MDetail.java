package com.safepay.fr.safepaySecure.BML.Commande;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "detail")
@Entity
@Data
public class MDetail  implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)

    private String id ;
    private String title ;
    private long quantity;
    private long price;
    private String description;
    private String monnaie_echange;
    private String monnaie_a_recevoir;
    @Column(name = "keyword",nullable = true)
    private String Keyword ;
    @Column(nullable = false)
    private String address;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
