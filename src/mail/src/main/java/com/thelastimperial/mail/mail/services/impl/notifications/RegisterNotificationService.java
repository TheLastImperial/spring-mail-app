package com.thelastimperial.mail.mail.services.impl.notifications;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.thelastimperial.auth.auth.services.NotificationService;
import com.thelastimperial.auth.domain.entities.UserActivationEntity;
import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ProduceMailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RegisterNotificationService implements NotificationService<UserActivationEntity> {
    private final ProduceMailService produceMailService;
    private final String TEMPLATE_ID = "ACTIVATE_ACCOUNT";

    @Override
    public void send(UserActivationEntity activation) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "/auth/activation/" + activation.getId());

        MailRequest mailRequest = MailRequest.builder()
        // The username have the Email
        .to(activation.getUser().getUsername())
        .subject("Activation Account.")
        .templateId(TEMPLATE_ID)
        .params(params)
        .build();

        produceMailService.accept(mailRequest);
    }
    
}
