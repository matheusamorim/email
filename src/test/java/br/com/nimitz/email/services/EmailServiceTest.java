package br.com.nimitz.email.services;

import br.com.nimitz.email.clients.IEmailProvider;
import br.com.nimitz.email.controller.dtos.EmailRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmailServiceTest {

    private EmailService emailService;
    private IEmailProvider emailAWSProvider;
    private IEmailProvider emailOCIProvider;

    @BeforeEach
    void setUp() {
        emailAWSProvider = mock(IEmailProvider.class);
        emailOCIProvider = mock(IEmailProvider.class);

        when(emailAWSProvider.getProvider()).thenReturn("AWS");
        when(emailOCIProvider.getProvider()).thenReturn("OCI");

        emailService = new EmailService(List.of(emailAWSProvider, emailOCIProvider));
    }

    @Test
    void shouldSendEmailUsingAWSProvider() {
        EmailRequest emailRequest = buildEmailRequest();
        ReflectionTestUtils.setField(emailService, "provider", "AWS");

        emailService.sendEmail(emailRequest);

        verify(emailAWSProvider, times(1)).emailFactory(emailRequest);
        verify(emailOCIProvider, never()).emailFactory(any());
    }

    @Test
    void shouldSendEmailUsingOCIProvider() {
        EmailRequest emailRequest = buildEmailRequest();
        ReflectionTestUtils.setField(emailService, "provider", "OCI");

        emailService.sendEmail(emailRequest);

        verify(emailOCIProvider, times(1)).emailFactory(emailRequest);
        verify(emailAWSProvider, never()).emailFactory(any());
    }

    private EmailRequest buildEmailRequest(){
        return new EmailRequest(
                "destinatario@gmail.com",
                "nome do destinatario",
                "",
                "Assunto",
                "Conteúdo");
    }
}
