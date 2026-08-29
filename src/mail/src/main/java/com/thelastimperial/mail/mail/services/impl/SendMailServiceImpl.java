package com.thelastimperial.mail.mail.services.impl;

import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import com.thelastimperial.mail.mail.services.SendMailService;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class SendMailServiceImpl implements SendMailService {
    private final JavaMailSender mailSender;

    @Override
    public void send(
        String to, String subject, String content, Map<String, Object> params
    ) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        Context ctx = new Context();
        ctx.setVariables(params);
        String htmlContent = stringTemplateEngine().process(content, ctx);
        MimeMessageHelper helper = new MimeMessageHelper(
            message, true, "UTF-8"
        );
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

    private TemplateEngine stringTemplateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        engine.addTemplateResolver(resolver);
        return engine;
    }
    
}
