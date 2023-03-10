package com.safepay.fr.safepaySecure.DAL.Commandes;

import com.safepay.fr.safepaySecure.BML.Commande.MPannier;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface APannierRepository extends JpaRepository<MPannier,String> {
    public List<MPannier> findMPannierByUserId(String id);
}
