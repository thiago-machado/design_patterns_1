package br.com.alura.designpatterns.requisicaoweb;

import br.com.alura.designpatterns.investimento.Conta;

public class FormatoNenhum implements Resposta {

	@Override
	public String responde(Requisicao req, Conta conta) {
		return "titular,saldo\n" + conta.getTitular() + "," + conta.getSaldo();
	}

	@Override
	public void setProxima(Resposta resposta) {}

}
