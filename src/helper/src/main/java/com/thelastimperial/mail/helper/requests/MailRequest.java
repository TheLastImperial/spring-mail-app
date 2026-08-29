package com.thelastimperial.mail.helper.requests;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class MailRequest {
    private String to;
    private String subject;
    private String templateId;
    private Map<String, Object> params;
}
