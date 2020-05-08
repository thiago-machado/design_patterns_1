package br.com.alura.designpatterns.requisicaoweb;

import br.com.alura.designpatterns.investimento.Conta;

public class FormatoCSV implements Resposta {

	private Resposta resposta;

	@Override
	public String responde(Requisicao req, Conta conta) {
		if(req.getFormato().equals(Formato.CSV)) {
			return "titular;saldo\n" + conta.getTitular() + ";" + conta.getSaldo();
		} else {
			return resposta.responde(req, conta);
		}
		
	}

	@Override
	public void setProxima(Resposta resposta) {
		this.resposta = resposta;
		
	}

}
