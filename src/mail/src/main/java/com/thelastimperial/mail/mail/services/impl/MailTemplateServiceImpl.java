package com.thelastimperial.mail.mail.services.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailTemplateEntity;
import com.thelastimperial.mail.domain.repositories.MailTemplateRepository;
import com.thelastimperial.mail.mail.services.MailTemplateService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class MailTemplateServiceImpl implements MailTemplateService {
    private final MailTemplateRepository mailTemplateRepository;

    @Override
    public Page<MailTemplateEntity> getAll(int page, int size) {
        if(page <= 1)
            page = 1;
        Pageable pageable = PageRequest.of(page - 1, size);
        return mailTemplateRepository.findAll(pageable);
    }

    @Override
    public MailTemplateEntity create(MailTemplateEntity mailTemplateEntity) {
        return mailTemplateRepository.save(mailTemplateEntity);
    }

    @Override
    public Optional<MailTemplateEntity> get(String id) {
        return mailTemplateRepository.findById(id);
    }

    @Override
    public Optional<MailTemplateEntity> update(String id, MailTemplateEntity mailTemplateEntity) {
        Optional<MailTemplateEntity> toEdit = mailTemplateRepository.findById(id);
        BeanUtils.copyProperties(mailTemplateEntity, toEdit);
        if(toEdit.isPresent()){
            log.info("MailTemplate to edit: {}", toEdit.get());
            MailTemplateEntity updated = mailTemplateRepository.save(toEdit.get());
            return Optional.of(updated);
        }
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        mailTemplateRepository.deleteById(id);
    }
    
}
