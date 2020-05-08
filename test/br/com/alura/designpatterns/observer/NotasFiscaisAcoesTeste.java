package br.com.alura.designpatterns.observer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.designpatterns.notafiscal.EnviadorDeEmail;
import br.com.alura.designpatterns.notafiscal.EnviadorDeSMS;
import br.com.alura.designpatterns.notafiscal.ItemDaNota;
import br.com.alura.designpatterns.notafiscal.NotaFiscal;
import br.com.alura.designpatterns.notafiscal.NotaFiscalBuilder;
import br.com.alura.designpatterns.notafiscal.NotaFiscalDAO;

/**
 * PATTERN OBSERVER
 * 
 * Quando temos classes que serão notificadas sobre alguma coisa (no nosso caso,
 * notificadas sobre a geração de uma nota fiscal) e um notificador que, assim
 * que executa uma ação, notifica todos que estão na lista sobre o evento
 * ocorrido, implementamos o padrão de projeto conhecido por Observer.
 * 
 * Veja como é fácil agora adicionar novas ações após a geração da nota. Basta
 * adicionar um novo "observador" ou, no nosso caso, uma nova Ação Após Gerar
 * Nota.
 * 
 * O Observer desacopla seu código e possibilita que seu código execute
 * diferentes ações após algum evento. Além disso, como o código acima
 * demonstra, criar e executar novas ações é uma tarefa fácil agora, facilitando
 * a manutenção e evolução desse trecho de código.
 * 
 * 
 * Quando podemos aplicar o padrão Observer?
 * 
 * Quando o acoplamento da nossa classe está crescendo, ou quando temos diversas
 * ações diferentes a serem executadas após um determinado processo, podemos
 * implementar o Observer.
 * 
 * Ele permite que diversas ações sejam executadas de forma transparente à
 * classe principal, reduzindo o acoplamento entre essas ações, facilitando a
 * manutenção e evolução do código.
 * 
 * @author thiago.machado
 *
 */
public class NotasFiscaisAcoesTeste {

	/*
	 * Imagine que, após todo o processo de geração de notas fiscais de um sistema,
	 * ainda é necessário enviar a nota fiscal por e-mail para o cliente, salvar no
	 * banco de dados, enviar por SMS e ainda imprimi-la.
	 */
	@Test
	public void criacaoNotaFiscal() {

		NotaFiscal nf = new NotaFiscalBuilder()
				.paraEmpresa("Caelum")
				.comCnpj("123.456.789/0001-10")
				.comItem(new ItemDaNota("item 1", 100.0))
				.comItem(new ItemDaNota("item 2", 200.0))
				.comItem(new ItemDaNota("item 3", 300.0))
				.comObservacoes("entregar nf pessoalmente")
				.naDataAtual()
				.adicionaAcao(new EnviadorDeSMS())
				.adicionaAcao(new EnviadorDeEmail())
				.adicionaAcao(new NotaFiscalDAO())
				.constroi();

		assertEquals("Caelum", nf.getRazaoSocial());
		assertEquals("123.456.789/0001-10", nf.getCnpj());
		assertEquals(600, nf.getValorBruto(), 0.01);
		assertEquals(3, nf.getItens().size());
		assertEquals("entregar nf pessoalmente", nf.getObservacoes());
	}
}
