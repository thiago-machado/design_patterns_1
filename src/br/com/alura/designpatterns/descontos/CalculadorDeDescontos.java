package br.com.alura.designpatterns.descontos;

import br.com.alura.designpatterns.Orcamento;

/**
 * Agora que implementamos o Chain of Responsibility, temos cada uma das
 * responsabilidades separadas em uma classe, e uma forma de unir essa corrente
 * novamente. Veja a flexibilidade que o padr�o nos deu: podemos montar a
 * corrente da forma como quisermos, e sem muitas complica��es.
 * 
 * Mas precisamos de uma classe que monte essa corrente na ordem certa, com
 * todos os descontos necess�rios. Por isso que optamos pela classe
 * CalculadorDeDescontos. Ela poderia ter qualquer outro nome como
 * CorrenteDeDescontos, e assim por diante, mas fato � que em algum lugar do seu
 * c�digo voc� precisar� montar essa corrente.
 */
public class CalculadorDeDescontos {

	/*
	 * Se o or�amento atende a regra de um desconto, o mesmo j� calcula o desconto.
	 * Caso contr�rio, ele passa para o "pr�ximo" desconto, qualquer que seja esse
	 * pr�ximo desconto.
	 * 
	 * Basta agora plugarmos todas essas classes juntas. Veja que um desconto recebe
	 * um "pr�ximo". Para o desconto, pouco importa qual � o pr�ximo desconto. Eles
	 * est�o totalmente desacoplados um do outro!
	 * 
	 * Esses descontos formam como se fosse uma "corrente", ou seja, um ligado ao
	 * outro. Da� o nome do padr�o de projeto: Chain of Responsibility.
	 */
	public double calcula(Orcamento orcamento) {

		Desconto d1 = new DescontoPorMaisDeCincoItens();
		Desconto d2 = new DescontoPorMaisDeQuinhentosReais();
		Desconto d3 = new DescontoPorVendaCasada();
		Desconto d4 = new SemDesconto();

		/*
		 * Caso a condi��o do primeiro desconto (d1) n�o seja satisfeita, chamar� o
		 * pr�ximo desconto (d2).
		 * 
		 * O mesmo se aplica aos demais descontos abaixo.
		 */
		d1.setProximo(d2);
		d2.setProximo(d3);
		d3.setProximo(d4);

		return d1.desconta(orcamento);
	}

}
