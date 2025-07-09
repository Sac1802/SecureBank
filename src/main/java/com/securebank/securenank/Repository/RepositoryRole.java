package com.securebank.securenank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.securebank.securenank.Model.role;

@Repository
public interface RepositoryRole extends JpaRepository<role, Integer> {
}
