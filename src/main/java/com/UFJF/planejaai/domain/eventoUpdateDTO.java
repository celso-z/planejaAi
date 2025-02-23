package com.UFJF.planejaai.domain;

import java.sql.Date;

public record eventoUpdateDTO(String nome, String descricao, String local, Integer maxAtividade,
		Date dataInicio, Date dataFim) {

}
