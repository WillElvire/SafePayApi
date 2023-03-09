package com.safepay.fr.safepaySecure.DAL.Users;

import com.safepay.fr.safepaySecure.BML.Users.MAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AAddressRepository  extends CrudRepository<MAddress,String> {

    public List<MAddress> findByUserId(String id);
    public int countByAddress(String address);
}
