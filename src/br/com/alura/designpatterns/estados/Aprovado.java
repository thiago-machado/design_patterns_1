package br.com.alura.designpatterns.estados;

import br.com.alura.designpatterns.Orcamento;

public class Aprovado implements EstadoDeUmOrcamento {

	private boolean descontoAplicado;

	@Override
	public void aplicaDescontoExtra(Orcamento orcamento) {
		if (!descontoAplicado) {
			orcamento.setBaseCalculo(orcamento.getBaseCalculo() * 0.02);
			descontoAplicado = true;
		} else {
			throw new RuntimeException("Desconto já aplicado!");
		}
	}
	
	@Override
	public void aprova(Orcamento orcamento) {
		// Já estou em aprovação
        throw new RuntimeException("Orçamento já está em estado de aprovação");
	}

	@Override
	public void reprova(Orcamento orcamento) {
		// Não pode ser reprovado agora!
        throw new RuntimeException("Orçamento está em estado de aprovação e não pode ser reprovado");
	}

	@Override
	public void finaliza(Orcamento orcamento) {
		// Daqui posso ir para o estado de finalizado
		orcamento.setEstadoAtual(new Finalizado());
	}
	
	

}
