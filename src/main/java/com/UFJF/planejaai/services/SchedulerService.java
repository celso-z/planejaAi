package com.UFJF.planejaai.services;
import com.UFJF.planejaai.strategies.EmailNotifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.domain.Inscricao;
import com.UFJF.planejaai.strategies.EmailNotifier;
import com.UFJF.planejaai.strategies.NotifierStrategy;

@Service
public class SchedulerService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private NotifierStrategy notifier;
    @Autowired
    private InscricaoService inscricaoService;
    
    public SchedulerService() {
    	this.notifier = new EmailNotifier();
    }
    
    public void setNotifier(NotifierStrategy notifier) {
    	this.notifier = notifier;
    }

    public void scheduleNotificacao(Atividade atividade) {
        long delay = Duration.between(LocalDateTime.now(), atividade.getData()).toMillis() - 300000; //5 minutos antes da atividade começar

        if (delay > 0) {
            scheduler.schedule(() -> notificar(atividade), delay, TimeUnit.MILLISECONDS);
            System.out.println("Atividade programada para: " + atividade.getData());
        } else {
            System.out.println("Atividade não pode ser criada para uma data passada, alertas não serão enviados");
        }
    }

    private void notificar(Atividade atividade) {
    	for(Inscricao inscricao : inscricaoService.getAllInscricoesPorAtividade(atividade)) {
    		this.notifier.notify(inscricao);
    	}
    }
}