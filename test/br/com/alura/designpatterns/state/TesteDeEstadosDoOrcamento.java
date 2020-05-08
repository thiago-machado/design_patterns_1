package br.com.alura.designpatterns.state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.Orcamento;

/**
 * 
 * PATTERN STATE
 * 
 * Nossos orçamentos podem ter diferentes estados durante o seu ciclo de vida.
 * Um orçamento nasce "Em aprovação", e pode virar "Aprovado" ou "Reprovado". Ao
 * final de todo o processo, deverá ser "Finalizado".
 * 
 * Dependendo do estado que o orçamento se encontra, algumas ações podem ser
 * diferentes. Por exemplo, podemos adicionar um desconto extra ao orçamento:
 * quando o orçamento está em aprovação, a empresa oferece 5% a mais de
 * desconto; quando já está aprovado, a empresa oferece 2% de desconto.
 * Orçamentos reprovados e finalizados não recebem nada de desconto extra.
 * 
 * Ler o que está em EstadoDeUmOrcamento.
 * 
 * 
 * Quando podemos aplicar o padrão State? O que você vê de positivo e negativo
 * nele?
 * 
 * A principal situação que faz emergir o Design Pattern State é a necessidade
 * de implementação de uma máquina de estados. Geralmente, o controle das
 * possíveis transições são vários e complexos, fazendo com que a implementação
 * não seja simples. O State auxilia a manter o controle dos estados simples e
 * organizados através da criação de classes que representem cada estado e saiba
 * controlar as transições.
 */
public class TesteDeEstadosDoOrcamento {

	@Test
	public void iniciaFinalizaUmOrcamento() {
		Orcamento reforma = new Orcamento(500.0);

		reforma.aplicaDescontoExtra();
		assertEquals(25, reforma.getBaseCalculo(), 0.01);
		reforma.aprova(); // aprova nota!

		reforma.aplicaDescontoExtra();
		assertEquals(0.5, reforma.getBaseCalculo(), 0.01);

		reforma.finaliza();
	}

	@Test(expected = RuntimeException.class)
	public void naoPodeAplicarDescontoMaisDeUmaVezParaMesmoEstado() {
		Orcamento reforma = new Orcamento(500.0); // Está EM APROVAÇÃO
		reforma.aplicaDescontoExtra();
		reforma.aplicaDescontoExtra();
	}

	@Test(expected = RuntimeException.class)
	public void lancaExcecaoQuandoAplicaDescontoAposFinalizacao() {

		Orcamento reforma = new Orcamento(500.0);

		reforma.aplicaDescontoExtra();
		assertEquals(25, reforma.getBaseCalculo(), 0.01);
		reforma.aprova(); // aprova nota!

		reforma.aplicaDescontoExtra();
		assertEquals(0.5, reforma.getBaseCalculo(), 0.01);

		reforma.finaliza();

		reforma.aplicaDescontoExtra(); // lancará excecao, pois não pode dar desconto nesse estado
	}

	@Test(expected = RuntimeException.class)
	public void lancaExcecaoQuandoAprovaAposFinalizacao() {
		Orcamento reforma = new Orcamento(500.0);
		reforma.aprova(); // aprova nota!
		reforma.finaliza();
		reforma.aprova();
	}

	@Test(expected = RuntimeException.class)
	public void lancaExcecaoQuandoEmAndamentoPulaParaFinalizacao() {
		Orcamento reforma = new Orcamento(500.0);
		reforma.finaliza();
	}

	@Test(expected = RuntimeException.class)
	public void lancaExcecaoQuandoFinalizaPulaParaReprovado() {
		Orcamento reforma = new Orcamento(500.0);
		reforma.aprova();
		reforma.finaliza();
		reforma.reprova();
	}

	@Test(expected = RuntimeException.class)
	public void lancaExcecaoQuandoFinalizaNovamente() {
		Orcamento reforma = new Orcamento(500.0);
		reforma.aprova();
		reforma.finaliza();
		reforma.finaliza();
	}

	@Test(expected = RuntimeException.class)
	public void lancaExcecaoQuandoReprovaNovamente() {
		Orcamento reforma = new Orcamento(500.0);
		reforma.aprova();
		reforma.reprova();
		reforma.reprova();
	}
}
