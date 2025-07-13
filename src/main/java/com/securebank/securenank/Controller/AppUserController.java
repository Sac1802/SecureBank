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
import java.util.Map;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserTotal(@PathVariable int id, @RequestBody app_user_view user){
        if(user == null || user.id_user == 0 || user.username.trim().isEmpty() || user.email.trim().isEmpty()
                || user.role == 0){
            return JsonResponse.sendJsonErrorServerForbidden("It is necessary to provide all user data for the update");
        }
        app_user_view userUpdated = serviceAppUser.updateUser(id, user);
        return userUpdated != null ? JsonResponse.sendJsonSuccessResponse("The user updated successfully") :
                JsonResponse.sendJsonErrorServerNotFound("The user with that id was not found.");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        String delete = serviceAppUser.delete(id);
        return JsonResponse.sendJsonErrorServerNotFound(delete);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Map<String, Object> updates){
        try{
            app_user updateUser = serviceAppUser.partialUpdate(id, updates);
            return JsonResponse.sendJsonSuccessResponse("User Update Successfully");
        }catch (Exception e){
            return JsonResponse.sendJsonErrorClientBadRequest("User not found");
        }
    }

}
