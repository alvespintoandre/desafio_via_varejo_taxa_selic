package br.com.viavarejo.desafio.service;

import java.util.List;

import br.com.viavarejo.desafio.model.SimulacaoCompraRequest;
import br.com.viavarejo.desafio.model.SimulacaoCompraResponse;

public interface SimulacaoCompraService {
	
	List<SimulacaoCompraResponse> simularCompra(final SimulacaoCompraRequest simulacaoCompraRequest);
	
}
