package com.thelastimperial.mail.mail.services.impl;

import java.util.Optional;

import com.thelastimperial.mail.domain.entities.MailAllowEntity;
import com.thelastimperial.mail.domain.repositories.MailAllowRepository;
import com.thelastimperial.mail.mail.services.AllowSendMailService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AllowSendMailServiceImpl implements AllowSendMailService {
    private final MailAllowRepository mailAllowRepository;

    @Override
    public boolean canSend(String email) {
        Optional<MailAllowEntity> allow = mailAllowRepository.findById(email);
        if(allow.isPresent()){
            return allow.get().isAllow();
        }else {
            return false;
        }
    }
}
