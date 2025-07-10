package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.DTO.app_user_view;
import com.securebank.securenank.Mapper.MapperAppUser;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Repository.RepositoryAppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAppUser {

    private static RepositoryAppUser repositoryAppUser;
    private static MapperAppUser mapperAppUser;


    public app_user save(app_userDTO userDTO){
        app_user user = mapperAppUser.convertToUser(userDTO);
        if (user != null){
            repositoryAppUser.save(user);
            return user;
        }
        return user;
    }

    public List<app_user_view> findAll(){
        return repositoryAppUser.findAll().stream().map(mapperAppUser::convertToView).toList();
    }

    public app_user_view findByIdUser(int id){
        app_user user = repositoryAppUser.findById(id).orElse(null);
        return user != null ? mapperAppUser.convertToView(user) : new app_user_view();
    }

    /*public app_user_view updateUser(int id, app_user_view user){

    }*/
}
