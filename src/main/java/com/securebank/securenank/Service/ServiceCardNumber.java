package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.card_numberDTO;
import com.securebank.securenank.Mapper.MapperCardNumber;
import com.securebank.securenank.Repository.RepositoryCardNumber;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceCardNumber {

    private final RepositoryCardNumber repositoryCardNumber;
    private final MapperCardNumber mapperCardNumber;

    public ServiceCardNumber(RepositoryCardNumber repositoryCardNumber,
                             MapperCardNumber mapperCardNumber){
        this.repositoryCardNumber = repositoryCardNumber;
        this.mapperCardNumber = mapperCardNumber;
    }


    public String generateCardToken(){
        return "tok_" + UUID.randomUUID().toString().replace("-", "".substring(0, 16));
    }
}
