package br.com.alura.designpatterns.descontos;

import br.com.alura.designpatterns.Orcamento;

public class DescontoPorMaisDeQuinhentosReais implements Desconto {

	private Desconto proximo;

	/*
	 * Caso o valor da base de cálculo seja superior a 500 reais, aplica um desconto de 7%
	 * 
	 * Senão, chama o próximo desconto.
	 */
	@Override
	public double desconta(Orcamento orcamento) {
		if (orcamento.getBaseCalculo() > 500) {
			return orcamento.getBaseCalculo() * 0.07;
		} else {
			return proximo.desconta(orcamento);
		}
	}

	@Override
	public void setProximo(Desconto proximo) {
		this.proximo = proximo;
	}
}
