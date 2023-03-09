package com.safepay.fr.safepaySecure.DAL.Commandes;

import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableJpaRepositories
public interface AProductRepository  extends JpaRepository<MProduct,String> {
    //@Query(value = "SELECT product p , user.id , user.is_active,user.firstname,user.lastname,user.is_certify , detail d from product innner join user on product.user_id = user.id inner join detail on p.detail_id = detail.id",nativeQuery = true)
    @Query(value = "SELECT user.id from product  join  user limit 10",nativeQuery = true)
    public List<MProduct> findPublicationByUser();
    public List<MProduct> findMProductsByPosterId(String id);

}
