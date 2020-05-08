package br.com.alura.designpatterns.descontos;

import br.com.alura.designpatterns.Orcamento;

public interface Desconto {
	double desconta(Orcamento orcamento);
	void setProximo(Desconto proximo);
}
