# mail-sender
Envio de email com Spring Starter e Freemarker

Spring Boot fornece a possibilidade de enviar emails apenas definindo quais as propriedades do servidor de emails. 

O projeto começa com a declaração das dependências dos starters necessários:

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```
Como estamos usando o Spring Boot, as propriedades de email são declaradas com o prefixo spring.mail

Aqui eu coloco dois tipos de configuração que podem ser também separadas por perfil

**application-prod.properties**

```
#SENDGRID
spring.mail.host=smtp.sendgrid.net
spring.mail.port=587
spring.mail.username=apikey
spring.mail.password=${sendgrid.apikey:DEFAULT_VALUE}


mail.sender=sender@company.com.br
```

**application.properties**

```
#COMMON SMTP SERVER

spring.mail.host=mailer.host.com
spring.mail.username=smtpsender@host.com
spring.mail.password=${mail.password:DEFAULT_VALUE}
spring.mail.properties.mail.smtp.port=25
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.socketFactory.port=25
spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory


mail.sender=sender@company.com
```

O primeiro set de configurações usa o SENDGRID para enviar emails.

Já o segundo usa um servidor SMTP interno com uma camada de SSL.

A partir de então, só é necessário instanciar e popular um helper do próprio framework:

```
.
.
.

MimeMessage mimeMessage = mailSender.createMimeMessage();


MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

helper.setFrom(appProperties.getSender());
helper.setTo(message.getRecipients().toArray(new String[0]));
helper.setSubject(message.getSubject());

//estamos usando freemarker aqui,
//portanto precisamos marcar que o conteudo do email esta em HTML
helper.setText(processarTemplate(message), true);



mailSender.send(mimeMessage);
.
.
.
```

 Basicamente, é isso que precisamos fazer.
