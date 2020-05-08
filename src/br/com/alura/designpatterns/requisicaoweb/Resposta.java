package br.com.alura.designpatterns.requisicaoweb;

import br.com.alura.designpatterns.investimento.Conta;

public interface Resposta {
	String responde(Requisicao req, Conta conta);
    void setProxima(Resposta resposta);
}
