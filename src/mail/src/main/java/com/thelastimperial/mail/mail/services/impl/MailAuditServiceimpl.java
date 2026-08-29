package com.thelastimperial.mail.mail.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailAuditActionEntity;
import com.thelastimperial.mail.domain.entities.MailAuditEntity;
import com.thelastimperial.mail.domain.repositories.MailAuditActionRepository;
import com.thelastimperial.mail.domain.repositories.MailAuditRepository;
import com.thelastimperial.mail.helper.requests.MailRequestWrapper;
import com.thelastimperial.utils.services.AuditService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class MailAuditServiceimpl implements AuditService<MailRequestWrapper> {
    private final MailAuditRepository mailAuditRepository;
    private final MailAuditActionRepository mailAuditActionRepository;

    @Override
    public void save(MailRequestWrapper t) {
        Optional<MailAuditActionEntity> action = mailAuditActionRepository
            .findById(t.getActionId());

        MailAuditEntity audit = MailAuditEntity.builder()
        .sendTo(t.getMailRequest().getTo())
        .mailTemplateId(t.getMailRequest().getTemplateId())
        .isSended(t.isSended())
        .action(action.get())
        .comment(t.getComment())
        .build();

        mailAuditRepository.save(audit);
    }
}
