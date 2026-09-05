package com.thelastimperial.mail.mail.services;

import java.util.UUID;

public interface SendMailRetryService {
    public void retryAll();
    public void retryById(UUID id);
    public void retryByTemplate(String templateId);
    public void retryByActionId(String actionId);
}
