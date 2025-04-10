# API de Cadastro de Produtos e Exportação de Relatórios

Este projeto é uma API REST desenvolvida em **Java com Spring Boot**, com o objetivo de realizar o cadastro de produtos e permitir a exportação dos dados para um arquivo Excel (.xlsx).

## :wrench: Funcionalidades

- CRUD de produtos
- Mapeamento Objeto-Relacional com JPA/Hibernate
- Exportação de relatórios em Excel utilizando Apache POI
- Integração com banco de dados MySQL

## :computer: Tecnologias utilizadas

<div align="center">
    <a href="https://dev.java/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"></a>
    <a href="https://spring.io/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Spring-6DB33F.svg?style=for-the-badge&logo=Spring&logoColor=white"></a>
    <a href="https://hibernate.org/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Hibernate-59666C.svg?style=for-the-badge&logo=Hibernate&logoColor=white"></a>
    <a href="https://maven.apache.org/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=for-the-badge&logo=Apache-Maven&logoColor=white"></a>
    <a href="https://poi.apache.org/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Apache%20POI-D22128.svg?style=for-the-badge&logo=Apache&logoColor=white"></a>
    <a href="https://www.mysql.com/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-badge&logo=MySQL&logoColor=white"></a>
    <a href="https://www.docker.com/" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white"></a>
</div>

## :gear: Como executar o projeto

Pré-requisitos: 
- Java 21
- Maven
- Docker e Docker Compose

```bash
# Clonar o repositório
git clone https://github.com/xxzidanilloxx/report-export-api

# Acessar a pasta do projeto
cd report-export-api

# Subir o container MySQL
docker-compose up -d

# Instalar as dependências
mvn clean install

# Executar o projeto
mvn spring-boot:run
```

## :checkered_flag: Endpoints

Endpoints disponíveis para o gerenciamento de produtos e exportação de relatórios:

### `POST localhost:8080/api/products`
Cadastra um novo produto com os dados fornecidos.

#### Exemplo do corpo da requisição (JSON):
```JSON
{
    "name": "Teste",
    "brand": "Teste",
    "description": "Teste",
    "category": "SMARTPHONES",
    "stockQuantity": 500,
    "price": 1299.99
}
```

#### Demonstração do envio da requisição no Postman:
![post-products](https://github.com/user-attachments/assets/30376115-973e-47e0-9e11-5aa5ad84e12c)

### `GET localhost:8080/api/products`
Retorna todos os produtos cadastrados.

#### Demonstração do envio da requisição no Postman:
![get-products](https://github.com/user-attachments/assets/3b60866b-f73d-43e0-929b-fc093805c3b1)

### `PUT localhost:8080/api/products/{id}`
Atualiza os dados de um produto cadastrado a partir do id fornecido.

#### Exemplo do corpo da requisição (JSON):
```JSON
{
    "name": "Teste",
    "brand": "Teste",
    "description": "Teste",
    "category": "SMARTPHONES",
    "stockQuantity": 499,
    "price": 1249.99
}
```

#### Demonstração do envio da requisição no Postman:
![put-products](https://github.com/user-attachments/assets/981559ca-266d-4d56-92ba-e2cea011d8a2)

### `GET localhost:8080/api/reports`
Extrai um arquivo Excel (.xlsx) com os dados dos produtos.

#### Demonstração do download do relatório no navegador:
![get-reports](https://github.com/user-attachments/assets/dc91833c-cd05-4c6b-9ad5-b54891ad3bc6)

## :card_index_dividers: Estrutura do Projeto
A estrutura foi organizada em camadas para facilitar a organização e seguir boas práticas no desenvolvimento.

```plaintext
report-export-api/
└── src/
    └── main/
        └── java/
            └── br.gov.sp.cps.api.pixel/
                ├── controller/
                │   ├── ProductController
                │   └── ReportController
                ├── dto/
                │   ├── ProductRequestDTO
                │   └── ProductResponseDTO
                ├── entity/
                │   └── Product
                ├── enumeration/
                │   └── Category
                ├── mapper/
                │   └── ProductMapper
                ├── repository/
                │   └── ProductRepository
                ├── service/
                │   ├── ProductService
                │   └── ReportService
                └── ReportExportApiApplication.java
```

## :page_facing_up: Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais informações.

## :bust_in_silhouette: Autor

Danillo Wesley da Costa Silva

https://www.linkedin.com/in/danillowesley
