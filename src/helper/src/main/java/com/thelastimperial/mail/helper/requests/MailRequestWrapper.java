package com.thelastimperial.mail.helper.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * MailRequestWrapper Wrapper a mail request and extra data to resend if is needed.
*/
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class MailRequestWrapper {
    private MailRequest mailRequest;
    private boolean isSended;
    private String actionId;
    private String comment;
}
