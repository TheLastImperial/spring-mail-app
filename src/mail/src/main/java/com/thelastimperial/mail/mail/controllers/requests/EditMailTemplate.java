package com.thelastimperial.mail.mail.controllers.requests;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EditMailTemplate {
    private String id;
    private String description;
    private String content;
    private boolean isHtml;
}
