package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

public class ICCC extends Imposto {

	/*
	 * Construtor default que permite calcular o imposto sem sem a necessidade de
	 * chamar outro.
	 * 
	 * Devemos permitir que um imposto n�o tenha mais um pr�ximo imposto a ser
	 * calculado. Para isso, vamos adicionar um construtor default na classe
	 * Imposto, e fazer com que o m�todo calculoDoOutroImposto() agora trate o caso
	 * de n�o haver um pr�ximo.
	 */
	public ICCC() {
		super(null);
	}
	
	public ICCC(Imposto outroImposto) {
		super(outroImposto);
	}

	@Override
	public double calcula(Orcamento orcamento) {
		if (orcamento.getBaseCalculo() < 1000) {
			return orcamento.getBaseCalculo() * 0.05 + calculoDoOutroImposto(orcamento);
		} else if (orcamento.getBaseCalculo() <= 3000) {
			return orcamento.getBaseCalculo() * 0.07 + calculoDoOutroImposto(orcamento);
		} else {
			return ((orcamento.getBaseCalculo() * 0.08) + 30) + calculoDoOutroImposto(orcamento);
		}
	}
	
	@Override
	public double calculoDoOutroImposto(Orcamento orcamento) {
		return outroImposto == null ? 0 : outroImposto.calcula(orcamento);
	}

}
