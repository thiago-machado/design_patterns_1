package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

/**
 * PATTERN TEMPLATE METHOD
 * 
 * Classe molde que generaliza as chamadas de calcula().
 * 
 * Basta agora fazer com que os impostos desejados possuam suas pr�prias
 * implementa��es de deveUsarMaximaTaxacao(), maximaTaxacao() e minimaTaxacao().
 * 
 * Podemos deixar expl�cito nesse c�digo que cada um desses m�todos s�o
 * "buracos" e devem ser implementados por classes-filhas. Logo, podemos tornar
 * esses m�todos abstratos!
 * 
 * Ou seja, quando algum imposto estender dessa classe, ele dever� implementar
 * esses "buracos". E quando executado o m�todo calcula(), as implementa��es
 * realizadas nesses "buracos" ser�o executadas conforme est�o programadas em
 * calcula().
 * 
 * Veja que ambas as classes de impostos s� implementam as partes "que faltam"
 * do algoritmo! A classe TemplateDeImpostoCondicional possui um m�todo que
 * funciona como um template, ou seja, um molde, para as classes filhas. Da� o
 * nome do padr�o de projeto: Template Method.
 * 
 * Veja que o uso do padr�o evitou a repeti��o de c�digo, e ainda facilitou a
 * implementa��o das diferentes varia��es do algoritmo.
 * 
 * 
 * @author thiago.machado
 *
 */
public abstract class TemplateDeImpostoCondicional extends Imposto {
	
	/*
	 * Construtor default que permite calcular o imposto sem sem a necessidade de
	 * chamar outro.
	 * 
	 * Devemos permitir que um imposto n�o tenha mais um pr�ximo imposto a ser
	 * calculado. Para isso, vamos adicionar um construtor default na classe
	 * Imposto, e fazer com que o m�todo calculoDoOutroImposto() agora trate o caso
	 * de n�o haver um pr�ximo.
	 */
	public TemplateDeImpostoCondicional(Imposto outroImposto) {
		super(outroImposto);
	}

	/*
	 * Definindo como "final", proibimos que as classes filhas editem esse m�todo
	 */
	@Override
	public final double calcula(Orcamento orcamento) {

		if (deveUsarMaximaTaxacao(orcamento)) {
			return maximaTaxacao(orcamento) + calculoDoOutroImposto(orcamento);
		} else {
			return minimaTaxacao(orcamento) + calculoDoOutroImposto(orcamento);
		}
	}

	protected abstract boolean deveUsarMaximaTaxacao(Orcamento orcamento);

	protected abstract double maximaTaxacao(Orcamento orcamento);

	protected abstract double minimaTaxacao(Orcamento orcamento);
	
	@Override
	public double calculoDoOutroImposto(Orcamento orcamento) {
		return outroImposto == null ? 0 : outroImposto.calcula(orcamento);
	}

}
