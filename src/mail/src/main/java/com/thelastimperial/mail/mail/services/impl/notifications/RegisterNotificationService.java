package com.thelastimperial.mail.mail.services.impl.notifications;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thelastimperial.auth.auth.services.NotificationService;
import com.thelastimperial.auth.domain.entities.UserActivationEntity;
import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ProduceMailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegisterNotificationService implements NotificationService<UserActivationEntity> {
    private final ProduceMailService produceMailService;
    private final String serverAddress;
    private final String TEMPLATE_ID = "ACTIVATE_ACCOUNT";

    public RegisterNotificationService(
        ProduceMailService produceMailService,
        @Value("${com.thelastimperial.mail.server.address}") String serverAddress
    ) {
        this.produceMailService = produceMailService;
        this.serverAddress = serverAddress;
    }

    @Override
    public void send(UserActivationEntity activation) {

        HashMap<String, Object> params = new HashMap<>();
        params.put(
            "url",
            serverAddress + "/auth/activation/" + activation.getId()
        );

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
