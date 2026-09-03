package com.thelastimperial.mail.mail.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailAllowEntity;
import com.thelastimperial.mail.domain.repositories.MailAllowRepository;
import com.thelastimperial.mail.mail.services.MailAllowService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailAllowServiceImpl implements MailAllowService {
    private final MailAllowRepository mailAllowRepository;
    private final Pattern emailPattern;

    public MailAllowServiceImpl(
        MailAllowRepository mailAllowRepository,
        @Value("${com.thelastimperial.auth.patterns.email}") String emailStr
    ) {
        this.mailAllowRepository = mailAllowRepository;
        log.info("Pattern: {}", emailStr);
        this.emailPattern = Pattern.compile(emailStr);
    }

    @Override
    public Page<MailAllowEntity> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mailAllowRepository.findAll(pageable);
    }
    @Override
    public List<MailAllowEntity> block(String emails) {
        return create(emails, false);
    }
    @Override
    public List<MailAllowEntity> allow(String emails) {
        return create(emails, true);
    }

    public List<MailAllowEntity> create(String emails, boolean allow) {
        List<MailAllowEntity> emailsToSave = Arrays
            .asList(emails.split(","))
            .stream().filter(em -> {
                return emailPattern.matcher(em).matches();
            })
            .map( em -> {
                return MailAllowEntity.builder()
                    .id(em)
                    .isAllow(allow)
                    .build();
            })
            .collect(Collectors.toList());
        return mailAllowRepository.saveAll(emailsToSave);
    }

}
