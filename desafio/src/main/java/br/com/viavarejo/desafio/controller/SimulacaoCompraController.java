package br.com.viavarejo.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viavarejo.desafio.model.SimulacaoCompraRequest;
import br.com.viavarejo.desafio.model.SimulacaoCompraResponse;
import br.com.viavarejo.desafio.service.SimulacaoCompraService;

@RestController
public class SimulacaoCompraController {
	
	@Autowired
	private SimulacaoCompraService simulacaoCompraService;
	
	@GetMapping(produces = "application/json")
	@RequestMapping(value="simular")
    public List<SimulacaoCompraResponse> calcularSimulacaoCompra(@RequestBody SimulacaoCompraRequest simulacaoCompraRequest){
		List<SimulacaoCompraResponse> listaSimulacaoCompraResponse =  simulacaoCompraService.simularCompra(simulacaoCompraRequest);
		return listaSimulacaoCompraResponse;
    }
}