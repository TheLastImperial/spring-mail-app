package com.thelastimperial.mail.mail.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelastimperial.mail.domain.entities.MailAllowEntity;
import com.thelastimperial.mail.mail.controllers.requests.NewMailAllow;
import com.thelastimperial.mail.mail.controllers.responses.MailAllow;
import com.thelastimperial.mail.mail.services.MailAllowService;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@Controller
@RequestMapping("/mails-allows")
@Slf4j
public class MailAllowController {
    private final MailAllowService mailAllowService;

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "1") @Min(value = 1) int page,
        @RequestParam(defaultValue = "5") int size,
        Model model
    ) {
        if(page < 1)
            page = 1;
        else
            page = page - 1;
        Page<MailAllowEntity> pageData = mailAllowService.getAll(page, size);
        List<MailAllow> content = pageData.stream().map(ent -> {
            MailAllow resp = new MailAllow();
            BeanUtils.copyProperties(ent, resp);
            return resp;
        }).collect(Collectors.toList());
        model.addAttribute("content", content);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalElements", pageData.getTotalElements());
        model.addAttribute("currentPage", pageData.getNumber());
        model.addAttribute("totalPages", pageData.getTotalPages());
        int start = (page * size) + 1;
        int end = (page + 1) * size;
        if(page == pageData.getTotalPages() - 1){
            end = (int)pageData.getTotalElements();
        }
        model.addAttribute("start", start);
        model.addAttribute("end", end);

        return "allows/index";
    }

    @GetMapping("/allow")
    public String allow(NewMailAllow newMailAllow) {
        return "allows/allow";
    }
    
    @PostMapping("/allow")
    public String allowEmails(NewMailAllow newMailAllow, BindingResult result) {
        if(result.hasErrors()) {
            return "allows/allow";
        }
        log.info("Request: {}", newMailAllow);
        mailAllowService.allow(newMailAllow.getEmails());
        return "redirect:/mails-allows";
    }

    @GetMapping("/block")
    public String block(NewMailAllow newMailAllow) {
        return "allows/block";
    }

    @PostMapping("/block")
    public String blockEmails(NewMailAllow newMailAllow, BindingResult result) {
        if(result.hasErrors()){
            return "allows/block";
        }
        mailAllowService.block(newMailAllow.getEmails());
        return "redirect:/mails-allows";
    }
    
    
    
}
