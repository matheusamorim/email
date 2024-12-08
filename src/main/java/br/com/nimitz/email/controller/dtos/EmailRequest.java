package br.com.nimitz.email.controller.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(

       @Email
       @NotBlank
       String emailDestinatario,
       String nomeDestinatario,

       @Email
       @NotBlank
       String emailRemetente,
       String assunto,
       String conteudo) {
}
