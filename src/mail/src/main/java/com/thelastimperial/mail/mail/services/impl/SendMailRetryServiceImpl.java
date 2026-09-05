package com.thelastimperial.mail.mail.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailRetryEntity;
import com.thelastimperial.mail.domain.repositories.MailRetryRepository;
import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ProduceMailService;
import com.thelastimperial.mail.mail.services.SendMailRetryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SendMailRetryServiceImpl implements SendMailRetryService {
    private final ProduceMailService produceMailService;
    private final MailRetryRepository mailRetryRepository;

    @Override
    public void retryAll() {
        mailRetryRepository.findByRetriedAndCanceled(false, false)
            .stream().forEach(ele -> {
                MailRequest mr = MailRequest
                    .builder()
                    .to(ele.getSendTo())
                    .params(ele.getParams())
                    .build();
                produceMailService.accept(mr);
                ele.setRetried(true);
                mailRetryRepository.save(ele);                
            });
    }

    @Override
    public void retryById(UUID id) {
        Optional<MailRetryEntity> mailRetry = mailRetryRepository.findById(id);
        mailRetry.ifPresent(ele -> {
            if(ele.isRetried())
                return;
            MailRequest mr = MailRequest
                .builder()
                .to(ele.getSendTo())
                .templateId(ele.getTemplateId())
                .params(ele.getParams())
                .build();
            produceMailService.accept(mr);
            ele.setRetried(true);
            mailRetryRepository.save(ele);
        });
    }

    @Override
    public void retryByTemplate(String templateId) {
        mailRetryRepository.findByTemplateId(templateId)
            .stream().forEach(ele -> {
                MailRequest mr = MailRequest
                    .builder()
                    .to(ele.getSendTo())
                    .params(ele.getParams())
                    .build();
                produceMailService.accept(mr);
                ele.setRetried(true);
                mailRetryRepository.save(ele);                
            });
    }

    @Override
    public void retryByActionId(String actionId) {
        mailRetryRepository.findByActionIdAndIsRetriedAndIsCanceled(
            actionId, false, false
        ).stream().forEach(ele -> {
                MailRequest mr = MailRequest
                    .builder()
                    .to(ele.getSendTo())
                    .params(ele.getParams())
                    .build();
                produceMailService.accept(mr);
                ele.setRetried(true);
                mailRetryRepository.save(ele); 
        });;
    }
}
