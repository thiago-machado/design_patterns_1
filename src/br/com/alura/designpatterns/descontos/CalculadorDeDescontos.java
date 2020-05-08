package br.com.alura.designpatterns.descontos;

import br.com.alura.designpatterns.Orcamento;

/**
 * Agora que implementamos o Chain of Responsibility, temos cada uma das
 * responsabilidades separadas em uma classe, e uma forma de unir essa corrente
 * novamente. Veja a flexibilidade que o padrão nos deu: podemos montar a
 * corrente da forma como quisermos, e sem muitas complicações.
 * 
 * Mas precisamos de uma classe que monte essa corrente na ordem certa, com
 * todos os descontos necessários. Por isso que optamos pela classe
 * CalculadorDeDescontos. Ela poderia ter qualquer outro nome como
 * CorrenteDeDescontos, e assim por diante, mas fato é que em algum lugar do seu
 * código você precisará montar essa corrente.
 */
public class CalculadorDeDescontos {

	/*
	 * Se o orçamento atende a regra de um desconto, o mesmo já calcula o desconto.
	 * Caso contrário, ele passa para o "próximo" desconto, qualquer que seja esse
	 * próximo desconto.
	 * 
	 * Basta agora plugarmos todas essas classes juntas. Veja que um desconto recebe
	 * um "próximo". Para o desconto, pouco importa qual é o próximo desconto. Eles
	 * estão totalmente desacoplados um do outro!
	 * 
	 * Esses descontos formam como se fosse uma "corrente", ou seja, um ligado ao
	 * outro. Daí o nome do padrão de projeto: Chain of Responsibility.
	 */
	public double calcula(Orcamento orcamento) {

		Desconto d1 = new DescontoPorMaisDeCincoItens();
		Desconto d2 = new DescontoPorMaisDeQuinhentosReais();
		Desconto d3 = new DescontoPorVendaCasada();
		Desconto d4 = new SemDesconto();

		/*
		 * Caso a condição do primeiro desconto (d1) não seja satisfeita, chamará o
		 * próximo desconto (d2).
		 * 
		 * O mesmo se aplica aos demais descontos abaixo.
		 */
		d1.setProximo(d2);
		d2.setProximo(d3);
		d3.setProximo(d4);

		return d1.desconta(orcamento);
	}

}
