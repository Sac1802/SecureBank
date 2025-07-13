package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.Mapper.MapperAccount;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Repository.RepositoryAccount;
import org.springframework.stereotype.Service;

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
}
