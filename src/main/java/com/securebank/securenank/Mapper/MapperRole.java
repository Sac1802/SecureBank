package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.roleDTO;
import com.securebank.securenank.Model.role;
import org.springframework.stereotype.Component;

@Component
public class MapperRole {

    public roleDTO convertToDTO(role role){
        roleDTO roleDTO = new roleDTO();
        roleDTO.id_role = role.getId_role();
        roleDTO.user_role = role.getUser_role();
        return roleDTO;
    }

    public role convertToRole(roleDTO roleDTO){
        return new role(roleDTO.id_role, roleDTO.user_role);
    }
}
