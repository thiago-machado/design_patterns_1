package br.com.alura.designpatterns.notafiscal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe responsável por pedir todos os parâmetros necessários, montar o que
 * precisa e enfim produzir uma Nota Fiscal, de forma com que o cliente dessa
 * classe consiga entender o que está acontecendo.
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
	 * Repare que, para o método constroi(), pouco importa qual ação está sendo
	 * executada. Ele simplesmente dispara uma, ou mais ações.
	 * 
	 * Com essa informação, podemos reescrever nosso método, fazendo com que ele
	 * dispare uma lista de ações, independente de quais ações.
	 * 
	 * Repare que agora o método constroi() não se importa com quais ações serão
	 * executadas. Ele simplesmente notifica essas ações. Precisamos só preencher
	 * essa lista com as ações que devem ser notificadas.
	 */
	public NotaFiscal constroi() {
		NotaFiscal nf = new NotaFiscal(razaoSocial, cnpj, data, valorBruto, impostos, todosItens, observacoes);

		// Executa todas as acoes que estão na lista
		for (AcaoAposGerarNota acao : acoes) {
			acao.executa(nf);
		}
		return nf;
	}
}
