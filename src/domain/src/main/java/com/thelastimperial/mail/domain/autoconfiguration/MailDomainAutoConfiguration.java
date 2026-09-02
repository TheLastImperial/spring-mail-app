package com.thelastimperial.mail.domain.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@EntityScan(basePackages = "com.thelastimperial.mail.domain.entities")
@EnableJpaRepositories(basePackages = "com.thelastimperial.mail.domain.repositories")
public class MailDomainAutoConfiguration {
}
