package com.thelastimperial.mail.mail.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.thelastimperial.mail.domain.entities.MailTemplateEntity;
import com.thelastimperial.mail.mail.controllers.requests.EditMailTemplate;
import com.thelastimperial.mail.mail.controllers.requests.NewMailTemplate;
import com.thelastimperial.mail.mail.controllers.responses.MailTemplate;
import com.thelastimperial.mail.mail.services.MailTemplateService;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/mails")
@Slf4j
public class MailTemplateController {
    private final MailTemplateService mailTemplateService;

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "1") @Min(value = 1) int page,
        @RequestParam(defaultValue = "5") int size,
        Model model
    ) {
        log.debug("Request Index");
        if(page < 1)
            page = 1;
        else
            page = page - 1;

        Page<MailTemplateEntity> pageData = mailTemplateService.getAll(page, size);
        List<MailTemplate> content = pageData.stream().map(ent -> {
            MailTemplate resp = new MailTemplate();
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

        return "mails/index";
    }
    
    @GetMapping("/new")
    public String newMail(NewMailTemplate newMailTemplate) {
        log.debug("Request Get New Mail Template");
        return "mails/new";
    }
    
    @PostMapping("/create")
    public String create(NewMailTemplate newMailTemplate, BindingResult result) {
        log.debug("Request Post New Mail Template");
        if(result.hasErrors()){
            return "mails/new";
        }
        MailTemplateEntity toSave = new MailTemplateEntity();
        BeanUtils.copyProperties(newMailTemplate, toSave);
        toSave.setId(toSave.getId().toUpperCase().replace(" ", "_"));
        MailTemplateEntity saved = mailTemplateService.create(toSave);
        return "redirect:/mails/show/" + saved.getId();
    }
    
    @GetMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        log.debug("Request show Mail Template");
        MailTemplateEntity response = mailTemplateService.get(id)
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("mail", response);
        return "mails/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(EditMailTemplate editMailTemplate, @PathVariable String id) {
        log.debug("Request Edit Mail Template");
        MailTemplateEntity response = mailTemplateService.get(id)
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BeanUtils.copyProperties(response, editMailTemplate);
        return "mails/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, EditMailTemplate editMailTemplate, BindingResult result) {
        log.debug("Request update Mail Template");
        if(result.hasErrors()){
            return "mails/edit";
        }
        MailTemplateEntity toEdit = new MailTemplateEntity();
        BeanUtils.copyProperties(editMailTemplate, toEdit);
        MailTemplateEntity updated = mailTemplateService.update(id, toEdit)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return "redirect:/mails/show/" + updated.getId();
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        log.debug("Request Delete Mail Template");
        mailTemplateService.delete(id);
        return "redirect:/mails";
    }

}
