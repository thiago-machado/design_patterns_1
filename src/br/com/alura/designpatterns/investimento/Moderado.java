package br.com.alura.designpatterns.investimento;

import java.util.Random;

public class Moderado implements Investimento {
	private Random random;
	private double percentual;

	public Moderado() {
		this.random = new Random();
	}

	public double getPercentual() {
		return percentual;
	}

	/*
	 * Tem 50% de chances de retornar 2.5%, e 50% de chances de retornar 0.7%;
	 */
	public double calcula(Conta conta) {
		if (random.nextInt(2) == 0) {
			percentual = 0.025;
			return conta.getSaldo() * percentual;
		} else {
			percentual = 0.007;
			return conta.getSaldo() * percentual;
		}
	}

}
