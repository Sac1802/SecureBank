package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.cardViewDTO;
import com.securebank.securenank.Mapper.MapperCardNumber;
import com.securebank.securenank.Model.card_number;
import com.securebank.securenank.Repository.RepositoryCardNumber;
import com.securebank.securenank.Utils.AESUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Transactional
    public cardViewDTO createNewCard(Map<String, Object> brandAndType) throws Exception {
        card_number newCard = new card_number();
        String brand =  brandAndType.get("brandCard").toString().toLowerCase();
        String type = brandAndType.get("typeCard").toString().toLowerCase();
        String numberCard = generateNumberCard(type, brand);
        String last4 = numberCard.substring(numberCard.length() - 4);
        LocalDate nowDate = LocalDate.now();
        newCard.setBrandCard(brand);
        newCard.setTypeCard(type);
        newCard.setLast4(last4);
        newCard.setEncryptedNumber(aesUtil.encrypt(numberCard));
        newCard.setCardToken(generateCardToken());
        newCard.setExpiryYear(nowDate.getYear() + 4);
        newCard.setExpiryMonth(nowDate.getMonthValue());
        card_number savedCard = repositoryCardNumber.save(newCard);
        return mapperCardNumber.convertToView(savedCard);
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
