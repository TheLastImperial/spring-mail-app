package com.thelastimperial.mail.mail.services.impl;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailTemplateEntity;
import com.thelastimperial.mail.domain.repositories.MailTemplateRepository;
import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ConsumeMailService;
import com.thelastimperial.mail.mail.services.SendMailService;
import com.thelastimperial.utils.services.AuditService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ConsumeMailServiceImpl implements ConsumeMailService {
    private final MailTemplateRepository mailTemplateRepository;
    private final SendMailService sendMailService;
    private final AuditService<MailRequest> mailAuditService;

    @RabbitListener(queues = "${com.thelastimperial.mail.mq.queue}")
    @Override
    public void accept(MailRequest t) {
        log.debug("Received request to send Mail: {}", t.getTemplateId());
        Optional<MailTemplateEntity> templateOpt = mailTemplateRepository
            .findById(t.getTemplateId());
        if(templateOpt.isEmpty()){
            log.error("Template Id doesn't exists: {}", t.getTemplateId());
            return;
        }
        MailTemplateEntity template = templateOpt.get();
        sendMailService.send(t.getTo(), t.getSubject(), template.getContent(), t.getParams());
        mailAuditService.save(t);
    }
    
}
