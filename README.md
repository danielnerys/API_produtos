# API de Produtos e Categorias

API REST desenvolvida em Spring Boot para gerenciamento de produtos e categorias, com relacionamento entre entidades, controle de estoque transacional, tratamento de erros centralizado e cobertura de testes automatizados (unitários e de integração).

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **PostgreSQL** (banco de dados principal)
- **H2** (banco em memória para testes de integração)
- **Maven**
- **Lombok**
- **Bean Validation**
- **JUnit 5 + Mockito** (testes unitários)
- **MockMvc** (testes de integração)
- **Docker** (containerização do banco de dados)

## 📐 Arquitetura

O projeto segue uma arquitetura em camadas bem definida, com responsabilidades separadas:

```
Controller  →  Mapper  →  Service  →  Repository  →  Banco de Dados
```

- **Controller**: recebe requisições HTTP, aplica validação (`@Valid`) e define status codes.
- **Mapper**: converte DTOs em entidades e vice-versa (isolando essa responsabilidade do Controller).
- **Service**: concentra as regras de negócio e controla transações (`@Transactional`).
- **Repository**: acesso a dados via Spring Data JPA.
- **GlobalExceptionHandler**: centraliza o tratamento de exceções, padronizando as respostas de erro.

## 🗂️ Modelagem

- **Categoria** `1` ── `N` **Produto** (um-para-muitos / muitos-para-um via `@OneToMany` / `@ManyToOne`)
- Cada Produto pertence a uma única Categoria.

## 📋 Funcionalidades

### Categorias
| Método | Endpoint             | Descrição                                  |
|--------|-----------------------|---------------------------------------------|
| POST   | `/api/categorias`     | Cadastra uma nova categoria                  |
| GET    | `/api/categorias`     | Lista todas as categorias                    |

### Produtos
| Método | Endpoint                          | Descrição                                          |
|--------|-------------------------------------|-----------------------------------------------------|
| POST   | `/api/produtos`                    | Cadastra um novo produto                            |
| GET    | `/api/produtos?nome={nome}`        | Busca produtos por nome (parcial)                   |
| GET    | `/api/produtos/todos`              | Lista todos os produtos                             |
| PATCH  | `/api/produtos/{id}`               | Atualiza parcialmente um produto                     |
| DELETE | `/api/produtos/{id}`               | Remove um produto                                    |
| POST   | `/api/produtos/{id}/vender`        | Realiza a venda de um produto, reduzindo o estoque   |

## ✅ Regras de negócio

- Não é permitido cadastrar produtos ou categorias com nomes duplicados.
- A venda de um produto é uma operação transacional (`@Transactional`): se a quantidade solicitada for maior que o estoque disponível, a operação é revertida por completo e uma exceção é lançada.
- A atualização de produto (`PATCH`) é parcial: apenas os campos enviados na requisição são alterados.

## ⚠️ Tratamento de erros

Todas as exceções de negócio são centralizadas em um `GlobalExceptionHandler`, retornando respostas padronizadas no formato:

```json
{
  "mensagem": "Descrição do erro"
}
```

| Cenário                                   | Status HTTP |
|--------------------------------------------|-------------|
| Recurso já existente (nome duplicado)       | 409 Conflict |
| Recurso não encontrado                      | 404 Not Found |
| Estoque insuficiente para venda             | 409 Conflict |
| Erro de validação (campos inválidos)        | 400 Bad Request |

## 🧪 Testes

O projeto conta com dois níveis de teste automatizado:

- **Testes unitários** (JUnit 5 + Mockito): validam as regras de negócio da camada de Service de forma isolada, utilizando mocks para as dependências.
- **Testes de integração** (`@SpringBootTest` + `MockMvc`): validam o fluxo completo (Controller → Service → Repository → Banco), utilizando um banco H2 em memória, isolado do ambiente de desenvolvimento.

Para rodar os testes:
```bash
mvn test
```

## 🐳 Como rodar o projeto localmente

### Pré-requisitos
- Java 21
- Maven
- Docker

### 1. Subir o banco de dados PostgreSQL via Docker
```bash
docker run --name produtos-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=senha123 -e POSTGRES_DB=produtos_db -p 5432:5432 -d postgres:16
```

### 2. Configurar o `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/produtos_db
spring.datasource.username=postgres
spring.datasource.password=senha123
spring.jpa.hibernate.ddl-auto=update
```

### 3. Rodar a aplicação
```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## 📦 Exemplo de uso

**Cadastrar uma categoria:**
```http
POST /api/categorias
Content-Type: application/json

{
  "nome": "Ferramentas de Corte"
}
```

**Cadastrar um produto:**
```http
POST /api/produtos
Content-Type: application/json

{
  "nome": "Tesoura Profissional",
  "preco": 45.90,
  "quantidadeEstoque": 10,
  "categoriaId": "uuid-da-categoria"
}
```

**Atualizar parcialmente um produto:**
```http
PATCH /api/produtos/{id}
Content-Type: application/json

{
  "preco": 39.90
}
```

**Vender um produto:**
```http
POST /api/produtos/{id}/vender
Content-Type: application/json

{
  "quantidade": 2
}
```

## 👤 Autor

Daniel Nery Oliveira
[LinkedIn](https://linkedin.com/in/danielnerys/) | [GitHub](https://github.com/danielnerys)
