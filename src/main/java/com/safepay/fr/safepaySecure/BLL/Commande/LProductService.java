package com.safepay.fr.safepaySecure.BLL.Commande;

import com.safepay.fr.safepaySecure.BML.Commande.MDetail;
import com.safepay.fr.safepaySecure.BML.Commande.Dto.MFullProductDetailDto;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.BML.Payload.MUserProduct;
import com.safepay.fr.safepaySecure.BML.Payload.MUserPublication;
import com.safepay.fr.safepaySecure.BML.Users.MUser;
import com.safepay.fr.safepaySecure.DAL.Commandes.ADetailRepository;
import com.safepay.fr.safepaySecure.DAL.Commandes.AProductRepository;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LProductService implements IService<MProduct> {

    @Autowired
    AProductRepository aProductRepository;
    @Autowired
    ADetailRepository aDetailRepository;
    @Autowired
    AUserRepository     aUserRepository;
    @Transactional
    public ReturnMessage savePublication(MFullProductDetailDto mFullProductDetailDto) {

        ReturnMessage message = new ReturnMessage();
        try
        {
            MDetail detail = new MDetail();
            MProduct product = new MProduct();
            detail.setAddress(mFullProductDetailDto.getAddress());
            detail.setDescription(mFullProductDetailDto.getDescription());
            detail.setPrice(mFullProductDetailDto.getPrice());
            detail.setMonnaie_echange(mFullProductDetailDto.getMonnaie_echange());
            detail.setMonnaie_a_recevoir(mFullProductDetailDto.getMonnaie_a_recevoir());
            detail.setQuantity(mFullProductDetailDto.getQuantity());
            detail.setTitle(mFullProductDetailDto.getTitle());
            var newDetail = aDetailRepository.save(detail);
            product.setDetail(newDetail);
            product.setActive(mFullProductDetailDto.isActive());
            product.setVerify(mFullProductDetailDto.isVerify());
            var User = aUserRepository.findById(mFullProductDetailDto.getUserId()).get();
            product.setPoster(User);
            aProductRepository.save(product);
            message.setCode(HttpStatus.ACCEPTED);
            message.setMessage("Publication ajouté avec succes !");

        }
        catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public Object save(MProduct mProduct) {
        return null;
    }

    @Override
    public ReturnMessage findAll() {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aProductRepository.findAll());
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }

        return message;

    }


    public  ReturnMessage getAllTransactionAndUser(boolean active , boolean verify ) {
        ReturnMessage message = new ReturnMessage();
        List<MUserPublication> mUserPublications = new ArrayList<>();
        List<MProduct> mProductList;
        try {
            mProductList = aProductRepository.findMProductsByIsActiveAndIsVerify(active,verify);
            mProductList.forEach((product)-> {
               var user = aUserRepository.findById(product.getPoster().getId());
               if(user.isPresent()) {
                   var currentUser = user.get();
                   MUserPublication mUserPublication = new MUserPublication();
                   mUserPublication.setProduct(product);
                   mUserPublication.setLastname(currentUser.getLastname());
                   mUserPublication.setFirstname(currentUser.getFirstname());
                   mUserPublication.setCertifed(currentUser.getIsCertifed());
                   mUserPublication.setActive(currentUser.getIsActive());
                   mUserPublications.add(mUserPublication);
               }
            });
            message.setReturnObject(mUserPublications);
            message.setCode(HttpStatus.ACCEPTED);
        }catch(Exception ex) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(ex.getMessage());
        }
        return message;
    }

    public ReturnMessage findTop10Publication() {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aProductRepository.findTop20MProductsByIsActiveAndIsVerify(true , false));
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }

        return message;
    }

    @Override
    public ReturnMessage findById(String id) {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aProductRepository.findById(id));
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }


    public ReturnMessage findMProductsByPosterIds(String id) {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aProductRepository.findMProductsByPosterId(id));
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    public ReturnMessage deleteByProductId(String id) {
        ReturnMessage message = new ReturnMessage();
        try
        {
            aProductRepository.deleteById(id);
            message.setMessage("Publication supprimé");
            message.setCode(HttpStatus.ACCEPTED);

        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return  message;
    }
}
