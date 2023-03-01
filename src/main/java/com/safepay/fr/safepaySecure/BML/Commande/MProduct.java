package com.safepay.fr.safepaySecure.BML.Commande;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Table(name = "product")
@Entity
@Data
public class MProduct  implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "product_id")
    private  String id;

    @OneToOne()
    private MDetail detail;
    private boolean isActive;
    private boolean isVerify;
    private Date createdAt;
    private Date updatedAt;


}
