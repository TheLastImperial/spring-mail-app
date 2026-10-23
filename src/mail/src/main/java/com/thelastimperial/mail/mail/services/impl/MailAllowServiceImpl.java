package com.thelastimperial.mail.mail.services.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailAllowAuditEntity;
import com.thelastimperial.mail.domain.entities.MailAllowEntity;
import com.thelastimperial.mail.domain.repositories.MailAllowAuditRepository;
import com.thelastimperial.mail.domain.repositories.MailAllowRepository;
import com.thelastimperial.mail.mail.services.MailAllowService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailAllowServiceImpl implements MailAllowService {
    private final MailAllowRepository mailAllowRepository;
    private final MailAllowAuditRepository mailAllowAuditRepository;
    private final Pattern emailPattern;

    public MailAllowServiceImpl(
        MailAllowRepository mailAllowRepository,
        MailAllowAuditRepository mailAllowAuditRepository,
        @Value("${com.thelastimperial.auth.patterns.email}") String emailStr
    ) {
        this.mailAllowRepository = mailAllowRepository;
        this.mailAllowAuditRepository = mailAllowAuditRepository;
        this.emailPattern = Pattern.compile(emailStr);
    }

    @Override
    public Page<MailAllowEntity> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mailAllowRepository.findAll(pageable);
    }
    @Override
    public List<MailAllowEntity> block(String emails, Principal principal) {
        return create(emails, false, principal);
    }
    @Override
    public List<MailAllowEntity> allow(String emails, Principal principal) {
        return create(emails, true, principal);
    }

    public List<MailAllowEntity> create(String emails, boolean allow, Principal principal) {
        List<MailAllowEntity> emailsToSave = new ArrayList<>();
        List<MailAllowAuditEntity> auditsToSave = new ArrayList<>();
        Arrays
            .asList(emails.split(","))
            .stream().filter(em -> {
                return emailPattern.matcher(em).matches();
            })
            .forEach(mail -> {
                emailsToSave.add(MailAllowEntity.builder()
                    .id(mail)
                    .isAllow(allow)
                    .build()
                );
                auditsToSave.add(
                  MailAllowAuditEntity.builder()
                  .email(mail)
                  .isAllow(allow)
                  .updatedBy(UUID.fromString(principal.getName()))
                  .build()  
                );
            });
        mailAllowAuditRepository.saveAll(auditsToSave);
        return mailAllowRepository.saveAll(emailsToSave);
    }

}
