package com.securebank.securenank.Repository;

import com.securebank.securenank.Model.transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTransfer extends JpaRepository<transfer, Integer> {
}
