package com.thelastimperial.mail.domain.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thelastimperial.mail.domain.entities.MailRetryEntity;

public interface MailRetryRepository extends JpaRepository<MailRetryEntity, UUID> {
    public List<MailRetryEntity> findByTemplateId(String templateId);
    public List<MailRetryEntity> findByRetriedAndCanceled(boolean retried, boolean canceled);
    public Page<MailRetryEntity> findAll(Pageable page);
}
