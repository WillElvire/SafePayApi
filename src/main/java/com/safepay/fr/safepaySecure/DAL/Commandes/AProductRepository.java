package com.safepay.fr.safepaySecure.DAL.Commandes;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.List;
@EnableJpaRepositories
public interface AProductRepository  extends JpaRepository<MProduct,String> {

    public List<MProduct> findMProductsByPosterId(String id);
    public List<MProduct> findTop20MProductsByIsActive(boolean active);

}
