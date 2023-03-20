package com.safepay.fr.safepaySecure.BML.Payload;

import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MUserPublication {
    private  String firstname;
    private String lastname;
    private MProduct Product;
    private boolean isActive;
    private boolean isCertifed;

}
