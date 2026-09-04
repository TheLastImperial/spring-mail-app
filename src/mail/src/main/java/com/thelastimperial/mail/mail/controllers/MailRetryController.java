package com.thelastimperial.mail.mail.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelastimperial.mail.domain.entities.MailRetryEntity;
import com.thelastimperial.mail.mail.controllers.responses.MailRetry;
import com.thelastimperial.mail.mail.services.MailRetryService;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
@RequestMapping("/mails/retries")
public class MailRetryController {
    private final MailRetryService mailRetryService;

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
        Page<MailRetryEntity> pageData = mailRetryService.getAll(page, size);
        List<MailRetry> content = pageData.stream().map(ent -> {
            MailRetry resp = new MailRetry();
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

        return "retries/index";
    }
    @GetMapping("/retry/{id}")
    public String retry(@PathVariable UUID id) {
        mailRetryService.retry(id);
        return "redirect:/mails/retries";
    }
    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable UUID id) {
        mailRetryService.cancel(id);
        return "redirect:/mails/retries";
    }
    
    
}
