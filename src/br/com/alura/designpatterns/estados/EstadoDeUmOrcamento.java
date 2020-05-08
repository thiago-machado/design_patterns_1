package br.com.alura.designpatterns.estados;

import br.com.alura.designpatterns.Orcamento;

/**
 * Todas as classes que implementarem essa interface são possíveis estados de um
 * orçamento. Todos esses estados devem também dar o desconto extra para o
 * orçamento, de acordo com sua regra de negócio.
 * 
 * Repare que todas as implementações recebem um Orcamento. Isso é necessário já
 * que precisamos alterar o orçamento, e as classes de estado precisam
 * enxergá-lo.
 * 
 * Precisamos agora fazer com que o Orcamento use essas classes para representar
 * seu estado interno. Além disso, precisamos fazer com que a classe que
 * representa o estado do orçamento, como as classes Aprovado, Reprovado e
 * EmAprovacao, respondam pelo comportamento de descontoExtra:
 * 
 * Podemos incrementar ainda mais nossa classe Orcamento, implementando a troca
 * de estados. Por exemplo, se o orçamento está no estado EM APROVAÇÃO, ele pode
 * ir apenas para os estados APROVADO e REPROVADO. Dos estados APROVADO e
 * REPROVADO, podemos ir apenas para o estado FINALIZADO.
 * 
 * Representar isso em código procedural é difícil. Precisaríamos de várias
 * condições (leia-se ifs), para alcançar o resultado esperado. O State nos
 * ajuda nesse problema também. Basta representarmos as possíveis trocas em
 * todas as classes que representam o estado.
 * 
 * Cada estado, por sua vez toma a decisão correta, e muda o estado do
 * orçamento.
 * 
 * Veja o estado EmAprovacao. Observe o método aprova(): do estado EM APROVAÇÃO,
 * pode-se ir para o estado APROVADO. É isso que a classe implementa. Ela muda o
 * estado do orçamento para APROVADO. Agora repare no método finaliza(). Não
 * podemos ir para o estado FINALIZADO e por isso o método lança a exceção.
 * 
 * O Orcamento por sua vez, sempre que recebe uma ação que depende do seu
 * estado, repassa a chamada para o seu estado atual.
 * 
 * Repare que a criação de um novo "Estado" é fácil: basta criar uma nova classe
 * que implementa EstadoDeUmOrcamento e ela funcionará sem muito esforço!
 * 
 * Essa é a grande graça da orientação a objetos! Classes pequenas e com
 * responsabilidades bem definidas. E, com a ajuda do polimorfismo, podemos
 * juntar esses comportamentos e formar um sistema maior.
 * 
 * @author thiago.machado
 *
 */
public interface EstadoDeUmOrcamento {
	void aplicaDescontoExtra(Orcamento orcamento);

	void aprova(Orcamento orcamento);

	void reprova(Orcamento orcamento);

	void finaliza(Orcamento orcamento);
}
