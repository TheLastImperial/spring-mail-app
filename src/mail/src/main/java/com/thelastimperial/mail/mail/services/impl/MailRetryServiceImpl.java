package com.thelastimperial.mail.mail.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailRetryEntity;
import com.thelastimperial.mail.domain.repositories.MailRetryRepository;
import com.thelastimperial.mail.helper.requests.MailRequestWrapper;
import com.thelastimperial.mail.mail.services.MailRetryService;
import com.thelastimperial.mail.mail.services.SendMailRetryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MailRetryServiceImpl implements MailRetryService {
    private final MailRetryRepository mailRetryRepository;
    private final SendMailRetryService sendMailRetryService;

    @Override
    public void save(MailRequestWrapper mrw) {
        MailRetryEntity mailRetry = MailRetryEntity.builder()
            .sendTo(mrw.getMailRequest().getTo())
            .templateId(mrw.getMailRequest().getTemplateId())
            .isRetried(false)
            .isCanceled(false)
            .params(mrw.getMailRequest().getParams())
            .actionId(mrw.getActionId())
            .build();
        mailRetryRepository.save(mailRetry);
    }

    @Override
    public Page<MailRetryEntity> getAll(int page, int size) {
        return mailRetryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void retry(UUID id) {
        sendMailRetryService.retryById(id);
    }

    @Override
    public void cancel(UUID id) {
        mailRetryRepository.findById(id).ifPresent(mr -> {
            if(!mr.isCanceled() && !mr.isRetried()){
                mr.setCanceled(true);
                mailRetryRepository.save(mr);
            }
        });
    }

}
