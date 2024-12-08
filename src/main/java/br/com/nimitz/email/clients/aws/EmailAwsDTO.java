package br.com.nimitz.email.clients.aws;

public record EmailAwsDTO(
        String recipient,
        String recipientName,
        String sender,
        String subject,
        String content) {
}
