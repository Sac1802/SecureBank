package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.history_transactionDTO;
import com.securebank.securenank.Model.Account;
import com.securebank.securenank.Model.history_transaction;
import com.securebank.securenank.Repository.RepositoryAccount;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperHistoryTransaction {

    private static RepositoryAccount repositoryAccount;

    public history_transactionDTO convertToDTO(history_transaction history){
        history_transactionDTO historyDTO = new history_transactionDTO();
        historyDTO.id_history = history.getId_history();
        historyDTO.datetime = history.getDatetime();
        historyDTO.account  = history.getAccount().getId_account();
        historyDTO.amount = history.getAmount();
        return historyDTO;
    }

    public history_transaction convertToHistory(history_transactionDTO historyDTO){
        Optional<Account> account = repositoryAccount.findById(historyDTO.account);
        if(account.isPresent()){
            return new history_transaction(historyDTO.id_history, historyDTO.datetime,
                    account.get(), historyDTO.amount);
        }
        return new history_transaction();
    }
}
