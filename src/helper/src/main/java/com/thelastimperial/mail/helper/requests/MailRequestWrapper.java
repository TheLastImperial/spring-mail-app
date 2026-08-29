package com.thelastimperial.mail.helper.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
