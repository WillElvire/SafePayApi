package com.safepay.fr.safepaySecure.BML.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Role")

public class MRole  implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id ;
    @Column(name = "name",unique = true)
    private String name;

    @JsonIgnore()
    @OneToMany(mappedBy = "role")
    private List<MUser> users;

    public MRole() {

    }

    public  MRole(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
