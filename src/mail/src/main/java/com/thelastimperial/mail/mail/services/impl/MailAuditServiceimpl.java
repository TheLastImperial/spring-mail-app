package com.thelastimperial.mail.mail.services.impl;

import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailAuditEntity;
import com.thelastimperial.mail.domain.repositories.MailAuditRepository;
import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.utils.services.AuditService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class MailAuditServiceimpl implements AuditService<MailRequest> {
    private final MailAuditRepository mailAuditRepository;

    @Override
    public void save(MailRequest t) {
        MailAuditEntity audit = MailAuditEntity.builder()
        .sendTo(t.getTo())
        .mailTemplateId(t.getTemplateId())
        .build();

        mailAuditRepository.save(audit);
    }
}
