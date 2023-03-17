package com.safepay.fr.safepaySecure.BLL.Seeder;

import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Users.MRole;
import com.safepay.fr.safepaySecure.DAL.Users.ARoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class LRoleSeederService {

    @Autowired
    ARoleRepository aRoleRepository;

    @Transactional
    public ReturnMessage seedRole() {

        ReturnMessage message = new ReturnMessage();

            if (aRoleRepository.findAll().size() == 0) {

                List<MRole> mRoleList = new ArrayList<>();
                MRole staff = new MRole();
                MRole admin = new MRole();
                MRole business = new MRole();
                MRole utilisateur = new MRole();

                staff.setName("Staff");
                admin.setName("Admin");
                business.setName("Business");
                utilisateur.setName("Utilisateur");

                mRoleList.add(staff);
                mRoleList.add(admin);
                mRoleList.add(business);
                mRoleList.add(utilisateur);

                mRoleList.forEach((mRole) -> {
                    try {
                        aRoleRepository.save(mRole);
                        message.setMessage("Role ajouté avec succes");
                        message.setCode(HttpStatus.ACCEPTED);
                    }catch (Exception ex) {
                        message.setMessage(ex.getMessage());
                        message.setCode(HttpStatus.NOT_FOUND);
                    }
                });
            }


        return message;
    }

}
