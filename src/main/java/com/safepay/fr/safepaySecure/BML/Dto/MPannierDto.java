package com.safepay.fr.safepaySecure.BML.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MPannierDto {
  private boolean isCheckout;
  private String userId;
  private List<String> productId;

}
