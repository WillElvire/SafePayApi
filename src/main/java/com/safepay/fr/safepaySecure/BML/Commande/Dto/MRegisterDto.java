package com.safepay.fr.safepaySecure.BML.Commande.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MRegisterDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String countryCode;
    private String roleId;
    private boolean useWeb3 = true;

}
