package br.com.alura.designpatterns.descontos;

import br.com.alura.designpatterns.Orcamento;

public class DescontoPorMaisDeCincoItens implements Desconto {

	private Desconto proximo;

	/*
	 * Caso tenha mais de 5 itens na lista, aplica um desconto de 10%.
	 * 
	 * Senão, chama o próximo desconto.
	 */
	@Override
	public double desconta(Orcamento orcamento) {
		if (orcamento.getItens().size() > 5)
			return orcamento.getBaseCalculo() * 0.1;
		else
			return proximo.desconta(orcamento);

	}

	@Override
	public void setProximo(Desconto proximo) {
		this.proximo = proximo;
	}

}
