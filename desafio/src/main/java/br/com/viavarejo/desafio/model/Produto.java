package br.com.viavarejo.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private BigDecimal valor;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
