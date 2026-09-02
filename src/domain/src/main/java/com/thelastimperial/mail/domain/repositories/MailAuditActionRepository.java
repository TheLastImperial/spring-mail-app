package com.thelastimperial.mail.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thelastimperial.mail.domain.entities.MailAuditActionEntity;

public interface MailAuditActionRepository extends JpaRepository<MailAuditActionEntity, String>{
}
