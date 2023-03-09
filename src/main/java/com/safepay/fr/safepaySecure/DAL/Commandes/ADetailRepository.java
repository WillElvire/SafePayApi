package com.safepay.fr.safepaySecure.DAL.Commandes;

import com.safepay.fr.safepaySecure.BML.Commande.MDetail;
import org.springframework.data.repository.CrudRepository;

public interface ADetailRepository  extends CrudRepository<MDetail,String> {
}
