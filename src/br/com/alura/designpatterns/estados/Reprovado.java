package br.com.alura.designpatterns.estados;

import br.com.alura.designpatterns.Orcamento;

public class Reprovado implements EstadoDeUmOrcamento {

	@Override
	public void aplicaDescontoExtra(Orcamento orcamento) {
		throw new RuntimeException("Or�amentos reprovados n�o recebem desconto extra!");
	}

	@Override
	public void aprova(Orcamento orcamento) {
		// N�o pode ser aprovado
        throw new RuntimeException("Or�amento j� est� reprovado!");
	}

	@Override
	public void reprova(Orcamento orcamento) {
		// N�o pode ser reprovado novamente
        throw new RuntimeException("Or�amento j� est� reprovado novamente!");
	}

	@Override
	public void finaliza(Orcamento orcamento) {
		// Daqui posso ir para finalizado
		orcamento.setEstadoAtual(new Finalizado());
	}

}
