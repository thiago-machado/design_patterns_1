package br.com.alura.designpatterns.descontos;

import br.com.alura.designpatterns.Orcamento;

public class DescontoPorVendaCasada implements Desconto {

	private Desconto proximo;

	public double desconta(Orcamento orcamento) {
		if (aconteceuVendaCasadaEm(orcamento))
			return orcamento.getBaseCalculo() * 0.05;
		else
			return proximo.desconta(orcamento);
	}

	private boolean aconteceuVendaCasadaEm(Orcamento orcamento) {
		return existe("CANETA", orcamento) && existe("LÁPIS", orcamento);
	}

	private boolean existe(String nomeDoItem, Orcamento orcamento) {
		for (Item item : orcamento.getItens()) {
			if (item.getNome().equals(nomeDoItem))
				return true;
		}
		return false;
	}

	public void setProximo(Desconto proximo) {
		this.proximo = proximo;
	}

}
