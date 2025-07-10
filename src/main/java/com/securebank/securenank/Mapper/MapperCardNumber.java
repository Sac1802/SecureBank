package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.card_numberDTO;
import com.securebank.securenank.Model.card_number;
import org.springframework.stereotype.Component;

@Component
public class MapperCardNumber {

    public card_numberDTO convertToDTO(card_number card){
        card_numberDTO cardDTO = new card_numberDTO();
        cardDTO.id_card = card.getId_card();
        cardDTO.number_card = card.getNumber_card();
        cardDTO.expired_date = card.getExpired_date();
        cardDTO.cvv = card.getCvv();
        cardDTO.type_card = card.getType_card();
        cardDTO.brand_card = card.getBrand_card();
        return cardDTO;
    }

    public card_number convertToCard(card_numberDTO cardDTO){
        return new card_number(cardDTO.cvv, cardDTO.id_card,  cardDTO.number_card, cardDTO.expired_date,
                cardDTO.type_card, cardDTO.brand_card);
    }
}
