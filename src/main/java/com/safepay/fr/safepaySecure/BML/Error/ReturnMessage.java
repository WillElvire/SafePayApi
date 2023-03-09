package com.safepay.fr.safepaySecure.BML.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnMessage {
    private String message;
    private Object ReturnObject;
    private HttpStatus Code;
}

