package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.Model.Account;
import com.securebank.securenank.Service.ServiceAccount;
import com.securebank.securenank.Utils.JsonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private ServiceAccount serviceAccount;

    @PostMapping("/")
    public ResponseEntity<?> saveAccount(@Valid @RequestBody accountDTO accountDTO){
        Account accountSaved = serviceAccount.saveAccountService(accountDTO);
        return JsonResponse.sendJsonGenericDto(accountSaved);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<accountDTO> listAccount = serviceAccount.getAllAccount();
        return JsonResponse.sendJsonGenericObjectList(listAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getById(@PathVariable int id){
        accountDTO accountFindById = serviceAccount.findById(id);
        return JsonResponse.sendJsonGenericDto(accountFindById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody accountDTO accountDTO, @PathVariable int id){
        accountDTO updateAccount = serviceAccount.updateAccount(accountDTO, id);
        return JsonResponse.sendJsonGenericDto(updateAccount);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        String response = serviceAccount.deleteAccount(id);
        return JsonResponse.sendJsonErrorServerNotFound(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdate(@PathVariable  int id,
                                         @Valid @RequestBody Map<String, Object> AccountUpdated){
        accountDTO response = serviceAccount.patchUpdate(id,  AccountUpdated);
        return JsonResponse.sendJsonGenericDto(response);
    }

}
