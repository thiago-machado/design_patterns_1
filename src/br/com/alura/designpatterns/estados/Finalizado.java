package br.com.alura.designpatterns.estados;

import br.com.alura.designpatterns.Orcamento;

public class Finalizado implements EstadoDeUmOrcamento {

	@Override
	public void aplicaDescontoExtra(Orcamento orcamento) {
		throw new RuntimeException("Or�amentos finalizados n�o recebem desconto extra!");
	}

	@Override
	public void aprova(Orcamento orcamento) {
		// N�o pode ser aprovado
        throw new RuntimeException("Or�amento j� est� finalizado!");
	}

	@Override
	public void reprova(Orcamento orcamento) {
		// N�o pode ser reprovado
        throw new RuntimeException("Or�amento j� est� finalizado!");
	}

	@Override
	public void finaliza(Orcamento orcamento) {
		// N�o pode ser finalizado novamente
        throw new RuntimeException("Or�amento j� est� finalizado novamente!");
	}

}
