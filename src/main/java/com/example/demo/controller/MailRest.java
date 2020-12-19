package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MailData;
import com.example.demo.service.MailSenderService;


@RestController
@RequestMapping(value = "/mail")
public class MailRest {

    @Autowired
    MailSenderService usuarioSrv;
    
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PostMapping
    public void mail(@RequestBody MailData data) {
        usuarioSrv.sendMail(data);
    }
}
