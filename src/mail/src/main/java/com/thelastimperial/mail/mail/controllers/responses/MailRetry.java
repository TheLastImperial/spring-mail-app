package com.thelastimperial.mail.mail.controllers.responses;

import java.util.UUID;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class MailRetry {
    private UUID id;
    private String templateId;
    private String actionId;
    private boolean isRetried;
    private boolean isCanceled;
}
