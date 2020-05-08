package br.com.alura.designpatterns.notafiscal;

public class EnviadorDeSMS implements AcaoAposGerarNota {
	public void executa(NotaFiscal notaFiscal) {
		System.out.println("enviando por sms");
	}
}
