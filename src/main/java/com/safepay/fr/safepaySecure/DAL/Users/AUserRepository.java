package com.safepay.fr.safepaySecure.DAL.Users;

import com.safepay.fr.safepaySecure.BML.Users.MUser;
import org.springframework.data.repository.CrudRepository;

public interface AUserRepository  extends CrudRepository<MUser , String> {

   public MUser findByEmail(String email);
   public int   countByEmailOrPhone(String email,String phone);
   public int countByEmail(String email);
   public int countByPhone(String phone);
}
