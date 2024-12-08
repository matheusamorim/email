package br.com.nimitz.email.controller;

import br.com.nimitz.email.controller.dtos.EmailRequest;
import br.com.nimitz.email.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity login(@Valid @RequestBody EmailRequest request){
        emailService.sendEmail(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
