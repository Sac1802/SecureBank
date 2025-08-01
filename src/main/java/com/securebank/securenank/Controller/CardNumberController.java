package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.cardViewDTO;
import com.securebank.securenank.DTO.card_numberDTO;
import com.securebank.securenank.Service.ServiceCardNumber;
import com.securebank.securenank.Utils.JsonResponse;
import com.securebank.securenank.Utils.StatusCard;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/card")
public class CardNumberController {

    private final ServiceCardNumber serviceCardNumber;

    public CardNumberController(ServiceCardNumber serviceCardNumber){
        this.serviceCardNumber = serviceCardNumber;
    }

    @PostMapping
    public ResponseEntity<?> saveCard(@Valid @RequestBody Map<String, Object> data) throws Exception {
        cardViewDTO cardGenerated = serviceCardNumber.createNewCard(data);
        return JsonResponse.sendJsonGenericDto(cardGenerated);
    }

    @GetMapping
    public ResponseEntity<?> getAllCards(){
        List<cardViewDTO> cardViewDTOList = serviceCardNumber.getAllCard();
        return JsonResponse.sendJsonGenericObjectList(cardViewDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        cardViewDTO cardFind = serviceCardNumber.getFindById(id);
        return JsonResponse.sendJsonGenericDto(cardFind);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable int id, StatusCard newStatus){
        cardViewDTO cardUpdatedStatus = serviceCardNumber.updateCardStatus(id, newStatus);
        return JsonResponse.sendJsonGenericDto(cardUpdatedStatus);
    }
}
