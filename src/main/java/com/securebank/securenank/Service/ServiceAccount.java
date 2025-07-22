package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.ExceptionHandler.ResourceNotFoundException;
import com.securebank.securenank.Mapper.MapperAccount;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Repository.RepositoryAccount;
import com.securebank.securenank.Utils.JsonResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public accountDTO updateAccount(accountDTO accountDTO, int id){
        if( accountDTO.getId_user() == 0 || accountDTO.getId_card() == 0 ||
                accountDTO.getMount_account_total() == 0){
            throw new IllegalArgumentException("Invalid data to update the account.");
        }
        account accountExists = repositoryAccount.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + id));

        account mapperAccountDTO = mapperAccount.convertToAccount(accountDTO);

        accountExists.setId_user(mapperAccountDTO.getId_user());
        accountExists.setId_card(mapperAccountDTO.getId_card());
        accountExists.setMount_account_total(mapperAccountDTO.getMount_account_total());

        repositoryAccount.save(accountExists);

        return mapperAccount.convertToDTO(accountExists);
    }

    public String deleteAccount(int id){
        Optional<account> findAccount = repositoryAccount.findById(id);
        if (findAccount.isPresent()) {
            repositoryAccount.deleteById(id);
            return "Delete account";
        }
        return null;
    }

    /*public accountDTO patchUpdate()*/
}
