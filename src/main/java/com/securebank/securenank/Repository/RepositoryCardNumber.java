package com.securebank.securenank.Repository;

import com.securebank.securenank.Model.card_number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCardNumber extends JpaRepository<card_number, Integer> {
}
