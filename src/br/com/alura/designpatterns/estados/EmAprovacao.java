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
			throw new RuntimeException("Desconto j� aplicado!");
		}

	}

	@Override
	public void aprova(Orcamento orcamento) {
		// Podemos ir de EM APROVA��O para APROVADO
		orcamento.setEstadoAtual(new Aprovado());
	}

	@Override
	public void reprova(Orcamento orcamento) {
		// Podemos ir de EM APROVA��O para REPROVADO
		orcamento.setEstadoAtual(new Reprovado());
	}

	@Override
	public void finaliza(Orcamento orcamento) {
		// Daqui n�o posso ir direto para finalizado
		throw new RuntimeException("Orcamento em aprova��o n�o podem ir para finalizado diretamente");
	}

}
