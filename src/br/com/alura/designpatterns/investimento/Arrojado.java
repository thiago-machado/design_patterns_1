package br.com.alura.designpatterns.investimento;

import java.util.Random;

public class Arrojado implements Investimento {
	
	private Random random;
	private double percentual;

	public Arrojado() {
		this.random = new Random();
	}
	
	public double getPercentual() {
		return percentual;
	}
	
	/*
	 * Tem 20% de chances de retornar 5%, 30% de chances de retornar 3%, e 50% de
	 * chances de retornar 0.6%.
	 */
	public double calcula(Conta conta) {
		int chute = random.nextInt(10);
		if (chute >= 0 && chute <= 1) {
			percentual = 0.05;
			return conta.getSaldo() * percentual;
		} else if (chute >= 2 && chute <= 4) {
			percentual = 0.03;
			return conta.getSaldo() * percentual;
		} else {
			percentual = 0.006;
			return conta.getSaldo() * percentual;
		}
	}

}
