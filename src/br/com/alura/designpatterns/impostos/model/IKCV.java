package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;
import br.com.alura.designpatterns.descontos.Item;

public class IKCV extends TemplateDeImpostoCondicional {

	public IKCV() {
		super(null);
	}
	
	public IKCV(Imposto outroImposto) {
		super(outroImposto);
	}

	@Override
	public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		return orcamento.getBaseCalculo() > 500 && temItemMaiorQue100ReaisNo(orcamento);
	}

	@Override
	public double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getBaseCalculo() * 0.08;
	}

	@Override
	public double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getBaseCalculo() * 0.04;
	}

	private boolean temItemMaiorQue100ReaisNo(Orcamento orcamento) {
		for (Item item : orcamento.getItens()) {
			if (item.getValor() > 100)
				return true;
		}
		return false;
	}
	
	/*
	 * TemplateDeImpostoCondicional já está implementando o método calculoDoOutroImposto(...)
	 */

}
