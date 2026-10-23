package com.thelastimperial.mail.helper.services;

import java.util.function.Consumer;

import com.thelastimperial.mail.helper.requests.MailRequest;

/**
 *
 * ConsumeMailService Interface to consume a mail that need to be send.
*/
public interface ConsumeMailService extends Consumer<MailRequest> {
}
