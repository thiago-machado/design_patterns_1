package br.com.alura.designpatterns.notafiscal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe respons�vel por pedir todos os par�metros necess�rios, montar o que
 * precisa e enfim produzir uma Nota Fiscal, de forma com que o cliente dessa
 * classe consiga entender o que est� acontecendo.
 * 
 * @author thiago.machado
 *
 */
public class NotaFiscalBuilder {

	private String razaoSocial;
	private String cnpj;
	private double impostos;
	private double valorBruto;
	private List<ItemDaNota> todosItens = new ArrayList<ItemDaNota>();
	private String observacoes;
	private Calendar data;
	private List<AcaoAposGerarNota> acoes = new ArrayList<AcaoAposGerarNota>();

	public NotaFiscalBuilder paraEmpresa(String razaoSocial) {
		this.razaoSocial = razaoSocial;
		return this;
	}

	public NotaFiscalBuilder comCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	public NotaFiscalBuilder comItem(ItemDaNota item) {
		todosItens.add(item);
		valorBruto += item.getValor();
		impostos += item.getValor() * 0.05;
		return this;
	}

	public NotaFiscalBuilder comObservacoes(String observacoes) {
		this.observacoes = observacoes;
		return this;
	}

	public NotaFiscalBuilder naDataAtual() {
		this.data = Calendar.getInstance();
		return this;
	}

	public NotaFiscalBuilder adicionaAcao(AcaoAposGerarNota novaAcao) {
		acoes.add(novaAcao);
		return this;
	}

	/*
	 * Repare que, para o m�todo constroi(), pouco importa qual a��o est� sendo
	 * executada. Ele simplesmente dispara uma, ou mais a��es.
	 * 
	 * Com essa informa��o, podemos reescrever nosso m�todo, fazendo com que ele
	 * dispare uma lista de a��es, independente de quais a��es.
	 * 
	 * Repare que agora o m�todo constroi() n�o se importa com quais a��es ser�o
	 * executadas. Ele simplesmente notifica essas a��es. Precisamos s� preencher
	 * essa lista com as a��es que devem ser notificadas.
	 */
	public NotaFiscal constroi() {
		NotaFiscal nf = new NotaFiscal(razaoSocial, cnpj, data, valorBruto, impostos, todosItens, observacoes);

		// Executa todas as acoes que est�o na lista
		for (AcaoAposGerarNota acao : acoes) {
			acao.executa(nf);
		}
		return nf;
	}
}
