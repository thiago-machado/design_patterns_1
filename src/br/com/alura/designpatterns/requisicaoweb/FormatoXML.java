package br.com.alura.designpatterns.requisicaoweb;

import br.com.alura.designpatterns.investimento.Conta;

public class FormatoXML implements Resposta {

	private Resposta resposta;

	@Override
	public String responde(Requisicao req, Conta conta) {
		if(req.getFormato().equals(Formato.XML)) {
			return "<conta><titular>" + conta.getTitular() + "</titular><saldo>" + conta.getSaldo() + "</saldo></conta>";
		} else {
			return resposta.responde(req, conta);
		}
		
	}

	@Override
	public void setProxima(Resposta resposta) {
		this.resposta = resposta;
	}

}
