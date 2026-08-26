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

@AllArgsConstructor
@Service
public class MailTemplateServiceImpl implements MailTemplateService {
    private final MailTemplateRepository mailTemplateRepository;

    @Override
    public Page<MailTemplateEntity> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
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
        Optional<MailTemplateEntity> toEditOpt = mailTemplateRepository.findById(id);
        if(toEditOpt.isEmpty()){
            return Optional.empty();
        }
        MailTemplateEntity toEdit = toEditOpt.get();
        BeanUtils.copyProperties(mailTemplateEntity, toEdit);
        MailTemplateEntity updated = mailTemplateRepository.save(toEdit);
        return Optional.of(updated);
    }

    @Override
    public void delete(String id) {
        mailTemplateRepository.deleteById(id);
    }
    
}
