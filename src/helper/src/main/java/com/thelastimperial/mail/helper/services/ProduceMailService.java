package com.thelastimperial.mail.helper.services;

import java.util.function.Consumer;

import com.thelastimperial.mail.helper.requests.MailRequest;

/**
 *
 * ProduceMailService interface to produce a new mail.
*/
public interface ProduceMailService extends Consumer<MailRequest>{
}
