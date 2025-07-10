package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.direction_userDTO;
import com.securebank.securenank.Model.direction_user;
import org.springframework.stereotype.Component;

@Component
public class MapperDirectionUser {

    public direction_userDTO convertToDTO(direction_user direction){
        direction_userDTO directionDTO  = new direction_userDTO();
        directionDTO.id_direction_user = direction.getId_direction_user();
        directionDTO.number_house = direction.getNumber_house();
        directionDTO.name_street = direction.getName_street();
        directionDTO.nomenclature = direction.getNomenclature();
        directionDTO.country = direction.getCountry();
        directionDTO.city = direction.getCity();
        return directionDTO;
    }

    public direction_user convertToDirection(direction_userDTO directionDTO){
        return new direction_user(directionDTO.id_direction_user, directionDTO.number_house, directionDTO.name_street,
                directionDTO.nomenclature, directionDTO.country, directionDTO.city);
    }

}
