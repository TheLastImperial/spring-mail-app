package com.thelastimperial.mail.mail.services.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ConsumeMailService;
import com.thelastimperial.mail.mail.services.HandlerConsumeMailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
@Profile("rabbit")
public class RabbitConsumeMailServiceImpl implements ConsumeMailService {
    private final HandlerConsumeMailService handlerConsumeMailService;

    @RabbitListener(queues = "${com.thelastimperial.mail.mq.queue}")
    @Override
    public void accept(MailRequest t) {
        log.debug("Rabbit Received request to send Mail: {}", t.getTemplateId());
        handlerConsumeMailService.accept(t);
    }
}
