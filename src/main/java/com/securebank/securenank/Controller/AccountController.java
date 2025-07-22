package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Service.ServiceAccount;
import com.securebank.securenank.Utils.JsonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private ServiceAccount serviceAccount;

    @PostMapping("/")
    public ResponseEntity<?> saveAccount(@RequestBody accountDTO accountDTO){
        account accountSaved = serviceAccount.saveAccountService(accountDTO);
        return accountSaved != null ? JsonResponse.sendJsonSuccessResponse("Account registered successfully") :
                JsonResponse.sendJsonErrorServerForbidden("The account could not be saved");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<account> listAccount = serviceAccount.getAllAccount();
        return !listAccount.isEmpty() ?  JsonResponse.sendJsonGenericObjectList(listAccount) :
                JsonResponse.sendJsonGenericObjectList(new ArrayList<account>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getById(@PathVariable int id){
        account accountFindById = serviceAccount.findById(id);
        return accountFindById != null ? JsonResponse.sendJsonGenericDto(accountFindById):
                JsonResponse.sendJsonErrorServerNotFound("The account with that id does not match any other.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody accountDTO accountDTO, @PathVariable int id){
        accountDTO updateAccount = serviceAccount.updateAccount(accountDTO, id);
        return JsonResponse.sendJsonGenericDto(updateAccount);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        String response = serviceAccount.deleteAccount(id);
        return response != null ? JsonResponse.sendJsonErrorServerNotFound(response) :
                JsonResponse.sendJsonErrorServerNotFound("The account with that id does not match any other.");
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdate(@PathVariable  int id){

    }*/

}
