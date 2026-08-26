package com.thelastimperial.mail.mail.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class NewMailTemplate {
    private String id;
    private String description;
    private String content;
    private boolean isHtml;
}
