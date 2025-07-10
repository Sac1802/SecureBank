package com.securebank.securenank.Mapper;

import com.securebank.securenank.DTO.transferDTO;
import com.securebank.securenank.Model.account;
import com.securebank.securenank.Model.transfer;
import com.securebank.securenank.Repository.RepositoryAccount;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MapperTransfer {

    private static RepositoryAccount repositoryAccount;

    public transferDTO convertToDTO(transfer transfer){
        transferDTO transferDTO  = new transferDTO();
        transferDTO.id_transfer  = transfer.getId_transfer();
        transferDTO.from_account =  transfer.getFrom_account().getId_account();
        transferDTO.to_account = transfer.getTo_account().getId_account();
        transferDTO.amount = transfer.getAmount();
        return transferDTO;
    }

    public transfer convertToTransfer(transferDTO transferDTO){
        Optional<account> fromAccount = repositoryAccount.findById(transferDTO.from_account);
        Optional<account> toAccount = repositoryAccount.findById(transferDTO.to_account);
        if(fromAccount.isPresent() && toAccount.isPresent()){
            return new transfer(transferDTO.id_transfer, fromAccount.get(), toAccount.get(), transferDTO.amount);
        }
        return new transfer();
    }
}
