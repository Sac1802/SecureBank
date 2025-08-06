package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.direction_userDTO;
import com.securebank.securenank.ExceptionHandler.ResourceNotFoundException;
import com.securebank.securenank.Mapper.MapperDirectionUser;
import com.securebank.securenank.Model.direction_user;
import com.securebank.securenank.Repository.RepositoryDirectionUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public direction_userDTO saveDirectionUser(direction_userDTO directionUserDTO){
        if(directionUserDTO == null){
            throw new IllegalArgumentException("Direction User cannot be null");
        }
        direction_user  directionSaved  = mapperDirectionUser.convertToDirection(directionUserDTO);
        direction_user savedDirection = repositoryDirectionUser.save(directionSaved);
        return mapperDirectionUser.convertToDTO(savedDirection);
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

    public String deleteDirection_user(int id){
        Optional<direction_user> existsDirection =  repositoryDirectionUser.findById(id);
        if(existsDirection.isEmpty()){
            throw new ResourceNotFoundException("Direction not found with ID: " + id);
        }
        repositoryDirectionUser.deleteById(id);
        return "Direction delete successfully";
    }

    public direction_userDTO patchDirection(Map<String, Object> dataDirection, int id){
        direction_user findDirection = repositoryDirectionUser.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("\"Direction not found with ID:" + id));
        dataDirection.forEach((key, value) -> {
            switch (key){
                case "number_house":
                    try {
                        int newNumberHouse = Integer.parseInt(value.toString());
                        findDirection.setNumber_house(newNumberHouse);
                        break;
                    }catch (NumberFormatException err){
                        throw new IllegalArgumentException("Invalid number house format: " + err.getMessage());
                    }
                case "name_street":
                    try{
                        String nameStreet = value.toString();
                        findDirection.setName_street(nameStreet);
                        break;
                    }catch (ClassCastException err){
                        throw new IllegalArgumentException("Invalid name street format: " + err.getMessage());
                    }

                case "nomenclature":
                    try {
                        String newNomenclature = value.toString();
                        findDirection.setNomenclature(newNomenclature);
                        break;
                    }catch (ClassCastException err){
                        throw new IllegalArgumentException("Invalid nomenclature format: " + err.getMessage());
                    }

                case "country":
                    try{
                        String newCountry = value.toString();
                        findDirection.setCountry(newCountry);
                        break;
                    }catch (ClassCastException err){
                        throw new IllegalArgumentException("Invalid country format: " +  err.getMessage());
                    }

                case "city":
                    try {
                        String newCity = value.toString();
                        findDirection.setCity(newCity);
                        break;
                    }catch (ClassCastException err){
                        throw new IllegalArgumentException("Invalid city format: " + err.getMessage());
                    }

                default:
                    break;

            }
        });
        repositoryDirectionUser.save(findDirection);
        return mapperDirectionUser.convertToDTO(findDirection);
    }
}
