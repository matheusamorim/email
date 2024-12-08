package br.com.nimitz.email.clients;

import br.com.nimitz.email.controller.dtos.EmailRequest;
import org.springframework.stereotype.Component;

@Component
public interface IEmailProvider {
    String getProvider();
    void emailFactory(EmailRequest emailRequest);
}
