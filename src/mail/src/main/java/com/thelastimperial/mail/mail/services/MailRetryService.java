package com.thelastimperial.mail.mail.services;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.thelastimperial.mail.domain.entities.MailRetryEntity;
import com.thelastimperial.mail.helper.requests.MailRequestWrapper;

public interface MailRetryService {
    public void save(MailRequestWrapper mrw);
    public Page<MailRetryEntity> getAll(int page, int size);
    public void retry(UUID id);
    public void cancel(UUID id);
}
