package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.DTO.app_user_view;
import com.securebank.securenank.Mapper.MapperAppUser;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Model.role;
import com.securebank.securenank.Repository.RepositoryAppUser;
import com.securebank.securenank.Repository.RepositoryRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceAppUser {

    private static RepositoryAppUser repositoryAppUser;
    private static MapperAppUser mapperAppUser;
    private static RepositoryRole repositoryRole;
    private final PasswordEncoder passwordEncoder;

    public ServiceAppUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public app_user save(app_userDTO userDTO){
        app_user user = mapperAppUser.convertToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public app_user_view updateUser(int id, app_user_view user){
        Optional<app_user> userUpdate = repositoryAppUser.findById(user.id_user);
        if(userUpdate.isPresent()){
            userUpdate.get().setId_user(user.id_user);
            userUpdate.get().setUsername(user.username);
            userUpdate.get().setEmail(user.email);
            Optional<role> newRole = repositoryRole.findById(user.role);
            if(newRole.isEmpty()){
                return null;
            }
            userUpdate.get().setRole(newRole.get());
            repositoryAppUser.save(userUpdate.get());
            return mapperAppUser.convertToView(userUpdate.get());
        }
        return null;
    }

    public String delete(int id){
        Optional<app_user> findUser = repositoryAppUser.findById(id);
        if(findUser.isPresent()){
            repositoryAppUser.deleteById(id);
            return "The user delete successfully";
        }
        return "The user with that id was not found.";
    }

    public app_user partialUpdate(int id, Map<String,  Object> update){
        Optional<app_user> userUpdate = repositoryAppUser.findById(id);
        if(userUpdate.isEmpty()) return null;
        update.forEach((key, value) -> {
            switch (key){
                case "username":
                    userUpdate.get().setUsername((String) value);
                    break;
                case "email":
                    userUpdate.get().setEmail((String) value);
                    break;
                case "password":
                    userUpdate.get().setPassword(passwordEncoder.encode((String) value));
                    break;
                case "role":
                    userUpdate.get().setRole((role) value);
                    break;
                default:
                    break;
            }
        });
        return repositoryAppUser.save(userUpdate.get());
    }
}
