package com.safepay.fr.safepaySecure.BML.Users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.safepay.fr.safepaySecure.BML.Commande.MPannier;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Table(name = "users")
@NoArgsConstructor()
@AllArgsConstructor()
@Entity
@Data
public class MUser  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull(message = "Le champ firstname est obligatoire")
    @NotBlank(message = "Le champ firstname ne peut etre vide")
    @Column(nullable = false)
    private String firstname;
    @NotNull(message = "Le champ lastname est obligatoire")
    @NotBlank(message = "Le champ lastname ne peut etre vide")
    @Column(nullable = false)
    private String lastname;
    @NotNull(message = "Le champ email est obligatoire")
    @NotBlank(message = "Le champ email ne peut etre vide")
    @Email
    @Column(unique = true,nullable = false)
    private String email;
    @NotNull(message = "Le champ phone est obligatoire")
    @NotBlank(message = "Le champ phone ne peut etre vide")
    @Column(unique = true,nullable = false)
    private  String phone;
    @NotNull(message = "Le champ password est obligatoire")
    @NotBlank(message = "Le champ password ne peut etre vide")
    @Column(nullable = false)
    private String password;


    @Column(insertable = false)
    private String countryCode;
    @Column(nullable = true)
    private  Boolean useWeb3;

    @Column(name = "isActive" , nullable = true)
    private  Boolean isActive;
    @Column(name = "isCeritifed" , nullable = true)
    private Boolean isCertifed;
    @OneToOne
    private MRole role;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name =  "user_id", nullable = true)
    private MPannier pannier;

    @JsonManagedReference
    @OneToOne()
    @JoinColumn(name =  "billing_id", nullable = true)
    private MBilling billing;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<MTransaction> transactions;

    @JsonManagedReference
    @OneToMany( mappedBy="user" )
    private List<MAddress> addresses;
    @JsonManagedReference
    @OneToMany(mappedBy = "poster")
    private List<MProduct> products;
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;




}
