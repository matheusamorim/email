package br.com.nimitz.email.clients.oci;

import br.com.nimitz.email.clients.IEmailProvider;
import br.com.nimitz.email.controller.dtos.EmailRequest;
import br.com.nimitz.email.exceptions.BusinessException;
import br.com.nimitz.email.validators.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class EmailOciImpl implements IEmailProvider {

    Validator validator = new Validator();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getProvider() {
        return "OCI";
    }

    @Override
    public void emailFactory(EmailRequest emailRequest) {
        var emailOciDTO = mapToOciDTO(emailRequest);
        validator.emailOcivalidate(emailOciDTO);

        try {
            String object = objectMapper.writeValueAsString(emailOciDTO);
            System.out.println("EmailOCI: " + object);
        } catch (JsonProcessingException e){
           throw new BusinessException("Erro ao enviar o e-mail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private EmailOciDTO mapToOciDTO(EmailRequest request) {
        return new EmailOciDTO(
                request.emailDestinatario(),
                request.nomeDestinatario(),
                request.emailRemetente(),
                request.assunto(),
                request.conteudo()
        );
    }
}