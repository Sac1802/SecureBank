package com.securebank.securenank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.securebank.securenank.Model.audit_log;

@Repository
public interface RepositoryAuditLog extends JpaRepository<audit_log, Integer> {
}
