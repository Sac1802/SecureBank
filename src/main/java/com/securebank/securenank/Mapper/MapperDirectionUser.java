package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.direction_userDTO;
import com.securebank.securenank.Model.direction_user;
import org.springframework.stereotype.Component;

@Component
public class MapperDirectionUser {

    public direction_userDTO convertToDTO(direction_user direction){
        direction_userDTO directionDTO  = new direction_userDTO();
        directionDTO.setId_direction_user(direction.getId_direction_user());
        directionDTO.setNumber_house(direction.getNumber_house());
        directionDTO.setName_street(direction.getName_street());
        directionDTO.setNomenclature(direction.getNomenclature());
        directionDTO.setCountry(direction.getCountry());
        directionDTO.setCity(direction.getCity());
        return directionDTO;
    }

    public direction_user convertToDirection(direction_userDTO directionDTO){
        return new direction_user(directionDTO.getId_direction_user(), directionDTO.getNumber_house(),
                directionDTO.getName_street(), directionDTO.getNomenclature(), directionDTO.getCountry(),
                directionDTO.getCity());
    }

}
