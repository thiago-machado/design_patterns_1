package br.com.alura.designpatterns;

import br.com.alura.designpatterns.impostos.model.Imposto;

public class CalculadorDeImpostos {

	public double realizaCalculo(Orcamento orcamento, Imposto imposto) {
		return imposto.calcula(orcamento);
	}

}
