package com.safepay.fr.safepaySecure.BML.Commande;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Table(name = "detail")
@Entity
@Data
public class MDetail  implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)

    private String id ;
    private String title;
    private long quantity;
    private long price;
    private String description;
    @Column(name = "created_at")
    private Date createdAT;
    @Column(name = "keyword")
    private String Keyword ;
    @Column(nullable = true)
    private String address;
}
