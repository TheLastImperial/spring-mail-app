package com.thelastimperial.mail.mail.services.impl.notifications;

import org.springframework.stereotype.Service;

import com.thelastimperial.auth.auth.services.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NewPasswordNotificationService implements NotificationService<Object> {
    @Override
    public void send(Object rq) {
        log.info("New Password Notification don't needed");
    }
}
