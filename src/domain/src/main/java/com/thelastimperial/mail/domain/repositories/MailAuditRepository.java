package com.thelastimperial.mail.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thelastimperial.mail.domain.entities.MailAuditEntity;

public interface MailAuditRepository extends JpaRepository<MailAuditEntity, UUID>{
}
