package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.DTO.app_user_view;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Model.role;
import com.securebank.securenank.Repository.RepositoryRole;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperAppUser {

    private final RepositoryRole repositoryRole;

    public MapperAppUser(RepositoryRole repositoryRole){
        this.repositoryRole = repositoryRole;
    }

    public app_userDTO convertToDTO(app_user user){
        app_userDTO userDTO = new app_userDTO();
        userDTO.setId_user(user.getId_user());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole().getId_role());
        return userDTO;
    }

    public app_user convertToUser(app_userDTO userDTO){
        Optional<role> role = repositoryRole.findById(userDTO.getRole());
        if(role.isPresent()){
            return new app_user(userDTO.getId_user(), userDTO.getUsername(),
                    userDTO.getEmail(), role.get(),userDTO.getPassword());
        }
        return new app_user();
    }

    public app_user_view convertToView(app_user user){
        app_user_view userDTO = new app_user_view();
        userDTO.setId_user(user.getId_user());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole().getId_role());
        return userDTO;
    }
}
