package com.safepay.fr.safepaySecure.BLL.Commande;
import com.safepay.fr.safepaySecure.BML.Commande.Dto.MPannierDto;
import com.safepay.fr.safepaySecure.BML.Commande.MPannier;
import com.safepay.fr.safepaySecure.BML.Commande.MProduct;
import com.safepay.fr.safepaySecure.BML.Error.ReturnMessage;
import com.safepay.fr.safepaySecure.BML.Interface.IService;
import com.safepay.fr.safepaySecure.DAL.Commandes.APannierRepository;
import com.safepay.fr.safepaySecure.DAL.Commandes.AProductRepository;
import com.safepay.fr.safepaySecure.DAL.Users.AUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LPannierService implements IService<MPannier> {
    @Autowired
    APannierRepository aPannierRepository;
    @Autowired
    AUserRepository aUserRepository;
    @Autowired
    AProductRepository aProductRepository;
    @Override
    public Object save(MPannier mPannier) {
        return null;
    }

    @Transactional
    public ReturnMessage addProductToPannier(MPannierDto mPannierDto) {
        ReturnMessage message = new ReturnMessage();
        try
        {
            var user  =  aUserRepository.findById(mPannierDto.getUserId());
            List<MProduct> productList = new ArrayList<>();
            if(user.isPresent())
            {
                MPannier pannier = new MPannier();
                pannier.setUser(user.get());
                pannier.setCheckout(mPannierDto.isCheckout());
                mPannierDto.getProductId().forEach((id)-> {
                   var product =  aProductRepository.findById(id);
                   productList.add(product.get());
                });
                pannier.setProducts(productList);
                MPannier userCart = aPannierRepository.save(pannier);
                message.setReturnObject(userCart);
                message.setMessage("Produit ajouter au pannier ");
                message.setCode(HttpStatus.ACCEPTED);
            }else{
                message.setMessage("Erreur lors de la création du pannier");
                message.setCode(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex)
        {
            message.setMessage("Erreur lors de la création du pannier");
            message.setCode(HttpStatus.BAD_REQUEST);
        }
     return message;
    }

    @Override
    public ReturnMessage findAll() {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aPannierRepository.findAll());
            message.setCode(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            message.setCode(HttpStatus.BAD_REQUEST);
            message.setMessage(e.getMessage());
        }
        return message;
    }

    public ReturnMessage findByUserId(String id) {
        ReturnMessage message = new ReturnMessage();
        try{
            message.setReturnObject(aPannierRepository.findMPannierByUserId(id));
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
            message.setReturnObject(aPannierRepository.findById(id));
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
}
