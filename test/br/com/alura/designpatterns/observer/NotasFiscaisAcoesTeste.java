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
 * Quando temos classes que ser�o notificadas sobre alguma coisa (no nosso caso,
 * notificadas sobre a gera��o de uma nota fiscal) e um notificador que, assim
 * que executa uma a��o, notifica todos que est�o na lista sobre o evento
 * ocorrido, implementamos o padr�o de projeto conhecido por Observer.
 * 
 * Veja como � f�cil agora adicionar novas a��es ap�s a gera��o da nota. Basta
 * adicionar um novo "observador" ou, no nosso caso, uma nova A��o Ap�s Gerar
 * Nota.
 * 
 * O Observer desacopla seu c�digo e possibilita que seu c�digo execute
 * diferentes a��es ap�s algum evento. Al�m disso, como o c�digo acima
 * demonstra, criar e executar novas a��es � uma tarefa f�cil agora, facilitando
 * a manuten��o e evolu��o desse trecho de c�digo.
 * 
 * 
 * Quando podemos aplicar o padr�o Observer?
 * 
 * Quando o acoplamento da nossa classe est� crescendo, ou quando temos diversas
 * a��es diferentes a serem executadas ap�s um determinado processo, podemos
 * implementar o Observer.
 * 
 * Ele permite que diversas a��es sejam executadas de forma transparente �
 * classe principal, reduzindo o acoplamento entre essas a��es, facilitando a
 * manuten��o e evolu��o do c�digo.
 * 
 * @author thiago.machado
 *
 */
public class NotasFiscaisAcoesTeste {

	/*
	 * Imagine que, ap�s todo o processo de gera��o de notas fiscais de um sistema,
	 * ainda � necess�rio enviar a nota fiscal por e-mail para o cliente, salvar no
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
