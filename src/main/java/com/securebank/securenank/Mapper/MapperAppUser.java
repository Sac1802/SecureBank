package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Model.role;
import com.securebank.securenank.Repository.RepositoryRole;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperAppUser {

    private static RepositoryRole repositoryRole;

    public app_userDTO convertToDTO(app_user user){
        app_userDTO userDTO = new app_userDTO();
        userDTO.id_user = user.getId_user();
        userDTO.username  = user.getUsername();
        userDTO.email = user.getEmail();
        userDTO.password = user.getPassword();
        userDTO.role = user.getRole().getId_role();
        return userDTO;
    }

    public app_user convertToUser(app_userDTO userDTO){
        Optional<role> role = repositoryRole.findById(userDTO.role);
        if(role.isPresent()){
            return new app_user(userDTO.id_user, userDTO.username, userDTO.email, role.get(),userDTO.password);
        }
        return new app_user();
    }
}
