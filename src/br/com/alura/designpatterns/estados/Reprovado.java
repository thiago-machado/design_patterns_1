package br.com.alura.designpatterns.estados;

import br.com.alura.designpatterns.Orcamento;

public class Reprovado implements EstadoDeUmOrcamento {

	@Override
	public void aplicaDescontoExtra(Orcamento orcamento) {
		throw new RuntimeException("Orçamentos reprovados não recebem desconto extra!");
	}

	@Override
	public void aprova(Orcamento orcamento) {
		// Não pode ser aprovado
        throw new RuntimeException("Orçamento já está reprovado!");
	}

	@Override
	public void reprova(Orcamento orcamento) {
		// Não pode ser reprovado novamente
        throw new RuntimeException("Orçamento já está reprovado novamente!");
	}

	@Override
	public void finaliza(Orcamento orcamento) {
		// Daqui posso ir para finalizado
		orcamento.setEstadoAtual(new Finalizado());
	}

}
