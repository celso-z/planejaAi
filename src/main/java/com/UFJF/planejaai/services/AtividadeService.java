package com.UFJF.planejaai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.domain.AtividadeDTO;
import com.UFJF.planejaai.domain.Evento;
import com.UFJF.planejaai.domain.TipoAtividade;
import com.UFJF.planejaai.repositories.AtividadeRepository;
import com.UFJF.planejaai.services.EventoService;
@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;
	@Autowired
	private EventoService eventoService;
	
	public void criarAtividade(AtividadeDTO atividadeDto) {
		if(atividadeDto == null) {
			throw new IllegalArgumentException("Atividade não pode ser nula!");
		}
		Atividade atividade = new Atividade();
		atividade.setNome(atividadeDto.getNome());
		atividade.setDescricao(atividadeDto.getDescricao());
		atividade.setEvento(
				eventoService.findById(atividadeDto.getEventoId()));
		atividade.setMaxCapacidade(atividadeDto.getCapacidadeMaxima());
		atividade.setData(atividadeDto.getData());
		atividade.setMinutosDuracao(atividadeDto.getDuracao());
		atividade.setTipoAtividade(TipoAtividade.MINICURSO); //Temporário
		atividadeRepository.save(atividade);
	}
	
	public List<Atividade> getAllAtividades(){
		return (List<Atividade>) atividadeRepository.findAll();
	}
	
	public List<Atividade> getAllAtividadesEvento(Evento evento){
		return (List<Atividade>) atividadeRepository.findAllByEvento(evento);
	}
	
	public Atividade getById(Long id){
		Optional<Atividade> atividade = atividadeRepository.findById(id);
		return atividade.map(atv -> atv )
	      .orElseThrow(() -> new UsernameNotFoundException("Atividade não encontrada!"));
	}
	public void deleteById(Long id){
		Optional<Atividade> atividade = atividadeRepository.findById(id);
		atividade.ifPresentOrElse(atv -> {atividadeRepository.delete(atv);},
				() -> {throw new UsernameNotFoundException("Atividade não encontrada!");});
	}
}
