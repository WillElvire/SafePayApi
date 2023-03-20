package com.safepay.fr.safepaySecure.DAL.Commandes;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.List;
@EnableJpaRepositories
public interface AProductRepository  extends JpaRepository<MProduct,String> {

    public List<MProduct> findMProductsByPosterId(String id);
    public List<MProduct> findTop20MProductsByIsActiveAndIsVerify(boolean active,boolean verify);
    public List<MProduct> findMProductsByPosterIdAndIsActive(String id , Boolean active);
    public int countMProductsByPosterId(String id);
    public int countMProductsByPosterIdAndIsVerify(String id,Boolean verify);
    public int countMProductsByPosterIdAndIsActive(String id , Boolean active);
    public List<MProduct> findMProductsByIsActive(boolean active);
    public List<MProduct> findMProductsByIsActiveAndIsVerify(boolean active , boolean verify);

}
