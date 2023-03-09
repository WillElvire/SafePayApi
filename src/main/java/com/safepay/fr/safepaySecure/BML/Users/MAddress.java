package com.safepay.fr.safepaySecure.BML.Users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class MAddress implements Serializable {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;
    private String name = "Safe Connect Address";
    @Column(nullable = false,unique = true)
    private String address;
    private int status = 1;
    private int priority = 1 ;
    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "user_id" , nullable = false )
    private MUser user;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;    
}
