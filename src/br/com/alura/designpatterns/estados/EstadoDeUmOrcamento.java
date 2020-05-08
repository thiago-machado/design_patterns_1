package br.com.alura.designpatterns.estados;

import br.com.alura.designpatterns.Orcamento;

/**
 * Todas as classes que implementarem essa interface s�o poss�veis estados de um
 * or�amento. Todos esses estados devem tamb�m dar o desconto extra para o
 * or�amento, de acordo com sua regra de neg�cio.
 * 
 * Repare que todas as implementa��es recebem um Orcamento. Isso � necess�rio j�
 * que precisamos alterar o or�amento, e as classes de estado precisam
 * enxerg�-lo.
 * 
 * Precisamos agora fazer com que o Orcamento use essas classes para representar
 * seu estado interno. Al�m disso, precisamos fazer com que a classe que
 * representa o estado do or�amento, como as classes Aprovado, Reprovado e
 * EmAprovacao, respondam pelo comportamento de descontoExtra:
 * 
 * Podemos incrementar ainda mais nossa classe Orcamento, implementando a troca
 * de estados. Por exemplo, se o or�amento est� no estado EM APROVA��O, ele pode
 * ir apenas para os estados APROVADO e REPROVADO. Dos estados APROVADO e
 * REPROVADO, podemos ir apenas para o estado FINALIZADO.
 * 
 * Representar isso em c�digo procedural � dif�cil. Precisar�amos de v�rias
 * condi��es (leia-se ifs), para alcan�ar o resultado esperado. O State nos
 * ajuda nesse problema tamb�m. Basta representarmos as poss�veis trocas em
 * todas as classes que representam o estado.
 * 
 * Cada estado, por sua vez toma a decis�o correta, e muda o estado do
 * or�amento.
 * 
 * Veja o estado EmAprovacao. Observe o m�todo aprova(): do estado EM APROVA��O,
 * pode-se ir para o estado APROVADO. � isso que a classe implementa. Ela muda o
 * estado do or�amento para APROVADO. Agora repare no m�todo finaliza(). N�o
 * podemos ir para o estado FINALIZADO e por isso o m�todo lan�a a exce��o.
 * 
 * O Orcamento por sua vez, sempre que recebe uma a��o que depende do seu
 * estado, repassa a chamada para o seu estado atual.
 * 
 * Repare que a cria��o de um novo "Estado" � f�cil: basta criar uma nova classe
 * que implementa EstadoDeUmOrcamento e ela funcionar� sem muito esfor�o!
 * 
 * Essa � a grande gra�a da orienta��o a objetos! Classes pequenas e com
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
