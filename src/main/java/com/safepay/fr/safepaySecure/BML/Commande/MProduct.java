package com.safepay.fr.safepaySecure.BML.Commande;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "product")
@Entity
@Data
public class MProduct  implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "product_id")
    private  String id;
    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private MDetail detail;
    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "user_id" , nullable = false )
    private MUser poster;
    private boolean isActive;
    private boolean isVerify;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;


}
