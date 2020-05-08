package br.com.alura.designpatterns.impostos.model;

import br.com.alura.designpatterns.Orcamento;

public abstract class Imposto {

	protected final Imposto outroImposto;
	
    public Imposto(Imposto outroImposto) {
        this.outroImposto = outroImposto;
    }
    
	public abstract double calcula(Orcamento orcamento);
	protected abstract double calculoDoOutroImposto(Orcamento orcamento);
}
