package com.safepay.fr.safepaySecure.BML.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MFullProductDetailDto {
    private String title ;
    private long quantity;
    private long price;
    private String description;
    private String monnaie_echange;
    private String monnaie_a_recevoir;
    private String address;
    private boolean isActive = false;
    private boolean isVerify = false;
    private String userId;
}
