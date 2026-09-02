package com.thelastimperial.mail.helper.services;

import java.util.function.Consumer;

import com.thelastimperial.mail.helper.requests.MailRequest;

public interface ConsumeMailService extends Consumer<MailRequest> {
}
