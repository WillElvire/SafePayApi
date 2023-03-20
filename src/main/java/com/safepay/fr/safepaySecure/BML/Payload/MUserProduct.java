package com.safepay.fr.safepaySecure.BML.Payload;

import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MUserProduct {
    MUser user ;
    MProduct product;
}
