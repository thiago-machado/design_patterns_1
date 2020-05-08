package br.com.alura.designpatterns.investimento;

public class Conservador implements Investimento {
	public double calcula(Conta conta) {
		return conta.getSaldo() * 0.008;
	}
}
