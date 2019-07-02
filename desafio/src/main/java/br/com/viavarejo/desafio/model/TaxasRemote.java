package br.com.viavarejo.desafio.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaxasRemote {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date data;
	private BigDecimal valor;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
