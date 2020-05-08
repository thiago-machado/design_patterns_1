package br.com.alura.designpatterns.state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.Orcamento;

/**
 * 
 * PATTERN STATE
 * 
 * Nossos or�amentos podem ter diferentes estados durante o seu ciclo de vida.
 * Um or�amento nasce "Em aprova��o", e pode virar "Aprovado" ou "Reprovado". Ao
 * final de todo o processo, dever� ser "Finalizado".
 * 
 * Dependendo do estado que o or�amento se encontra, algumas a��es podem ser
 * diferentes. Por exemplo, podemos adicionar um desconto extra ao or�amento:
 * quando o or�amento est� em aprova��o, a empresa oferece 5% a mais de
 * desconto; quando j� est� aprovado, a empresa oferece 2% de desconto.
 * Or�amentos reprovados e finalizados n�o recebem nada de desconto extra.
 * 
 * Ler o que est� em EstadoDeUmOrcamento.
 * 
 * 
 * Quando podemos aplicar o padr�o State? O que voc� v� de positivo e negativo
 * nele?
 * 
 * A principal situa��o que faz emergir o Design Pattern State � a necessidade
 * de implementa��o de uma m�quina de estados. Geralmente, o controle das
 * poss�veis transi��es s�o v�rios e complexos, fazendo com que a implementa��o
 * n�o seja simples. O State auxilia a manter o controle dos estados simples e
 * organizados atrav�s da cria��o de classes que representem cada estado e saiba
 * controlar as transi��es.
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
		Orcamento reforma = new Orcamento(500.0); // Est� EM APROVA��O
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

		reforma.aplicaDescontoExtra(); // lancar� excecao, pois n�o pode dar desconto nesse estado
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
