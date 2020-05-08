package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

public class ICMS extends Imposto {

	/*
	 * Construtor default que permite calcular o imposto sem sem a necessidade de
	 * chamar outro.
	 * 
	 * Devemos permitir que um imposto não tenha mais um próximo imposto a ser
	 * calculado. Para isso, vamos adicionar um construtor default na classe
	 * Imposto, e fazer com que o método calculoDoOutroImposto() agora trate o caso
	 * de não haver um próximo.
	 */
	public ICMS() {
		super(null);
	}

	public ICMS(Imposto outroImposto) {
		super(outroImposto);
	}

	@Override
	public double calcula(Orcamento orcamento) {
		return orcamento.getBaseCalculo() * 0.1 + calculoDoOutroImposto(orcamento);
	}

	/*
	 * Repare que o método calculoDoOutroImposto invoca o método calcula caso outro
	 * imposto tenha sido definido. Caso contrário, apenas retorna 0, não
	 * influenciando na composiçao do cálculo.
	 */
	@Override
	public double calculoDoOutroImposto(Orcamento orcamento) {
		return outroImposto == null ? 0 : outroImposto.calcula(orcamento);
	}
}
