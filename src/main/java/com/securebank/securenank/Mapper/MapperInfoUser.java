package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.info_userDTO;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Model.direction_user;
import com.securebank.securenank.Model.info_user;
import com.securebank.securenank.Repository.RepositoryAppUser;
import com.securebank.securenank.Repository.RepositoryDirectionUser;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperInfoUser {

    private static RepositoryAppUser repositoryAppUser;
    private static RepositoryDirectionUser repositoryDirectionUser;


    public info_userDTO convertToDTO(info_user info){
        info_userDTO infoDTO = new info_userDTO();
        infoDTO.id_info_user = info.getId_info_user();
        infoDTO.dni = info.getDni();
        infoDTO.number_phone = info.getNumber_phone();
        infoDTO.gender = info.getGender();
        infoDTO.user_id = info.getUser().getId_user();
        infoDTO.direction = info.getDirection().getId_direction_user();
        return infoDTO;
    }

    public info_user convertToInfo(info_userDTO infoDTO){
        Optional<app_user> user = repositoryAppUser.findById(infoDTO.user_id);
        Optional<direction_user> direction = repositoryDirectionUser.findById(infoDTO.direction);
        if(user.isPresent() && direction.isPresent()){
            return new info_user(infoDTO.id_info_user, infoDTO.dni, infoDTO.number_phone, infoDTO.gender, user.get(),
                    direction.get());
        }
        return new info_user();
    }
}
