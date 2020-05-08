package br.com.alura.designpatterns.investimento;

public class RealizadorDeInvestimentos {
	
	public void realiza(Conta conta, Investimento investimento) {
		double resultado = investimento.calcula(conta);
		conta.deposita(resultado * 0.75);
	}
}
