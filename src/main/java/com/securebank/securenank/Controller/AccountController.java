package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Service.ServiceAccount;
import com.securebank.securenank.Utils.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private ServiceAccount serviceAccount;

    @PostMapping("/save")
    public ResponseEntity<?> saveAccount(@RequestBody accountDTO accountDTO){
        account accountSaved = serviceAccount.saveAccountService(accountDTO);
        return accountSaved != null ? JsonResponse.sendJsonSuccessResponse("Account registered successfully") :
                JsonResponse.sendJsonErrorServerForbidden("The account could not be saved");
    }

}
