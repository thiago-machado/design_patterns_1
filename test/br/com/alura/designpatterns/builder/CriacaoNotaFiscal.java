package br.com.alura.designpatterns.builder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.notafiscal.ItemDaNota;
import br.com.alura.designpatterns.notafiscal.NotaFiscal;
import br.com.alura.designpatterns.notafiscal.NotaFiscalBuilder;

/**
 * PATTERN BUILDER
 * 
 * Para classes cuja responsabilidade � a de criar outros objetos, geralmente
 * complexos como o do exemplo acima, damos o nome de Builder. Ela esconde toda
 * a l�gica da cria��o de objetos complexos.
 * 
 * Um dos problemas da utiliza��o de construtores � que, em casos onde diversos
 * atributos sejam opcionais, precisar�amos ter uma combina��o gigante de
 * construtores (uma para cada poss�vel combina��o de atributos obrigat�rios e
 * opcionais), tornando o c�digo dif�cil de manter. Al�m disso, como construir
 * esse objeto � complicado, colocar essa regra no construtor do objeto, s� faz
 * com que ele tenha agora mais uma responsabilidade.
 * 
 * Em outros casos, poder�amos ter diversos if's dentro do construtor. Quanto
 * mais complexo for o processo de cria��o da classe, mais voc� deve pensar em
 * isol�-lo em uma classe espec�fica.
 * 
 * O Builder possibilita a separa��o da complexidade da cria��o desse objeto em
 * uma classe espec�fica para isso, al�m de possibilitar a implementa��o de
 * atributos opcionais mais facilmente.
 * 
 * 
 * Quando podemos aplicar o padr�o Builder? E quando ele mais atrapalhar� do que
 * ajudar�?
 * 
 * Sempre que tivermos um objeto complexo de ser criado, que possui diversos
 * atributos, ou que possui uma l�gica de cria��o complicada, podemos esconder
 * tudo isso em um Builder.
 * 
 * Al�m de centralizar o c�digo de cria��o e facilitar a manuten��o, ainda
 * facilitamos a vida das classes que precisam criar essa classe complexa,
 * afinal a interface do Builder tende a ser mais clara e f�cil de ser usada.
 * 
 * @author thiago.machado
 *
 */
public class CriacaoNotaFiscal {

	/*
	 * A regra de cria��o de objeto NotaFiscal est� centralizado em uma classe que
	 * s� tem isso como responsabilidade. Al�m do c�digo estar em um lugar s�, essa
	 * classe ainda prov� uma interface bem clara e simples de ser usada,
	 * facilitando a vida das classes que ir�o utiliz�-la.
	 */
	@Test
	public void criacaoNotaFiscal() {

		/*
		 * Veja que fomos invocando um m�todo atr�s do outro. Mas como isso � poss�vel?
		 * Basta trocarmos o retorno de todos os m�todos do builder: de void para
		 * NotaFiscalBuilder. Veja que, se todo m�todo devolver NotaFiscalBuilder,
		 * poderemos chamar o pr�ximo m�todo logo em seguida.
		 * 
		 * Os m�todos do Builder agora guardam a informa��o, e retorna ele mesmo. Nosso
		 * builder tem agora uma interface fluente. A vantagem de usar method chaining,
		 * nome da t�cnica onde conseguimos invocar um m�todo atr�s do outro, �
		 * justamente a clareza do c�digo e a elimina��o do uso da vari�vel criador
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
