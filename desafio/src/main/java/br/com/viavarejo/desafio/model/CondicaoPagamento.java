package br.com.viavarejo.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CondicaoPagamento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal valorEntrada;
	private int qtdeParcelas;
	
	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public int getQtdeParcelas() {
		return qtdeParcelas;
	}
	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

}
