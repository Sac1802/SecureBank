package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.cardViewDTO;
import com.securebank.securenank.DTO.card_numberDTO;
import com.securebank.securenank.Model.card_number;
import org.springframework.stereotype.Component;

@Component
public class MapperCardNumber {

    public card_number convertToCard(card_numberDTO card_numberDTO){
        return new card_number(card_numberDTO.getIdCard(), card_numberDTO.getLast4(),
                card_numberDTO.getEncryptedNumber(), card_numberDTO.getExpiryMonth(),
                card_numberDTO.getExpiryYear(), card_numberDTO.getTypeCard(),
                card_numberDTO.getBrandCard(), card_numberDTO.getCardToken());
    }

    public card_numberDTO convertToDTO(card_number cardNumber){
        card_numberDTO card_numberDTO = new card_numberDTO();
        card_numberDTO.setIdCard(cardNumber.getIdCard());
        card_numberDTO.setLast4(cardNumber.getLast4());
        card_numberDTO.setEncryptedNumber(cardNumber.getEncryptedNumber());
        card_numberDTO.setExpiryMonth(cardNumber.getExpiryMonth());
        card_numberDTO.setExpiryYear(cardNumber.getExpiryYear());
        card_numberDTO.setTypeCard(cardNumber.getTypeCard());
        card_numberDTO.setBrandCard(cardNumber.getBrandCard());
        card_numberDTO.setCardToken(cardNumber.getCardToken());
        return card_numberDTO;
    }

    public cardViewDTO convertToView(card_number card){
        cardViewDTO cardView = new cardViewDTO();
        cardView.setLast4(cardView.getLast4());
        cardView.setExpiryMonth(card.getExpiryMonth());
        cardView.setExpiryMonth(card.getExpiryMonth());
        cardView.setCardToken(card.getCardToken());
        return cardView;
    }

}
