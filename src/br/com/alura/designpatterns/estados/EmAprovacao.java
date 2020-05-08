package br.com.alura.designpatterns.estados;

import br.com.alura.designpatterns.Orcamento;

public class EmAprovacao implements EstadoDeUmOrcamento {

	private boolean descontoAplicado;

	@Override
	public void aplicaDescontoExtra(Orcamento orcamento) {
		if (!descontoAplicado) {
			orcamento.setBaseCalculo(orcamento.getBaseCalculo() * 0.05);
			descontoAplicado = true;
		} else {
			throw new RuntimeException("Desconto já aplicado!");
		}

	}

	@Override
	public void aprova(Orcamento orcamento) {
		// Podemos ir de EM APROVAÇÃO para APROVADO
		orcamento.setEstadoAtual(new Aprovado());
	}

	@Override
	public void reprova(Orcamento orcamento) {
		// Podemos ir de EM APROVAÇÃO para REPROVADO
		orcamento.setEstadoAtual(new Reprovado());
	}

	@Override
	public void finaliza(Orcamento orcamento) {
		// Daqui não posso ir direto para finalizado
		throw new RuntimeException("Orcamento em aprovação não podem ir para finalizado diretamente");
	}

}
