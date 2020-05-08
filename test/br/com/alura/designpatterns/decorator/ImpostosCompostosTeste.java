package br.com.alura.designpatterns.decorator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.CalculadorDeImpostos;
import br.com.alura.designpatterns.Orcamento;
import br.com.alura.designpatterns.impostos.model.ICCC;
import br.com.alura.designpatterns.impostos.model.ICCP;
import br.com.alura.designpatterns.impostos.model.ICMS;
import br.com.alura.designpatterns.impostos.model.IKCV;
import br.com.alura.designpatterns.impostos.model.ISS;
import br.com.alura.designpatterns.impostos.model.Imposto;

/**
 * 
 * PATTERN DECORATOR
 * 
 * Em muitos projetos, pode ser necessário criar comportamentos que sejam
 * compostos por outros comportamentos. Um exemplo seria calcularmos o ICMS em
 * cima do ISS.
 * 
 * Pensando no momento do uso das classes de impostos e tendo em vista que
 * queremos ter diferentes combinações de impostos sem precisarmos criar classes
 * novas, poderíamos fazer os impostos, opcionalmente, depender de outro
 * imposto.
 * 
 * Quando compomos comportamento, através de classes que recebem objetos do
 * mesmo tipo que elas mesmas (nesse caso, ISS que é um Imposto, recebe em seu
 * construtor outro Imposto) para fazerem parte de seu comportamento, de uma
 * maneira que seu uso é definido a partir do que se passou para a instanciação
 * dos objetos, é o que caracteriza o Design Pattern chamado Decorator.
 * 
 * 
 * Quando devemos aplicar o padrão Decorator?
 * 
 * Sempre que percebemos que temos comportamentos que podem ser compostos por
 * comportamentos de outras classes envolvidas em uma mesma hierarquia, como foi
 * o caso dos impostos, que podem ser composto por outros impostos. O Decorator
 * introduz a flexibilidade na composição desses comportamentos, bastando
 * escolher no momento da instanciação, quais instancias serão utilizadas para
 * realizar o trabalho.
 * 
 * @author thiago.machado
 *
 */
public class ImpostosCompostosTeste {

	/*
	 * A classe ISS precisa receber outros impostos. E se quiséssemos fazer o
	 * processo inverso, ou seja, calcular o ISS, em cima do ICMS, precisaríamos que
	 * a classe ICMS recebesse também outros impostos. E pela flexibilidade, deveria
	 * poder ser recebido qualquer imposto.
	 * 
	 * Com isso, percebemos que em nosso caso, todos os impostos, podem receber
	 * outros impostos. Podemos explicitar isso através de construtores na classe
	 * Imposto. Caso você possua uma interface chamada Imposto, nesse momento,
	 * pode-se transformá-la em uma classe abstrata, para podermos adicionar os
	 * construtores.
	 * 
	 * O próximo passo é fazermos as classes filhas de Imposto também terem os
	 * construtores, delegando para o construtor de Imposto.
	 * 
	 * Nesse momento, já conseguimos fazer com que os impostos recebam outros
	 * impostos.
	 */
	@Test
	public void calculandoICMSComISS() {

		Imposto imposto = new ICMS(new ISS());

		Orcamento orcamento = new Orcamento(789.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double issImposto = calculador.realizaCalculo(orcamento, imposto);

		assertEquals(126.39, issImposto, 0.01);
	}

	@Test
	public void calculandoISSComICCC() {

		Imposto imposto = new ISS(new ICCC());

		Orcamento orcamento = new Orcamento(789.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double issImposto = calculador.realizaCalculo(orcamento, imposto);

		assertEquals(86.89, issImposto, 0.01);
	}

	@Test
	public void calculandoICCCComICMS() {

		Imposto imposto = new ICCC(new ICMS());

		Orcamento orcamento = new Orcamento(789.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double issImposto = calculador.realizaCalculo(orcamento, imposto);

		assertEquals(118.49, issImposto, 0.01);
	}

	@Test
	public void calculandoISSComICMSComICCC() {

		Imposto imposto = new ISS(new ICMS(new ICCC()));

		Orcamento orcamento = new Orcamento(789.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double issImposto = calculador.realizaCalculo(orcamento, imposto);

		assertEquals(165.88, issImposto, 0.01);
	}

	@Test
	public void calculandoImpostoComdicionalIKCVComICCP() {

		Imposto imposto = new IKCV(new ICCP());

		Orcamento orcamento = new Orcamento(789.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double issImposto = calculador.realizaCalculo(orcamento, imposto);

		assertEquals(86.89, issImposto, 0.01);
	}
}
