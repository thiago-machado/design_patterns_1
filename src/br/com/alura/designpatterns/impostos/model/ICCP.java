package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

public class ICCP extends TemplateDeImpostoCondicional {

	public ICCP() {
		super(null);
	}
	
	public ICCP(Imposto outroImposto) {
		super(outroImposto);
	}

	@Override
	public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		return orcamento.getBaseCalculo() > 500;
	}

	@Override
	public double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getBaseCalculo() * 0.07;
	}

	@Override
	public double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getBaseCalculo() * 0.04;
	}
	
	/*
	 * TemplateDeImpostoCondicional já está implementando o método calculoDoOutroImposto(...)
	 */

}
