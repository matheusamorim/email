package br.com.nimitz.email.validators;

import br.com.nimitz.email.clients.aws.EmailAwsDTO;
import br.com.nimitz.email.clients.oci.EmailOciDTO;
import br.com.nimitz.email.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class Validator {
    public void emailOcivalidate(EmailOciDTO dto) {
        StringBuilder errors = new StringBuilder();

        if (dto.recipientEmail() != null && dto.recipientEmail().length() > 40) {
            errors.append("E-mail do destinatário pode ter no máximo 40 caracteres.");
        }

        if (dto.recipientName() != null && dto.recipientName().length() > 50) {
            errors.append("Nome do destinatário pode ter no máximo 50 caracteres");
        }

        if (dto.senderEmail() != null && dto.senderEmail().length() > 40) {
            errors.append("E-mail do remetente pode ter no máximo 40 caracteres");
        }

        if (dto.subject() != null && dto.subject().length() > 100) {
            errors.append("Assunto pode ter no máximo 100 caracteres.");
        }

        if (dto.body() != null && dto.body().length() > 250) {
            errors.append("Conteúdo do e-mail pode ter no máximo 250 caracteres.");
        }

        if (errors.length() > 0) {
            throw new BusinessException("Erros de validação: " + errors, HttpStatus.BAD_REQUEST);
        }
    }

    public void emailAwsValidate(EmailAwsDTO dto) {
        StringBuilder errors = new StringBuilder();

        if (dto.recipient() != null && dto.recipient().length() > 45) {
            errors.append("E-mail do destinatário pode ter no máximo 45 caracteres.");
        }

        if (dto.recipientName() != null && dto.recipientName().length() > 60) {
            errors.append("Nome do destinatário pode ter no máximo 60 caracteres");
        }

        if (dto.sender() != null && dto.sender().length() > 45) {
            errors.append("E-mail do remetente pode ter no máximo 45 caracteres");
        }

        if (dto.subject() != null && dto.subject().length() > 120) {
            errors.append("Assunto pode ter no máximo 120 caracteres.");
        }

        if (dto.content() != null && dto.content().length() > 256) {
            errors.append("Conteúdo do e-mail pode ter no máximo 256 caracteres.");
        }

        if (errors.length() > 0) {
            throw new BusinessException("Erros de validação: " + errors, HttpStatus.BAD_REQUEST);
        }
    }
}
