package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.direction_userDTO;
import com.securebank.securenank.ExceptionHandler.ResourceNotFoundException;
import com.securebank.securenank.Mapper.MapperDirectionUser;
import com.securebank.securenank.Model.direction_user;
import com.securebank.securenank.Repository.RepositoryDirectionUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDirectionUser {

    private final RepositoryDirectionUser repositoryDirectionUser;
    private final MapperDirectionUser mapperDirectionUser;

    public ServiceDirectionUser(RepositoryDirectionUser repositoryDirectionUser,
                                MapperDirectionUser  mapperDirectionUser){
        this.repositoryDirectionUser  = repositoryDirectionUser;
        this.mapperDirectionUser = mapperDirectionUser;
    }

    @Transactional
    public direction_user saveDirectionUser(direction_userDTO directionUserDTO){
        if(directionUserDTO == null){
            throw new IllegalArgumentException("Direction User cannot be null");
        }
        direction_user  directionSaved  = mapperDirectionUser.convertToDirection(directionUserDTO);
        return repositoryDirectionUser.save(directionSaved);
    }

    public List<direction_userDTO>  findAllDirection(){
        return repositoryDirectionUser.findAll().stream()
                .map(mapperDirectionUser::convertToDTO)
                .toList();
    }

    public direction_userDTO findByIdDirection(int id){
        return repositoryDirectionUser.findById(id).map(mapperDirectionUser::convertToDTO).orElseThrow(
                ()  -> new ResourceNotFoundException("Account not found with ID: " + id)
        );
    }

    public direction_userDTO updateDirection(direction_userDTO  directionUserDTO ,int id){
        direction_user existsDirection  = repositoryDirectionUser.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account not found with ID: " + id)
        );
        existsDirection.setNumber_house(directionUserDTO.getNumber_house());
        existsDirection.setName_street(directionUserDTO.getName_street());
        existsDirection.setNomenclature(directionUserDTO.getNomenclature());
        existsDirection.setCountry(directionUserDTO.getCountry());
        existsDirection.setCity(directionUserDTO.getCity());

        repositoryDirectionUser.save(existsDirection);
        return mapperDirectionUser.convertToDTO(existsDirection);
    }
}
