package com.safepay.fr.safepaySecure.BML.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import com.safepay.fr.safepaySecure.BML.Transaction.MTransaction;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Table(name = "users")
@Entity
@Data
public class MUser  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private  String phone;
    private String password;
    private  Boolean useWeb3;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id" , nullable = false )
    private MRole role;

    @OneToOne()
    @JoinColumn(name =  "billing_id", nullable = true)
    private MBilling billing;

    @OneToMany(mappedBy = "user")
    private List<MTransaction> transactions;

    public MUser(String firstname, String lastname, String email, String password, Boolean useWeb3, MRole role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.useWeb3 = useWeb3;
        this.role = role;
    }

    public MUser() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUseWeb3() {
        return useWeb3;
    }

    public void setUseWeb3(Boolean useWeb3) {
        this.useWeb3 = useWeb3;
    }

    public MRole getRole() {
        return role;
    }
    @JsonIgnore()
    public void setRole(MRole role) {
        this.role = role;
    }
}
