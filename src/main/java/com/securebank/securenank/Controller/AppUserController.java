package com.securebank.securenank.Controller;

import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.DTO.app_user_view;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Service.ServiceAppUser;
import com.securebank.securenank.Utils.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private ServiceAppUser serviceAppUser;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody app_userDTO user){
        app_user userSave = serviceAppUser.save(user);
        return userSave != null
                ? JsonResponse.sendJsonSuccessResponse("User Registered Successfully") :
                JsonResponse.sendJsonErrorClientBadRequest("Role with id { " + user.role + "} not found");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        List<app_user_view> listUsers = serviceAppUser.findAll();
        return listUsers != null ?
                JsonResponse.sendJsonGenericObjectList(listUsers) :
                JsonResponse.sendJsonErrorServerNotFound(new ArrayList<app_user_view>().toString());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        app_user_view user = serviceAppUser.findByIdUser(id);
        return user != null ? JsonResponse.sendJsonGenericDto(user) :
                JsonResponse.sendJsonErrorServerNotFound("User with id { " + id + "} not found");
    }

    /*@PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody app_user_view user){
        
    }*/
}
