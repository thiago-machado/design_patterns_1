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
			throw new RuntimeException("Desconto j� aplicado!");
		}
	}
	
	@Override
	public void aprova(Orcamento orcamento) {
		// J� estou em aprova��o
        throw new RuntimeException("Or�amento j� est� em estado de aprova��o");
	}

	@Override
	public void reprova(Orcamento orcamento) {
		// N�o pode ser reprovado agora!
        throw new RuntimeException("Or�amento est� em estado de aprova��o e n�o pode ser reprovado");
	}

	@Override
	public void finaliza(Orcamento orcamento) {
		// Daqui posso ir para o estado de finalizado
		orcamento.setEstadoAtual(new Finalizado());
	}
	
	

}
