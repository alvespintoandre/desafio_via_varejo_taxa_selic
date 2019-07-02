Abrir o postman

Incluir a url: http://localhost:8080/simular

Selecionar o metodo get, inserir o header o key Content-Type, value application/json


Incluir o json abaixo no body:

{ 
"produto": { "codigo": 102030, "nome": "Corolla", "valor": 8000.00 }, 
	
  "condicaoPagamento": { "valorEntrada": 2000.00, "qtdeParcelas": 12 } 
}


Observações: Há uma chamada remota para calcular em tempo real o valor da taxa, verificando o acumulado dos últimos 30 dias.