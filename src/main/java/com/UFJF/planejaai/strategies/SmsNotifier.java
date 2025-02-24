package com.UFJF.planejaai.strategies;

import org.springframework.stereotype.Component;

import com.UFJF.planejaai.domain.Inscricao;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Component
public class SmsNotifier implements NotifierStrategy {

	@Override
	public boolean notify(Inscricao inscricao) {
        String message = "Se prepare para o seu compromisso"
  		      + "a atividade " + inscricao.getAtividade().getNome() + " começar a qualquer momento"
  		      + "Equipe Planejaai";
        String phoneNumber = inscricao.getParticipante().getTelefone();
        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .build();
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phoneNumber)
                    .build();

            PublishResponse result = snsClient.publish(request);
            snsClient.close();
            return true;
        } catch (SnsException e) {
            return false;
        }
    }

}
