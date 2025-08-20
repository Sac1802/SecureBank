package com.securebank.securenank.Repository;

import com.securebank.securenank.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAccount extends JpaRepository<Account, Integer> {
}
