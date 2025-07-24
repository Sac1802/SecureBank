package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.DTO.app_user_view;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Service.ServiceAppUser;
import com.securebank.securenank.Utils.JsonResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class AppUserController {

    final ServiceAppUser serviceAppUser;

    public AppUserController(ServiceAppUser serviceAppUser){
        this.serviceAppUser =  serviceAppUser;
    }


    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody app_userDTO user){
        app_user userSave = serviceAppUser.save(user);
        return JsonResponse.sendJsonGenericDto(userSave);
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        List<app_user_view> listUsers = serviceAppUser.findAll();
        return JsonResponse.sendJsonGenericObjectList(listUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        app_user_view user = serviceAppUser.findByIdUser(id);
        return JsonResponse.sendJsonGenericDto(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserFull(@PathVariable int id, @Valid @RequestBody app_user_view user){
        app_user_view userUpdated = serviceAppUser.updateUser(id, user);
        return JsonResponse.sendJsonGenericDto(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        String delete = serviceAppUser.delete(id);
        return JsonResponse.sendJsonSuccessResponse(delete);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody Map<String, Object> updates){
        app_user_view updateUser = serviceAppUser.partialUpdate(id, updates);
        return JsonResponse.sendJsonGenericDto(updateUser);
    }
}
