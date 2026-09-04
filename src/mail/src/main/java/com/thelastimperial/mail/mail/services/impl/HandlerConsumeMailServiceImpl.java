package com.thelastimperial.mail.mail.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thelastimperial.mail.domain.entities.MailTemplateEntity;
import com.thelastimperial.mail.domain.repositories.MailTemplateRepository;
import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.requests.MailRequestWrapper;
import com.thelastimperial.mail.mail.services.AllowSendMailService;
import com.thelastimperial.mail.mail.services.HandlerConsumeMailService;
import com.thelastimperial.mail.mail.services.MailRetryService;
import com.thelastimperial.mail.mail.services.SendMailService;
import com.thelastimperial.utils.services.AuditService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class HandlerConsumeMailServiceImpl implements HandlerConsumeMailService {
    private final MailTemplateRepository mailTemplateRepository;
    private final SendMailService sendMailService;
    private final AllowSendMailService allowSendMailService;
    private final MailRetryService mailRetryService;
    private final AuditService<MailRequestWrapper> mailAuditService;

    @Override
    public void accept(MailRequest t) {
        MailRequestWrapper mrw = MailRequestWrapper.builder()
            .mailRequest(t)
            .isSended(true)
            .actionId("EMAIL_SEND")
            .build();
        if(!allowSendMailService.canSend(t.getTo())){
            mrw.setSended(false);
            mrw.setActionId("EMAIL_NOT_ALLOW");
            mailAuditService.save(mrw);
            mailRetryService.save(mrw);
            return;
        }
        Optional<MailTemplateEntity> templateOpt = mailTemplateRepository
            .findById(t.getTemplateId());
        if(templateOpt.isEmpty()){
            log.error("Template Id doesn't exists: {}", t.getTemplateId());
            mrw.setSended(false);
            mrw.setActionId("TEMPLATE_NOT_FOUND");
            mailAuditService.save(mrw);
            mailRetryService.save(mrw);
            return;
        }
        MailTemplateEntity template = templateOpt.get();
        log.debug("TemplateId: {}", template.getId());
        log.debug("Subject: {}", template.getSubject());
        log.debug("Params: {}", t.getParams());
        try{
            sendMailService.send(t.getTo(), template.getSubject(), template.getContent(), t.getParams());
        }catch(Exception e){
            mrw.setSended(false);
            mrw.setActionId("TEMPLATE_ERROR");
            mrw.setComment(e.getClass().getName());
            mailRetryService.save(mrw);
        }
        mailAuditService.save(mrw);
    }
    
}
