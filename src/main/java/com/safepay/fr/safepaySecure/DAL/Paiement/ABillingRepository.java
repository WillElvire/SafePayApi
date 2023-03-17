package com.safepay.fr.safepaySecure.DAL.Paiement;

import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ABillingRepository extends JpaRepository<MBilling,String> {
}
