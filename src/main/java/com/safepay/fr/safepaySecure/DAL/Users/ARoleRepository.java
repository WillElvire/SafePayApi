package com.safepay.fr.safepaySecure.DAL.Users;

import com.safepay.fr.safepaySecure.BML.Users.MRole;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.management.relation.Role;
import java.util.List;

public interface ARoleRepository extends JpaRepository<MRole,String> {
    public List<MRole> findDistinctByNameIsNotNull();
}
