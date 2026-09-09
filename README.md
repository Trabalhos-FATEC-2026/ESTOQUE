# API de Estoque

Aplicacao Spring Boot simples para cadastrar produtos, consultar estoque e registrar vendas.

## Como executar

No terminal, dentro da pasta do projeto:

```bash
./mvnw spring-boot:run
```

A API sobe em:

```text
http://localhost:8080
```

Banco em memoria H2:

```text
http://localhost:8080/h2-console
```

Configuracao do H2:

```text
JDBC URL: jdbc:h2:mem:estoque-db
User Name: sa
Password:
```

## Endpoints para testar no Postman

### 1. Cadastrar produto

`POST http://localhost:8080/api/produtos`

Body JSON:

```json
{
  "nome": "Teclado",
  "qtd": 10
}
```

Resposta esperada:

```json
{
  "id": 1,
  "nome": "Teclado",
  "qtd": 10
}
```

### 2. Consultar estoque

`GET http://localhost:8080/api/produtos/1/estoque`

Resposta esperada:

```json
{
  "id": 1,
  "nome": "Teclado",
  "qtd": 10
}
```

### 3. Registrar venda

`POST http://localhost:8080/api/vendas`

Body JSON:

```json
{
  "produtoId": 1,
  "quantidade": 3
}
```

Resposta esperada:

```json
{
  "id": 1,
  "nome": "Teclado",
  "qtd": 7
}
```

## Fluxo de uso

1. Cadastre um produto.
2. Consulte o estoque pelo `id` retornado.
3. Registre uma venda para baixar a quantidade.
4. Consulte o estoque novamente para ver a quantidade atualizada.

## Possiveis erros

- Produto nao encontrado: retorna `404`.
- Quantidade invalida: retorna `400`.
- Estoque insuficiente: retorna `400`.