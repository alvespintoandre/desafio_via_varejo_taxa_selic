package br.com.viavarejo.desafio.model;

import java.math.BigDecimal;

public class SimulacaoCompraResponse {
	
	private int numeroParcela;
	private BigDecimal valorParcela;
	private BigDecimal taxaJuros;
	
	public int getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}
	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

}
