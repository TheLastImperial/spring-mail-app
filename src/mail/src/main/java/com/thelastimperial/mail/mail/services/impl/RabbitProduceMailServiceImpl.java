package com.thelastimperial.mail.mail.services.impl;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ProduceMailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
@Profile("rabbit")
public class RabbitProduceMailServiceImpl implements ProduceMailService {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Override
    public void accept(MailRequest t) {
        log.debug("MailRequest: {}", t);
        rabbitTemplate.convertAndSend(queue.getName(), t);
    }
    
}
