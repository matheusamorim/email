package br.com.nimitz.email.clients.oci;

public record EmailOciDTO(
        String recipientEmail,
        String recipientName,
        String senderEmail,
        String subject,
        String body) {
}
