package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Model.card_number;
import com.securebank.securenank.Repository.RepositoryAppUser;
import com.securebank.securenank.Repository.RepositoryCardNumber;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperAccount {

    private static RepositoryAppUser repositoryAppUser;
    private static RepositoryCardNumber repositoryCardNumber;

    public accountDTO convertToDTO(account account){
        accountDTO accountDTO = new accountDTO();
        accountDTO.id_account = account.getId_account();
        accountDTO.id_user = account.getId_user().getId_user();
        accountDTO.id_card = account.getId_card().getId_card();
        accountDTO.mount_account_total = account.getMount_account_total();
        return accountDTO;
    }

    public account convertToAccount(accountDTO accountDTO){
        Optional<app_user> user = repositoryAppUser.findById(accountDTO.id_user);
        Optional<card_number> card =  repositoryCardNumber.findById(accountDTO.id_card);
        if(user.isPresent() && card.isPresent()){
            return new account(accountDTO.id_account, user.get(), card.get(), accountDTO.mount_account_total);
        }
        return new account();
    }
}
