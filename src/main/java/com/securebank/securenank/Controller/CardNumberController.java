package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.card_numberDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardNumberController {

    @PostMapping
    public ResponseEntity<?> saveCard(@Valid @RequestBody card_numberDTO card_numberDTO){

    }

}
