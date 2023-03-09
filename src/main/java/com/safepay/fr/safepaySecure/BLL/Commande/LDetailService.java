package com.safepay.fr.safepaySecure.BLL.Commande;

import com.safepay.fr.safepaySecure.BML.Commande.MDetail;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.DAL.Commandes.ADetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LDetailService implements IService<MDetail> {

    @Autowired
    ADetailRepository aDetailRepository;
    @Override
    public MDetail save(MDetail mDetail) {
        return aDetailRepository.save(mDetail);
    }

    @Override
    public Object findAll() {
        return null;
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public Object findById(int id) {
        return null;
    }
}
