package com.safepay.fr.safepaySecure.BML.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MUserBillingPlan {
    private String typeTransaction;
    private String userId ;
    private String planId;
    private String amount;
    private String mean_of_payment;
    private  String address ;
    private String reason = "Plan billing Payment";
}
