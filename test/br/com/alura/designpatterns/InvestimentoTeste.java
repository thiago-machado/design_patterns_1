package br.com.alura.designpatterns;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.alura.designpatterns.investimento.Arrojado;
import br.com.alura.designpatterns.investimento.Conservador;
import br.com.alura.designpatterns.investimento.Conta;
import br.com.alura.designpatterns.investimento.Moderado;
import br.com.alura.designpatterns.investimento.RealizadorDeInvestimentos;

public class InvestimentoTeste {

	private Conta conta;
	
	@Before
	public void inicializar() {
		conta = new Conta();
		conta.deposita(3500);
	}
	
	@Test
	public void investimentoConservador() {
		RealizadorDeInvestimentos realizadorDeInvestimentos = new RealizadorDeInvestimentos();
		realizadorDeInvestimentos.realiza(conta, new Conservador());
		assertEquals(3521, conta.getSaldo(), 0.01);
	}
	
	@Test
	public void investimentoModerado() {
		
		Moderado moderado = new Moderado();
		RealizadorDeInvestimentos realizadorDeInvestimentos = new RealizadorDeInvestimentos();
		realizadorDeInvestimentos.realiza(conta, moderado);
		
		if(moderado.getPercentual() == 0.025) {
			assertEquals(3565.62, conta.getSaldo(), 0.01);
		} else {
			assertEquals(3518.37, conta.getSaldo(), 0.01);
		}
	}
	
	@Test
	public void investimentoArrojado() {
		
		Arrojado arrojado = new Arrojado();
		RealizadorDeInvestimentos realizadorDeInvestimentos = new RealizadorDeInvestimentos();
		realizadorDeInvestimentos.realiza(conta, arrojado);
		
		if(arrojado.getPercentual() == 0.05) {
			assertEquals(3631.25, conta.getSaldo(), 0.01);
		} else if (arrojado.getPercentual() == 0.03) {
			assertEquals(3578.75, conta.getSaldo(), 0.01);
		} else {
			assertEquals(3515.75, conta.getSaldo(), 0.01);
		}
	}
}
