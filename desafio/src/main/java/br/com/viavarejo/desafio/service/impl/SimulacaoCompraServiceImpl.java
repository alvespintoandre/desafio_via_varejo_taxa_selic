package br.com.viavarejo.desafio.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.viavarejo.desafio.model.SimulacaoCompraRequest;
import br.com.viavarejo.desafio.model.SimulacaoCompraResponse;
import br.com.viavarejo.desafio.model.TaxasRemote;
import br.com.viavarejo.desafio.service.SimulacaoCompraService;
import br.com.viavarejo.desafio.util.SimulacaoCompraConstantes;

@Service
public class SimulacaoCompraServiceImpl implements SimulacaoCompraService {

	BigDecimal taxaJuros = BigDecimal.ZERO;
	BigDecimal taxaJurosDefault = new BigDecimal("1.5");
	final String uri = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados?formato=json";

	@Override
	public List<SimulacaoCompraResponse> simularCompra(SimulacaoCompraRequest simulacaoCompraRequest) {
		
		List<SimulacaoCompraResponse> listaSimulacaoCompraResponse = new ArrayList<>();
		int quantidadeParcelas = simulacaoCompraRequest.getCondicaoPagamento().getQtdeParcelas();
		BigDecimal valorTotal = calcularJuros(simulacaoCompraRequest);

		for (int i = 1; i <= quantidadeParcelas; i++) {
			SimulacaoCompraResponse simulacaoCompraResponse = new SimulacaoCompraResponse();
			simulacaoCompraResponse.setNumeroParcela(i);
			simulacaoCompraResponse.setTaxaJuros(taxaJuros.multiply(BigDecimal.valueOf(100)));
			simulacaoCompraResponse
					.setValorParcela(valorTotal.divide(BigDecimal.valueOf(quantidadeParcelas), MathContext.DECIMAL128));
			listaSimulacaoCompraResponse.add(simulacaoCompraResponse);
		}
		return listaSimulacaoCompraResponse;
	}

	private BigDecimal calcularJuros(SimulacaoCompraRequest simulacaoCompraRequest) {
		int quantidadeParcelas = simulacaoCompraRequest.getCondicaoPagamento().getQtdeParcelas();
		BigDecimal resto = simulacaoCompraRequest.getProduto().getValor()
				.subtract(simulacaoCompraRequest.getCondicaoPagamento().getValorEntrada());
		
		if (quantidadeParcelas > SimulacaoCompraConstantes.QUANTIDADE_PARCELAS_CALCULO_JUROS) {
			try {
				taxaJuros = getTaxasRemote();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			taxaJuros = taxaJurosDefault.divide(new BigDecimal(100));
		}
		return resto.multiply((taxaJuros.add(new BigDecimal("1.0"))).pow(quantidadeParcelas));
	}
	
	private BigDecimal getTaxasRemote() throws JsonParseException, JsonMappingException, IOException {
	    RestTemplate restTemplate = new RestTemplate();
	    String resultRemote = restTemplate.getForObject(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		TaxasRemote[] converter = mapper.readValue(resultRemote, TaxasRemote[].class);
		List<TaxasRemote> listaTaxasRemote = Arrays.asList(mapper.readValue(resultRemote, TaxasRemote[].class));
		BigDecimal valorTaxaRemote = BigDecimal.ZERO;
        Date dataFinal = new Date();
        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(dataFinal);
        int numeroDiasParaSubtrair = -30;
        calendarData.add(Calendar.DATE,numeroDiasParaSubtrair);
        Date dataInicial = calendarData.getTime();
        int quantidadeTaxas = 0;
		for(TaxasRemote taxa : listaTaxasRemote) {
			if(taxa.getData().after(dataInicial) && taxa.getData().before(dataFinal)) {
				valorTaxaRemote = valorTaxaRemote.add(taxa.getValor());
				quantidadeTaxas++;
			}
		}
	    return valorTaxaRemote.divide(new BigDecimal(quantidadeTaxas));
	}
}