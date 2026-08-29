package com.thelastimperial.mail.mail.services;

import java.util.Map;

public interface SendMailService {
    public void send(String to, String subject, String content, Map<String, Object> params);
}
