package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

public class ISS extends Imposto {

	/*
	 * Construtor default que permite calcular o imposto sem sem a necessidade de
	 * chamar outro.
	 * 
	 * Devemos permitir que um imposto não tenha mais um próximo imposto a ser
	 * calculado. Para isso, vamos adicionar um construtor default na classe
	 * Imposto, e fazer com que o método calculoDoOutroImposto() agora trate o caso
	 * de não haver um próximo.
	 */
	public ISS() {
		super(null);
	}
	
	public ISS(Imposto outroImposto) {
		super(outroImposto);
	}

	@Override
	public double calcula(Orcamento orcamento) {
		return orcamento.getBaseCalculo() * 0.06 + calculoDoOutroImposto(orcamento);
	}

	@Override
	public double calculoDoOutroImposto(Orcamento orcamento) {
		return outroImposto == null ? 0 : outroImposto.calcula(orcamento);
	}

}
