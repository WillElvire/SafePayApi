package com.safepay.fr.safepaySecure.BML.Commande.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MAddressDto {
    private String name;
    private String address;
    private String user_id;
}
