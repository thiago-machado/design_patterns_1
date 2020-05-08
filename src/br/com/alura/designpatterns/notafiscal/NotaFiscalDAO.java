package br.com.alura.designpatterns.notafiscal;

public class NotaFiscalDAO implements AcaoAposGerarNota {
	public void executa(NotaFiscal notaFiscal) {
		System.out.println("salvando no banco");
	}
}
