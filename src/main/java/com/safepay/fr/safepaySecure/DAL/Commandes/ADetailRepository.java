package com.safepay.fr.safepaySecure.DAL.Commandes;

import com.safepay.fr.safepaySecure.BML.Commande.MDetail;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ADetailRepository  extends CrudRepository<MDetail,String> {

}
