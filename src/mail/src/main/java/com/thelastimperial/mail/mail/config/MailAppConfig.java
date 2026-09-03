package com.thelastimperial.mail.mail.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thelastimperial.mail.domain.repositories.MailAllowRepository;
import com.thelastimperial.mail.mail.services.AllowSendMailService;
import com.thelastimperial.mail.mail.services.impl.AllowSendMailServiceImpl;
import com.thelastimperial.mail.mail.services.impl.AlwaysAllowSendMailService;

@Configuration
public class MailAppConfig {
    @Bean
    @ConditionalOnProperty(
        name = "com.thelastimperial.mail.mails.blockNotAllows",
        havingValue = "false",
        matchIfMissing = true
    )
    public AllowSendMailService alwayAllowSendMailService() {
        return new AlwaysAllowSendMailService();
    }

    @Bean
    @ConditionalOnProperty(
        name = "com.thelastimperial.mail.mails.blockNotAllows",
        havingValue = "true"
    )
    public AllowSendMailService allowSendMailService(MailAllowRepository mailAllowRepository) {
        return new AllowSendMailServiceImpl(mailAllowRepository);
    }
}
