package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.app_userDTO;
import com.securebank.securenank.DTO.app_user_view;
import com.securebank.securenank.ExceptionHandler.ResourceNotFoundException;
import com.securebank.securenank.Mapper.MapperAppUser;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Model.role;
import com.securebank.securenank.Repository.RepositoryAppUser;
import com.securebank.securenank.Repository.RepositoryRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceAppUser {

    private final RepositoryAppUser repositoryAppUser;
    private final MapperAppUser mapperAppUser;
    private final RepositoryRole repositoryRole;
    private final  PasswordEncoder passwordEncoder;

    public ServiceAppUser(RepositoryAppUser repositoryAppUser,
                          MapperAppUser mapperAppUser,
                          RepositoryRole  repositoryRole) {
        this.repositoryAppUser =  repositoryAppUser;
        this.mapperAppUser = mapperAppUser;
        this.repositoryRole = repositoryRole;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public app_user save(app_userDTO userDTO){
        app_user userSaved = mapperAppUser.convertToUser(userDTO);
        userSaved.setPassword(passwordEncoder.encode(userSaved.getPassword()));
        return repositoryAppUser.save(userSaved);
    }

    public List<app_user_view> findAll(){
        return repositoryAppUser.findAll().
                stream().
                map(mapperAppUser::convertToView)
                .toList();
    }

    public app_user_view findByIdUser(int id){
        return repositoryAppUser.findById(id).map(mapperAppUser::convertToView).orElseThrow(() ->
                new ResourceNotFoundException("User not found with ID: " + id));
    }

    public app_user_view updateUser(int id, app_user_view user){
        app_user userFindById = repositoryAppUser.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with ID:" + id));
        role roleFindById = repositoryRole.findById(user.getRole()).orElseThrow(() ->
                new ResourceNotFoundException("Role not found with ID:" + user.getRole()));
        userFindById.setUsername(user.getUsername());
        userFindById.setEmail(user.getEmail());
        userFindById.setRole(roleFindById);
        return mapperAppUser.convertToView(repositoryAppUser.save(userFindById));
    }

    public String delete(int id){
        Optional<app_user> findUser = repositoryAppUser.findById(id);
        if(findUser.isEmpty()){
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        repositoryAppUser.deleteById(id);
        return "User deleted successfully";
    }

    public app_user_view partialUpdate(int id, Map<String, Object> update) {
        app_user userUpdate = repositoryAppUser.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with ID: " + id));
        update.forEach((key, value) -> {
            if (value == null) throw new IllegalArgumentException("Value for '" + key + "' cannot be null.");
            switch (key) {
                case "username":
                    userUpdate.setUsername((String) value);
                    break;
                case "email":
                    userUpdate.setEmail((String) value);
                    break;
                case "password":
                    userUpdate.setPassword(passwordEncoder.encode((String) value));
                    break;
                case "role":
                    try{
                        int idRole = Integer.parseInt(value.toString());
                        role roleFind = repositoryRole.findById(idRole).orElseThrow(() ->
                                new ResourceNotFoundException("Role ID not found with ID:" + idRole));
                        userUpdate.setRole(roleFind);
                    }catch (NumberFormatException e){
                        throw new IllegalArgumentException("Role ID must be a valid integer.");
                    }
                    break;
                default:
                    break;
            }
        });

        return mapperAppUser.convertToView(repositoryAppUser.save(userUpdate));
    }

}
