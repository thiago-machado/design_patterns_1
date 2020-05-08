package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

public class ICMS extends Imposto {

	/*
	 * Construtor default que permite calcular o imposto sem sem a necessidade de
	 * chamar outro.
	 * 
	 * Devemos permitir que um imposto n�o tenha mais um pr�ximo imposto a ser
	 * calculado. Para isso, vamos adicionar um construtor default na classe
	 * Imposto, e fazer com que o m�todo calculoDoOutroImposto() agora trate o caso
	 * de n�o haver um pr�ximo.
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
	 * Repare que o m�todo calculoDoOutroImposto invoca o m�todo calcula caso outro
	 * imposto tenha sido definido. Caso contr�rio, apenas retorna 0, n�o
	 * influenciando na composi�ao do c�lculo.
	 */
	@Override
	public double calculoDoOutroImposto(Orcamento orcamento) {
		return outroImposto == null ? 0 : outroImposto.calcula(orcamento);
	}
}
