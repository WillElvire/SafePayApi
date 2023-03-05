package com.safepay.fr.safepaySecure.BML.Users;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Role")

public class MRole  implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id ;
    @Column(name = "name",unique = true,nullable = false)
    private String name;

}
