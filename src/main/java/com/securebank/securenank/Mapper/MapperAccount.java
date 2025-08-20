package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.Model.Account;
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

    public accountDTO convertToDTO(Account account){
        accountDTO accountDTO = new accountDTO();
        accountDTO.setId_account(account.getId_account());
        accountDTO.setId_user(account.getId_user().getId_user());
        accountDTO.setId_card(account.getId_card().getIdCard());
        accountDTO.setMount_account_total(account.getMount_account_total());
        return accountDTO;
    }

    public Account convertToAccount(accountDTO accountDTO){
        Optional<app_user> user = repositoryAppUser.findById(accountDTO.getId_user());
        Optional<card_number> card =  repositoryCardNumber.findById(accountDTO.getId_card());
        if(user.isPresent() && card.isPresent()){
            return new Account(accountDTO.getId_account(), user.get(), card.get(), accountDTO.getMount_account_total());
        }
        return new Account();
    }
}
