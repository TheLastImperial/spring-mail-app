package com.thelastimperial.mail.mail.services.impl.notifications;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.thelastimperial.auth.auth.services.NotificationService;
import com.thelastimperial.auth.domain.entities.UserEntity;
import com.thelastimperial.auth.domain.entities.UserRecoveryEntity;
import com.thelastimperial.mail.helper.requests.MailRequest;
import com.thelastimperial.mail.helper.services.ProduceMailService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RecoveryNotificationService implements NotificationService<UserRecoveryEntity> {
    private final ProduceMailService produceMailService;
    private final String TEMPLATE_ID = "RECOVERY_ACCOUNT";
    @Override
    public void send(UserRecoveryEntity recovery) {

        UserEntity user = recovery.getUser();

        HashMap<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("url", "/auth/new-password/" + recovery.getId());

        MailRequest mailRequest = MailRequest.builder()
        // The username have the Email
        .to(user.getUsername())
        .subject("Activation Account.")
        .templateId(TEMPLATE_ID)
        .params(params)
        .build();
        produceMailService.accept(mailRequest);
    }
    
}
