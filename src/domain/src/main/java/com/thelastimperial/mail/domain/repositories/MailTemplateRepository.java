package com.thelastimperial.mail.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thelastimperial.mail.domain.entities.MailTemplateEntity;

public interface MailTemplateRepository extends JpaRepository<MailTemplateEntity, String>{
    public Page<MailTemplateEntity> findAll(Pageable pageable);
}
