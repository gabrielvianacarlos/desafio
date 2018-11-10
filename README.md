# desafio

Essa aplicação e uma API REST que tem como a gestão de boletos.
As operações disponibilizadas são:
* Criar boleto
  * Cria um novo boleto com o status pendente (PENDING)
* Listar todos os boletos cadastrados
* Recuperar os detalhes de um boleto
  * Essa funcionalidade também calcula uma multa caso o pagamento esteja atrasada e retorna para o consumidor
* Pagar um boleto
  * Altera o status do boleto para pago (PAID)
* Cancelar um boleto
  * Altera o status do boleto para cancelado (CANCELED)

### Executando a aplicação

Para rodar o projeto, acessar o diretório raiz da aplicação e executar o comando: _mvn spring-boot:run_

## Executando os testes unitários

_mvn clean compile test_

## Para ver a definição da API
[http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)

## Para testar a API via Swagger
[http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)
* Ver bank-slip-contrller
