
# Email Send

Envio de e-mail de acordo com os provedores

## Requisitos
- Java 17

### Estrutura do Projeto

A estrutura do projeto é dividida principalmente em três componentes:

- **Controller**: Responsável pela entrada e processamento das requisições HTTP.
- **Services**: Contém a definição das regras de negócio.
- **Clients**: Responsável pela comunicação com os provedores externos, como os provedores de e-mail. Aqui são feitas as integrações com serviços de terceiros

## Endpoints
#### URL: `POST localhost:8080/send`

#### Requisição

A requisição deve ser feita com o corpo no formato JSON, contendo os seguintes campos:

```json
{
  "emailDestinatario": "destinatario@example.com",
  "nomeDestinatario": "Nome do Destinatário",
  "emailRemetente": "remetente@example.com",
  "assunto": "Assunto do email",
  "conteudo": "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
}
``` 
A aplicacao enviará para o provider definido na `email.integracao` em [application.properties](src%2Fmain%2Fresources%2Fapplication.properties)

### Saída Esperada
#### OCI:
```json
{
    "recipientEmail":"destinatario@example.com",
    "recipientName":"Nome do Destinatário",
    "senderEmail":"remetente@example.com",
    "subject":"Assunto do email",
    "body":"Lorem ipsum dolor sit amet, consectetur adipiscing elit"
}
```
#### AWS: 
```json

{
  "recipient":"destinatario@example.com",
  "recipientName":"Nome do Destinatário",
  "sender":"remetente@example.com",
  "subject":"Assunto do email",
  "content":"Lorem ipsum dolor sit amet, consectetur adipiscing elit"
}
``` 
