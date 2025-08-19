package com.securebank.securenank.Repository;

import com.securebank.securenank.Model.app_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAppUser extends JpaRepository<app_user, Integer> {

    @Query(value = "SELECT * FROM app_user WHERE username = :username;",nativeQuery = true)
    app_user findByUsername(String username);
}
