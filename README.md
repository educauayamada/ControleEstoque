# 📦 Controle de Estoque

API REST para gerenciamento de **produtos, categorias e movimentações de estoque**, desenvolvida com Java e Spring Boot.

## Tecnologias

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Flyway
* Maven
* Lombok
* Bean Validation

## O que foi estudado

* Desenvolvimento de APIs REST
* Arquitetura em camadas (Controller, Service e Repository)
* DTOs
* Relacionamentos com JPA
* Validação de dados
* Tratamento global de exceções
* Regras de negócio para movimentação de estoque
* Persistência com PostgreSQL
* Versionamento do banco com Flyway

## Endpoints principais

* `/categorias` — CRUD de categorias
* `/produtos` — CRUD de produtos e busca por nome
* `/produtos/estoque-baixo` — consulta produtos abaixo de um limite de estoque
* `/movimentacoes` — registro e consulta de movimentações
* `/movimentacoes/produto/{produtoId}` — histórico de movimentações por produto

## Como executar

Configure as variáveis de ambiente do PostgreSQL:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Execute:

```bash
mvn spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```
