# API SIMPLES PARA GERENCIAMENTO DE PRODUTOS
#### Em desenvolvimento
# Stacks utilizadas
### Java 21
### SpringBoot
### Spring DATA JPA
### PostgreSQL
### Maven
### Bean Validation
### DOCKER

# Para testar:  
## Rode
       docker run --name produtos-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=senha123 -e POSTGRES_DB=produtos_db -p 5432:5432 -d postgres:16

## application.properties<br>
spring.application.name=product<br>
spring.datasource.url=jdbc:postgresql://localhost:5432/produtos_db<br>
spring.datasource.username=postgres<br>
spring.datasource.password=senha123<br>
spring.jpa.hibernate.ddl-auto=update<br>
spring.jpa.show-sql=true<br>
spring.jpa.properties.hibernate.format_sql=true<br>

##Endpoint http://localhost:8080/api/produtos

<img src="https://exemplo.com](https://github.com/danielnerys/API_produtos/blob/main/images/API_status_201.png" alt="retorno com status 201">

<img src="https://exemplo.com](https://github.com/danielnerys/API_produtos/blob/main/images/API_status_201.png" alt="retorno com status 409">

