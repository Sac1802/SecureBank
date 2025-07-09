package com.securebank.securenank.Repository;

import com.securebank.securenank.Model.direction_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDirectionUser extends JpaRepository<direction_user, Integer> {
}
