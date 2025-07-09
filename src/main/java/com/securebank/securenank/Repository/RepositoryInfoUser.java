package com.securebank.securenank.Repository;

import com.securebank.securenank.Model.info_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryInfoUser extends JpaRepository<info_user, Integer> {
}
