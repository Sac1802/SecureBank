package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.Mapper.MapperAccount;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Repository.RepositoryAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAccount {

    private RepositoryAccount repositoryAccount;
    private MapperAccount mapperAccount;

    public account saveAccountService(accountDTO  accountDTO){
        account accountMapper = mapperAccount.convertToAccount(accountDTO);
        if(accountMapper != null){
            return repositoryAccount.save(accountMapper);
        }
        return null;
    }

    public List<account> getAllAccount(){
        return repositoryAccount.findAll();
    }

    public account findById(int id){
        Optional<account> accountFind = repositoryAccount.findById(id);
        return accountFind.orElse(null);
    }
}
