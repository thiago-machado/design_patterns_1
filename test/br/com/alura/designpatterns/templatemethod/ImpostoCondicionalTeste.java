package br.com.alura.designpatterns.templatemethod;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.CalculadorDeImpostos;
import br.com.alura.designpatterns.Orcamento;
import br.com.alura.designpatterns.descontos.Item;
import br.com.alura.designpatterns.impostos.model.ICCP;
import br.com.alura.designpatterns.impostos.model.IKCV;
import br.com.alura.designpatterns.impostos.model.Imposto;

/**
 * Quando temos diferentes algoritmos que possuem estruturas parecidas, o
 * Template Method é uma boa solução. Com ele, conseguimos definir, em um nível
 * mais macro, a estrutura do algoritmo e deixar "buracos", que serão
 * implementados de maneira diferente por cada uma das implementações
 * específicas.
 * 
 * Dessa forma, reutilizamos ao invés de repetirmos código, e facilitamos
 * possíveis evoluções, tanto do algoritmo em sua estrutura macro, quanto dos
 * detalhes do algoritmo, já que cada classe tem sua responsabilidade bem
 * separada.
 * 
 * @author thiago.machado
 *
 */
public class ImpostoCondicionalTeste {

	@Test
	public void impostoICCPParaOrcamentoAbaixoDe500Reais() {
		Imposto iccp = new ICCP();

		Orcamento orcamento = new Orcamento(498.78);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double iccpImposto = calculador.realizaCalculo(orcamento, iccp);

		assertEquals(19.95, iccpImposto, 0.01);
	}

	@Test
	public void impostoICCPParaOrcamentoAcimaDe500Reais() {
		Imposto iccp = new ICCP();

		Orcamento orcamento = new Orcamento(875.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double iccpImposto = calculador.realizaCalculo(orcamento, iccp);

		assertEquals(61.31, iccpImposto, 0.01);
	}

	@Test
	public void impostoIKCVParaOrcamentoAcimaDe500ReaisMaisItemDe100Reais() {
		Imposto ikcv = new IKCV();

		Orcamento orcamento = new Orcamento(545);
		orcamento.adicionaItem(new Item("CADEIRA DE MADEIRA", 450));
		orcamento.adicionaItem(new Item("MOLDURA PARA QUADRO", 95));

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double iccpImposto = calculador.realizaCalculo(orcamento, ikcv);

		assertEquals(43.6, iccpImposto, 0.01);
	}

	@Test
	public void impostoIKCVParaOrcamentoAbaixoDe500Reais() {
		Imposto ikcv = new IKCV();

		Orcamento orcamento = new Orcamento(450.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double iccpImposto = calculador.realizaCalculo(orcamento, ikcv); // Calculando o ISS

		assertEquals(18.03, iccpImposto, 0.01);
	}
}
