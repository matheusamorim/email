package br.com.nimitz.email.services;

import br.com.nimitz.email.clients.IEmailProvider;
import br.com.nimitz.email.controller.dtos.EmailRequest;
import br.com.nimitz.email.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Value("${mail.integracao}")
    String provider;

    private final Map<String, IEmailProvider> emailProviders;

    public EmailService(List<IEmailProvider> providers) {
       this.emailProviders = providers.stream()
                .collect(Collectors.toMap(IEmailProvider::getProvider, emailProvider -> emailProvider));
    }

    public void sendEmail(EmailRequest emailRequest) {
        IEmailProvider emailProvider = emailProviders.get(provider);

        if (emailProvider == null) {
            throw new BusinessException("Provedor de email não é suportado", HttpStatus.NOT_IMPLEMENTED);
        }

        emailProvider.emailFactory(emailRequest);
    }
}
