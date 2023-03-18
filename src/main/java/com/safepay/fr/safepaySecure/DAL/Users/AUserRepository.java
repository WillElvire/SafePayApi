package com.safepay.fr.safepaySecure.DAL.Users;

import com.safepay.fr.safepaySecure.BML.Paiement.MBilling;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AUserRepository  extends JpaRepository<MUser , String> {
   public MUser findByEmail(String email);
   public int   countByEmailOrPhone(String email,String phone);
   public int countByEmail(String email);
   public int countByPhone(String phone);
   public boolean existsByEmail(String email);
   public boolean existsByPhone(String phone);

}
