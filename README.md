# Stockify – API REST de Controle de Estoque

API REST desenvolvida para gerenciar produtos de um **comércio físico**, permitindo controle de estoque de forma simples, segura e escalável.

Este projeto foi criado com foco em **boas práticas**, **organização de código** e **padrões utilizados no mercado**, servindo tanto como estudo quanto como **portfólio profissional**.


##  Contexto do Projeto

O **Stockify** representa o back-end de um sistema interno de loja física, responsável por:

- Cadastro de produtos
- Atualização de informações
- Controle de estoque
- Desativação lógica de produtos
- Listagem paginada e busca por nome

A API foi pensada para ser consumida por um front-end (ex: Angular) ou outros serviços.

##  Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Bean Validation
- **PostgreSQL**
- **Maven**
- **Hibernate**
- **REST API**
- **Arquitetura em camadas**
- **DTOs com** `record`
- **BigDecimal para valores monetários**


## Arquitetura

O projeto segue separação clara de responsabilidades:

````
com.marcos.stockify
├── api
│   └── controller
├── domain
│   ├── dto
│   ├── entity
│   ├── mapper
│   ├── repository
│   └── service
└── infrastructure
````

## Modelo de Produto

- **id:** Identificador único
- **name:** Nome do produto
- **price:** Preço (BigDecimal)
- **quantity:** Quantidade em estoque
- **active:** Indica se o produto está ativo

A exclusão é **lógica**, preservando histórico no banco de dados.

# 🔗 Endpoints Disponíveis
### ➕ Criar produto
`` POST /products ``
#### Request body
````json
{
  "name": "Caderno",
  "price": 18.90,
  "quantity": 50
}
````
#### Response
`` 201 Created ``

### ✏️ Atualizar produto
`` PUT /products/{id} ``

### ❌ Desativar produto (exclusão lógica)
`` DELETE /products/{id} ``
#### Response
`` 204 No Content ``

### 📄 Listar produtos ativos (paginado)

### 🔍 Buscar produtos por nome

`` GET /products/search?name=caderno&page=0&size=10 ``