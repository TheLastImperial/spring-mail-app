package com.thelastimperial.mail.mail.services.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ConsumeMailService;
import com.thelastimperial.mail.mail.services.HandlerConsumeMailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
@Profile("kafka")
public class KafkaConsumeMailServiceImpl implements ConsumeMailService{
    private final HandlerConsumeMailService handlerConsumeMailService;

    @Override
    @KafkaListener(
        topics = "${spring.kafka.topic.name}",
        properties={
            "spring.json.value.default.type=com.thelastimperial.mail.helper.requests.MailRequest"
        }
    )
    public void accept(MailRequest t) {
        log.debug("Kafka received Request: {}", t);
        handlerConsumeMailService.accept(t);
    }
    
}
