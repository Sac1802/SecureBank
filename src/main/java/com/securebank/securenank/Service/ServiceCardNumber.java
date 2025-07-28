package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.cardViewDTO;
import com.securebank.securenank.DTO.card_numberDTO;
import com.securebank.securenank.Mapper.MapperCardNumber;
import com.securebank.securenank.Repository.RepositoryCardNumber;
import com.securebank.securenank.Utils.AESUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class ServiceCardNumber {

    private final RepositoryCardNumber repositoryCardNumber;
    private final MapperCardNumber mapperCardNumber;
    private final AESUtil aesUtil;

    public ServiceCardNumber(RepositoryCardNumber repositoryCardNumber,
                             MapperCardNumber mapperCardNumber,
                             AESUtil aesUtil){
        this.repositoryCardNumber = repositoryCardNumber;
        this.mapperCardNumber = mapperCardNumber;
        this.aesUtil = aesUtil;
    }

    public cardViewDTO createNewCard(Map<String, Object> brandAndType) throws Exception {
        String brand =  brandAndType.get("brandCard").toString().toLowerCase();
        String type = brandAndType.get("typeCard").toString().toLowerCase();
        String numberCard = generateNumberCard(type, brand);
        String last4 = numberCard.substring(numberCard.length() - 4);
        String encryptedNumber = aesUtil.encrypt(numberCard);
        return new cardViewDTO();
    }

    public String generateCardToken(){
        return "tok_" + UUID.randomUUID().toString().replace("-", "".substring(0, 16));
    }

    public String generateNumberCard(String type, String brand){
        String bin = bin(type, brand);
        StringBuilder cardNumber = new StringBuilder(bin);

        for (int i = 0; i < 9; i++){
            cardNumber.append((int)(Math.random() * 10));
        }

        int luhnDigit   = luhnCheckDigit(cardNumber.toString());
        cardNumber.append(luhnDigit);

        return cardNumber.toString();
    }

    public String bin(String type, String brand){
        if(type.equals("debit") && brand.equals("visa")) return "400000";
        if(type.equals("credit") && brand.equals("visa")) return "411111";
        if(type.equals("credit") && brand.equals("mastercard"))  return "510000";
        if(type.equals("debit") && brand.equals("masterCard")) return "500000";
        return "000000";
    }

    public int luhnCheckDigit(String cardNumber){
        int  sum = 0;
        boolean alternate = true;
        for(int i = cardNumber.length() - 1; i >= 0; i--){
            int n = Integer.parseInt(cardNumber.substring(1,  i + 1));
            if(alternate){
                n *= 2;
                if(n > 9) n -= 9;
            }
            sum += n;
            alternate = false;
        }
        return (10 - (sum % 10) % 10);
    }
}
