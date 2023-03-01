package com.safepay.fr.safepaySecure.DAL.Users;

import com.safepay.fr.safepaySecure.BML.Users.MUser;
import org.springframework.data.repository.CrudRepository;

public interface AUserRepository  extends CrudRepository<MUser , String> {

}
