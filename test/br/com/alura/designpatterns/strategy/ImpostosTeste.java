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
 * Com um �nico m�todo em nosso CalculadorDeImpostos, podemos realizar o c�lculo
 * de diferentes tipos de impostos, apenas recebendo a estrat�gia do tipo do
 * imposto que desejamos utilizar no c�lculo.
 * 
 * Quando utilizamos uma hierarquia, como fizemos com a interface Imposto e as
 * implementa��es ICMS e ISS, e recebemos o tipo mais gen�rico como par�metro,
 * para ganharmos o polimorfismo na regra que ser� executada, simplificando o
 * c�digo e sua evolu��o, estamos usando o Design Pattern chamado Strategy.
 * 
 * Repare que a cria��o de uma nova estrat�gia de c�lculo de imposto n�o implica
 * em mudan�as no c�digo escrito abaixo! Basta criarmos uma nova classe que
 * implementa a interface Imposto, que nosso CalculadorDeImpostos conseguir�
 * calcul�-lo sem precisar de nenhuma altera��o!
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
