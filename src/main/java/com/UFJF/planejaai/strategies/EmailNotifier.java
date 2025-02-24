package com.UFJF.planejaai.strategies;

import org.springframework.stereotype.Component;

import com.UFJF.planejaai.domain.Inscricao;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest; 

@Component
public class EmailNotifier implements NotifierStrategy{
	

	static final String FROM = "noreply@planejaai.com.br";
  
	static final String CONFIGSET = "ConfigSet";

	static final String SUBJECT = "Uma atividade na qual você se cadastrou está iniciando!";
  

	@Override
	public boolean notify(Inscricao inscricao) {
		String body = "<h1>Se prepare para o seu compromisso</h1>"
		      + "<p>a atividade " + inscricao.getAtividade().getNome() + " começar a qualquer momento>"
		      + "Equipe Planejaai";
	  String to = inscricao.getParticipante().getEmail();
    try {
      AmazonSimpleEmailService client = 
          AmazonSimpleEmailServiceClientBuilder.standard()
            .withRegion(Regions.US_WEST_2).build();
      SendEmailRequest request = new SendEmailRequest()
          .withDestination(
              new Destination().withToAddresses(to))
          .withMessage(new Message()
              .withBody(new Body()
                  .withHtml(new Content()
                      .withCharset("UTF-8").withData(body)))
              .withSubject(new Content()
                  .withCharset("UTF-8").withData(SUBJECT)))
          .withSource(FROM)
          .withConfigurationSetName(CONFIGSET);
      client.sendEmail(request);
      return true;
    } catch (Exception ex) {
	      return false;
    }
  }
}

