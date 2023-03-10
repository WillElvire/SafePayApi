package com.safepay.fr.safepaySecure.DAL.Paiement;

import com.safepay.fr.safepaySecure.BML.Paiement.MPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APlanRepository  extends JpaRepository<MPlan, String> {
}
