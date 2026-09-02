package com.thelastimperial.mail.mail.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ProduceMailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Profile("kafka")
public class KafkaProduceMailServiceImpl implements ProduceMailService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topic;
    
    public KafkaProduceMailServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, 
        @Value("${spring.kafka.topic.name}")
        String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void accept(MailRequest t) {
        log.info("Send Message: {}", t);
        kafkaTemplate.send(topic, t);
    }
    
}
