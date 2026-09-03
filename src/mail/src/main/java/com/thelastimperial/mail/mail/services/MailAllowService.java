package com.thelastimperial.mail.mail.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.thelastimperial.mail.domain.entities.MailAllowEntity;

public interface MailAllowService {
    public Page<MailAllowEntity> getAll(int page, int size);
    public List<MailAllowEntity> block(String emails);
    public List<MailAllowEntity> allow(String emails);
}
