package com.UFJF.planejaai.strategies;
import com.UFJF.planejaai.domain.Inscricao;


public interface NotifierStrategy {
	boolean notify(Inscricao inscricao);
}
