package com.example.demo.service;


import java.time.LocalDate;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.config.AppProperties;
import com.example.demo.dto.MailData;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class MailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private AppProperties appProperties;
	
	@Autowired
	private Configuration freemarkerConfig;
    
    public void sendMail(MailData mailData) {
    	StringBuilder body = new StringBuilder("Elementum commodo facilisis pretium libero nascetur");
    	
    	body.append("Tempor dignissim interdum quisque.")
    		.append("Purus vel mus eu accumsan parturient ac vel, erat purus consectetur quam velit.")
    		.append("Est pretium tellus arcu mollis ligula dolor.");
        
    	MailMessage message = buildMailMessage(mailData, "Mail Subject", body.toString());
    	
    	send(message);
    }

    private MailMessage buildMailMessage(MailData data, String subject, String bodyMessage) {
        MailModel model = MailModel.builder()
                .username(data.getName())
                .company("Company Inc")
                .year(LocalDate.now().getYear())
                .productName("Your Product")
                .message(bodyMessage)
                .address("1st Street, 100 - City - State, Postal code")
                .team("Company Team")
                .support("https://www.company.com/")
                .productUrl("https://system.company.com/")
                .build();

        MailMessage message = MailMessage.builder()
            .template("mail-template")
            .subject(subject)
            .recipient(data.getMail())
            .property("model", model)
            .build();
        
        return message;
    }
    
	private void send(MailMessage message) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			
			helper.setFrom(appProperties.getSender());
			helper.setTo(message.getRecipients().toArray(new String[0]));
			helper.setSubject(message.getSubject());
			helper.setText(processarTemplate(message), true);

			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String processarTemplate(MailMessage message) throws Exception {
		Template template = freemarkerConfig.getTemplate(message.getTemplate() + ".html");
		
		return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getProperties());
	}	
}