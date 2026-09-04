package com.thelastimperial.mail.mail.services.impl;

import com.thelastimperial.mail.mail.services.AllowSendMailService;

public class AlwaysAllowSendMailService implements AllowSendMailService {
    @Override
    public boolean canSend(String email) {
        return true;
    }
}
