package com.safepay.fr.safepaySecure.BML.Commande;

import com.safepay.fr.safepaySecure.BML.Users.MUser;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Table(name = "cart")
@Entity
@Data
public class MPannier  implements Serializable  {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "cart_id")
    private String id ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private MUser user;

    @ManyToMany()
    @JoinTable(name = "ProductCart",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<MProduct> products;


}
