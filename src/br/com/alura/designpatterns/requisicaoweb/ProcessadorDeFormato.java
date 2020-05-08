package br.com.alura.designpatterns.requisicaoweb;

import br.com.alura.designpatterns.investimento.Conta;

public class ProcessadorDeFormato {

	public String processar(Conta conta, Requisicao requisicao) {
		
		Resposta f1 = new FormatoXML();
		Resposta f2 = new FormatoCSV();
		Resposta f3 = new FormatoPorcentagem();
		Resposta f4 = new FormatoNenhum();
		
		f1.setProxima(f2);
		f2.setProxima(f3);
		f3.setProxima(f4);
		
		return f1.responde(requisicao, conta);
	}
}
