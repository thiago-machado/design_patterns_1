package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

/**
 * PATTERN TEMPLATE METHOD
 * 
 * Classe molde que generaliza as chamadas de calcula().
 * 
 * Basta agora fazer com que os impostos desejados possuam suas próprias
 * implementações de deveUsarMaximaTaxacao(), maximaTaxacao() e minimaTaxacao().
 * 
 * Podemos deixar explícito nesse código que cada um desses métodos são
 * "buracos" e devem ser implementados por classes-filhas. Logo, podemos tornar
 * esses métodos abstratos!
 * 
 * Ou seja, quando algum imposto estender dessa classe, ele deverá implementar
 * esses "buracos". E quando executado o método calcula(), as implementações
 * realizadas nesses "buracos" serão executadas conforme estão programadas em
 * calcula().
 * 
 * Veja que ambas as classes de impostos só implementam as partes "que faltam"
 * do algoritmo! A classe TemplateDeImpostoCondicional possui um método que
 * funciona como um template, ou seja, um molde, para as classes filhas. Daí o
 * nome do padrão de projeto: Template Method.
 * 
 * Veja que o uso do padrão evitou a repetição de código, e ainda facilitou a
 * implementação das diferentes variações do algoritmo.
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
	 * Devemos permitir que um imposto não tenha mais um próximo imposto a ser
	 * calculado. Para isso, vamos adicionar um construtor default na classe
	 * Imposto, e fazer com que o método calculoDoOutroImposto() agora trate o caso
	 * de não haver um próximo.
	 */
	public TemplateDeImpostoCondicional(Imposto outroImposto) {
		super(outroImposto);
	}

	/*
	 * Definindo como "final", proibimos que as classes filhas editem esse método
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
