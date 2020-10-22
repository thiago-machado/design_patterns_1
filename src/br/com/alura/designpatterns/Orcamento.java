package br.com.alura.designpatterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.designpatterns.descontos.Item;
import br.com.alura.designpatterns.estados.EmAprovacao;
import br.com.alura.designpatterns.estados.EstadoDeUmOrcamento;

/**
 * Comentário de teste
 * @author thiago.machado
 *
 */
public class Orcamento {

	private double baseCalculo;
	private List<Item> itens;
	private EstadoDeUmOrcamento estadoAtual;

	public Orcamento(double baseCalculo) {
		this.baseCalculo = baseCalculo;
		itens = new ArrayList<Item>();
		estadoAtual = new EmAprovacao();
	}

	public void setBaseCalculo(double baseCalculo) {
		this.baseCalculo = baseCalculo;
	}

	public double getBaseCalculo() {
		return baseCalculo;
	}

	public void setEstadoAtual(EstadoDeUmOrcamento estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	public EstadoDeUmOrcamento getEstadoAtual() {
		return estadoAtual;
	}

	public List<Item> getItens() {
		return Collections.unmodifiableList(itens);
	}

	public void adicionaItem(Item item) {
		itens.add(item);
	}

	public void aplicaDescontoExtra() {
		estadoAtual.aplicaDescontoExtra(this);
	}

	public void aprova() {
		estadoAtual.aprova(this);
	}

	public void reprova() {
		estadoAtual.reprova(this);
	}

	public void finaliza() {
		estadoAtual.finaliza(this);
	}

}
