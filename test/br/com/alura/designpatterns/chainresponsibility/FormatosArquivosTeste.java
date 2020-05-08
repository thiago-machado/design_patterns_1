package br.com.alura.designpatterns.chainresponsibility;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.alura.designpatterns.investimento.Conta;
import br.com.alura.designpatterns.requisicaoweb.Formato;
import br.com.alura.designpatterns.requisicaoweb.ProcessadorDeFormato;
import br.com.alura.designpatterns.requisicaoweb.Requisicao;

public class FormatosArquivosTeste {

	private Conta conta;
	
	@Before
	public void inicializar() {
		conta = new Conta();
		conta.setTitular("Elaine Araújo");
		conta.deposita(89120.87);
	}
	
	@Test
	public void processarFormatoXML() {
		String respostaEsperada = "<conta><titular>Elaine Araújo</titular><saldo>89120.87</saldo></conta>";
		ProcessadorDeFormato processadorDeFormato = new ProcessadorDeFormato();
		String retorno = processadorDeFormato.processar(conta, new Requisicao(Formato.XML));
		assertEquals(respostaEsperada, retorno);
	}
	
	@Test
	public void processarFormatoCSV() {
		String respostaEsperada = "titular;saldo\nElaine Araújo;89120.87";
		ProcessadorDeFormato processadorDeFormato = new ProcessadorDeFormato();
		String retorno = processadorDeFormato.processar(conta, new Requisicao(Formato.CSV));
		assertEquals(respostaEsperada, retorno);
	}
	
	@Test
	public void processarFormatoPorcento() {
		String respostaEsperada = "titular%saldo\nElaine Araújo%89120.87";
		ProcessadorDeFormato processadorDeFormato = new ProcessadorDeFormato();
		String retorno = processadorDeFormato.processar(conta, new Requisicao(Formato.PORCENTO));
		assertEquals(respostaEsperada, retorno);
	}
}
