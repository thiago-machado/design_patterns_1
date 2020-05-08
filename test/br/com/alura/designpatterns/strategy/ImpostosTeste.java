package br.com.alura.designpatterns.strategy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.CalculadorDeImpostos;
import br.com.alura.designpatterns.Orcamento;
import br.com.alura.designpatterns.impostos.model.ICCC;
import br.com.alura.designpatterns.impostos.model.ICMS;
import br.com.alura.designpatterns.impostos.model.ISS;
import br.com.alura.designpatterns.impostos.model.Imposto;

/**
 * 
 * PATTERN STRATEGY
 * 
 * Com um único método em nosso CalculadorDeImpostos, podemos realizar o cálculo
 * de diferentes tipos de impostos, apenas recebendo a estratégia do tipo do
 * imposto que desejamos utilizar no cálculo.
 * 
 * Quando utilizamos uma hierarquia, como fizemos com a interface Imposto e as
 * implementações ICMS e ISS, e recebemos o tipo mais genérico como parâmetro,
 * para ganharmos o polimorfismo na regra que será executada, simplificando o
 * código e sua evolução, estamos usando o Design Pattern chamado Strategy.
 * 
 * Repare que a criação de uma nova estratégia de cálculo de imposto não implica
 * em mudanças no código escrito abaixo! Basta criarmos uma nova classe que
 * implementa a interface Imposto, que nosso CalculadorDeImpostos conseguirá
 * calculá-lo sem precisar de nenhuma alteração!
 * 
 * @author thiago.machado
 *
 */
public class ImpostosTeste {

	@Test
	public void calculandoICMSMaisISS() {

		Imposto iss = new ISS();
		Imposto icms = new ICMS();

		Orcamento orcamento = new Orcamento(789.95);

		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double issImposto = calculador.realizaCalculo(orcamento, iss); // Calculando o ISS
		double icmsImposto = calculador.realizaCalculo(orcamento, icms); // Calculando o ICMS

		assertEquals(78.99, icmsImposto, 0.01);
		assertEquals(47.39, issImposto, 0.01);
	}
	
	@Test
	public void calculandoICCC() {
		
		CalculadorDeImpostos calculador = new CalculadorDeImpostos();
		double issImposto800 = calculador.realizaCalculo(new Orcamento(800), new ICCC());
		double issImposto1200 = calculador.realizaCalculo(new Orcamento(1200), new ICCC());
		double issImposto3100 = calculador.realizaCalculo(new Orcamento(3100), new ICCC());
		
		assertEquals(40, issImposto800, 0.01);
		assertEquals(84, issImposto1200, 0.01);
		assertEquals(248 + 30, issImposto3100, 0.01);
	}
}
