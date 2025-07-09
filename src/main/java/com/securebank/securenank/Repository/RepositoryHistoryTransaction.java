package com.securebank.securenank.Repository;

import com.securebank.securenank.Model.history_transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryHistoryTransaction extends JpaRepository<history_transaction, Integer> {
}
