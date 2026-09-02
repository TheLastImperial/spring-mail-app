package com.thelastimperial.mail.mail.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.thelastimperial.mail.domain.entities.MailTemplateEntity;

public interface MailTemplateService {
    public Page<MailTemplateEntity> getAll(int page, int size);
    public MailTemplateEntity create(MailTemplateEntity mailTemplateEntity);
    public Optional<MailTemplateEntity> get(String id);
    public Optional<MailTemplateEntity> update(String id, MailTemplateEntity mailTemplateEntity);
    public void delete(String id);
}
