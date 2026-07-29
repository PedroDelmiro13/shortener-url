# Shortener URL

Encurtador de URLs desenvolvido em Java com Spring Boot, utilizando MongoDB como banco de dados e Docker para containerização.

##  Tecnologias

- Java
- Spring Boot
- MongoDB
- Docker / Docker Compose
- Maven

##  Pré-requisitos

- JDK instalado
- Docker e Docker Compose instalados

##  Como executar

1. Clone o repositório:
```bash
git clone https://github.com/PedroDelmiro13/shortener-url.git
cd shortener-url
```

2. Suba o container do MongoDB com Docker Compose:
```bash
cd docker
docker-compose up -d
```

3. Volte para a raiz do projeto e rode a aplicação:
```bash
cd ..
./mvnw spring-boot:run
```

##  Estrutura do projeto

```
shortener-url/
├── docker/                  # Configuração do container (docker-compose)
├── src/main/java/...        # Código-fonte da aplicação
│   ├── controller/           # Controllers e DTOs da API
│   ├── model/                 # Modelos de dados
│   └── repository/            # Repositórios (MongoDB)
└── pom.xml                   # Dependências do projeto (Maven)
```
