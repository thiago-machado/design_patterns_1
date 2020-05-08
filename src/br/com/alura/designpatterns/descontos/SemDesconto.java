package br.com.alura.designpatterns.descontos;

import br.com.alura.designpatterns.Orcamento;

public class SemDesconto implements Desconto {

	/*
	 * Não há mais desconto a ser aplicado
	 */
	@Override
	public double desconta(Orcamento orcamento) {
		return 0;
	}

	@Override
	public void setProximo(Desconto desconto) {
		// nao tem!
	}

}
