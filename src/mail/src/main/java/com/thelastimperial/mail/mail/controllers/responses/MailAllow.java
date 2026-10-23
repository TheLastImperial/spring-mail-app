package com.thelastimperial.mail.mail.controllers.responses;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class MailAllow {
    private String id;
    private boolean isAllow;
}
