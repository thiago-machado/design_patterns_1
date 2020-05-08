package br.com.alura.designpatterns.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.notafiscal.ItemDaNota;
import br.com.alura.designpatterns.notafiscal.NotaFiscal;
import br.com.alura.designpatterns.notafiscal.NotaFiscalBuilder;

/**
 * PATTERN BUILDER
 * 
 * Para classes cuja responsabilidade é a de criar outros objetos, geralmente
 * complexos como o do exemplo acima, damos o nome de Builder. Ela esconde toda
 * a lógica da criação de objetos complexos.
 * 
 * Um dos problemas da utilização de construtores é que, em casos onde diversos
 * atributos sejam opcionais, precisaríamos ter uma combinação gigante de
 * construtores (uma para cada possível combinação de atributos obrigatórios e
 * opcionais), tornando o código difícil de manter. Além disso, como construir
 * esse objeto é complicado, colocar essa regra no construtor do objeto, só faz
 * com que ele tenha agora mais uma responsabilidade.
 * 
 * Em outros casos, poderíamos ter diversos if's dentro do construtor. Quanto
 * mais complexo for o processo de criação da classe, mais você deve pensar em
 * isolá-lo em uma classe específica.
 * 
 * O Builder possibilita a separação da complexidade da criação desse objeto em
 * uma classe específica para isso, além de possibilitar a implementação de
 * atributos opcionais mais facilmente.
 * 
 * 
 * Quando podemos aplicar o padrão Builder? E quando ele mais atrapalhará do que
 * ajudará?
 * 
 * Sempre que tivermos um objeto complexo de ser criado, que possui diversos
 * atributos, ou que possui uma lógica de criação complicada, podemos esconder
 * tudo isso em um Builder.
 * 
 * Além de centralizar o código de criação e facilitar a manutenção, ainda
 * facilitamos a vida das classes que precisam criar essa classe complexa,
 * afinal a interface do Builder tende a ser mais clara e fácil de ser usada.
 * 
 * @author thiago.machado
 *
 */
public class CriacaoNotaFiscal {

	/*
	 * A regra de criação de objeto NotaFiscal está centralizado em uma classe que
	 * só tem isso como responsabilidade. Além do código estar em um lugar só, essa
	 * classe ainda provê uma interface bem clara e simples de ser usada,
	 * facilitando a vida das classes que irão utilizá-la.
	 */
	@Test
	public void criacaoNotaFiscal() {

		/*
		 * Veja que fomos invocando um método atrás do outro. Mas como isso é possível?
		 * Basta trocarmos o retorno de todos os métodos do builder: de void para
		 * NotaFiscalBuilder. Veja que, se todo método devolver NotaFiscalBuilder,
		 * poderemos chamar o próximo método logo em seguida.
		 * 
		 * Os métodos do Builder agora guardam a informação, e retorna ele mesmo. Nosso
		 * builder tem agora uma interface fluente. A vantagem de usar method chaining,
		 * nome da técnica onde conseguimos invocar um método atrás do outro, é
		 * justamente a clareza do código e a eliminação do uso da variável criador
		 * (builder) repetidas vezes.
		 */
		NotaFiscal nf = new NotaFiscalBuilder().paraEmpresa("Caelum").comCnpj("123.456.789/0001-10")
				.comItem(new ItemDaNota("item 1", 100.0)).comItem(new ItemDaNota("item 2", 200.0))
				.comItem(new ItemDaNota("item 3", 300.0)).comObservacoes("entregar nf pessoalmente").naDataAtual()
				.constroi();

		assertEquals("Caelum", nf.getRazaoSocial());
		assertEquals("123.456.789/0001-10", nf.getCnpj());
		assertEquals(600, nf.getValorBruto(), 0.01);
		assertEquals(3, nf.getItens().size());
		assertEquals("entregar nf pessoalmente", nf.getObservacoes());
	}
}
