package com.thelastimperial.mail.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thelastimperial.mail.domain.entities.MailAllowEntity;

public interface MailAllowRepository extends JpaRepository<MailAllowEntity, String>{
    public Page<MailAllowEntity> findAll(Pageable pageable);
}
