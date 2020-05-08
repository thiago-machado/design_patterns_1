package br.com.alura.designpatterns.chainresponsibility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.Orcamento;
import br.com.alura.designpatterns.descontos.CalculadorDeDescontos;
import br.com.alura.designpatterns.descontos.Item;

/**
 * 
 * PADR�O CHAIN OF RESPONSABILITY
 * 
 * A ideia do padr�o � resolver problemas do tipo: de acordo com o cen�rio,
 * devemos realizar alguma a��o. Ao inv�s de escrevermos c�digo procedural, e
 * deixarmos um �nico m�todo descobrir o que deve ser feito, quebramos essas
 * responsabilidades em v�rias diferentes classes, e as unimos como uma
 * corrente.
 * 
 * O que foi desenvolvido para exemplifica��o atende muito bem a esse padr�o:
 * dado um or�amento, verificamos se o primeiro desconto para esse or�amento �
 * satisfeito. Caso n�o, chama o pr�ximo e assim sucessivamente, at� atender a
 * alguma condi��o de desconto, ou at� chegar na classe que n�o ir� realizar
 * mais descontos e encerrar as chamadas encadeadas.
 * 
 * @author thiago.machado
 *
 */
public class DescontosTeste {

	@Test
	public void descontoMaisDeCincoItens() {
		CalculadorDeDescontos calculador = new CalculadorDeDescontos();

		Orcamento orcamento = new Orcamento(70.0);
		orcamento.adicionaItem(new Item("CANETA", 10.0));
		orcamento.adicionaItem(new Item("APONTADOR", 15.0));
		orcamento.adicionaItem(new Item("CADERNO", 25.0));
		orcamento.adicionaItem(new Item("R�GUA", 5));
		orcamento.adicionaItem(new Item("BORRACHA", 5));
		orcamento.adicionaItem(new Item("ESTOJO", 10));

		double desconto = calculador.calcula(orcamento);
		assertEquals(7, desconto, 0.01);
	}
	
	@Test
	public void descontoMaisDeQuinhentosReais() {
		CalculadorDeDescontos calculador = new CalculadorDeDescontos();

		Orcamento orcamento = new Orcamento(1080.0);
		orcamento.adicionaItem(new Item("NOTEBOOK ACER CELERON DUAL CORE 4GB RAM HD 500TB", 10.0));

		double desconto = calculador.calcula(orcamento);
		assertEquals(75.6, desconto, 0.01);
	}
	
	@Test
	public void descontoVendaCasada() {
		CalculadorDeDescontos calculador = new CalculadorDeDescontos();

		Orcamento orcamento = new Orcamento(25.0);
		orcamento.adicionaItem(new Item("CANETA", 10.0));
		orcamento.adicionaItem(new Item("L�PIS", 15.0));

		double desconto = calculador.calcula(orcamento);
		assertEquals(1.25, desconto, 0.01);
	}
	
	@Test
	public void descontoZerado() {
		CalculadorDeDescontos calculador = new CalculadorDeDescontos();

		Orcamento orcamento = new Orcamento(20.0);
		orcamento.adicionaItem(new Item("CADERNO", 10.0));

		double desconto = calculador.calcula(orcamento);
		assertEquals(0, desconto, 0.01);
	}
}
