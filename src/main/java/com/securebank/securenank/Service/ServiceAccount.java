package com.securebank.securenank.Service;

import com.securebank.securenank.DTO.accountDTO;
import com.securebank.securenank.ExceptionHandler.ResourceNotFoundException;
import com.securebank.securenank.Mapper.MapperAccount;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Model.app_user;
import com.securebank.securenank.Model.card_number;
import com.securebank.securenank.Repository.RepositoryAccount;
import com.securebank.securenank.Repository.RepositoryAppUser;
import com.securebank.securenank.Repository.RepositoryCardNumber;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceAccount {

    private final RepositoryAccount repositoryAccount;
    private final RepositoryAppUser repositoryAppUser;
    private final RepositoryCardNumber repositoryCardNumber;
    private final MapperAccount mapperAccount;

    public ServiceAccount(RepositoryAccount repositoryAccount,
                          RepositoryAppUser repositoryAppUser,
                          RepositoryCardNumber repositoryCardNumber,
                          MapperAccount mapperAccount) {
        this.repositoryAccount = repositoryAccount;
        this.repositoryAppUser = repositoryAppUser;
        this.repositoryCardNumber = repositoryCardNumber;
        this.mapperAccount = mapperAccount;
    }

    @Transactional
    public account saveAccountService(accountDTO  accountDTO){
        if (accountDTO == null) {
            throw new IllegalArgumentException("AccountDTO cannot be null");
        }
        account accountMapper = mapperAccount.convertToAccount(accountDTO);
        return repositoryAccount.save(accountMapper);
    }

    public List<accountDTO> getAllAccount(){
        return repositoryAccount.findAll().stream()
                .map(mapperAccount::convertToDTO)
                .toList();
    }

    public accountDTO findById(int id){
        return repositoryAccount.findById(id).map(mapperAccount::convertToDTO).orElseThrow(() ->
                new ResourceNotFoundException("Account not found with ID: " + id));
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
        if(findAccount.isEmpty()) {
            throw new ResourceNotFoundException("Account not found with ID: " + id);
        }
        repositoryAccount.deleteById(id);
        return "Account delete successfully";
    }

    @Transactional
    public accountDTO patchUpdate(int id, Map<String, Object> update){
        account accountUpdate = repositoryAccount.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Account not found with ID: " + id));
        update.forEach((key, value) -> {
            if(value == null) throw new IllegalArgumentException("Value for " + key + " cannot be null");
            switch (key){
                case "id_user":
                    int userId =  Integer.parseInt(value.toString());
                    app_user user = repositoryAppUser.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
                    accountUpdate.setId_user(user);
                    break;
                case "id_card":
                    int cardNumberId = Integer.parseInt(value.toString());
                    card_number cardNumber = repositoryCardNumber.findById(cardNumberId)
                            .orElseThrow(() -> new ResourceNotFoundException("Card Number ID not found with ID: " + cardNumberId));
                    accountUpdate.setId_card(cardNumber);
                    break;
                case "mount_account_total":
                    try{
                        float total = Float.parseFloat(value.toString());
                        accountUpdate.setMount_account_total(total);
                    }catch (NumberFormatException e){
                        throw new IllegalArgumentException("Invalid amount format: " + value);
                    }
                    break;
                default:
                    break;
            }
        });
        account accountUpdated = repositoryAccount.save(accountUpdate);
        return mapperAccount.convertToDTO(accountUpdated);
    }
}
