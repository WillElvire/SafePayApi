package com.safepay.fr.safepaySecure.BML.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MLoginPayload {
    private String email;
    private String password;
}
