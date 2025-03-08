
<div align="center">
  <h1>CatalogManager - Desafio Backend Anota.ai</h1>
  <img src="https://skillicons.dev/icons?i=java,spring,mongo,rabbitmq,docker">
</div>
<br>

Este projeto é uma API desenvolvida em **Java** e **Spring Boot** como solução para o **Desafio Backend da Anota.ai**. O desafio propõe o seguinte:

Desenvolver uma API para gerenciar produtos e categorias de um catálogo online
com operações básicas de criar, ler, atualizar e excluir (CRUD).

Toda alteração nos dados de produtos e categorias envia uma notificação para o AWS SQS. Um consumer escuta essas notificações, gera um JSON atualizado do catálogo e salva no AWS S3.

Quando a rota para obter o catálogo for chamada o arquivo JSON é retornado evitando consultas excessivas no banco de dados.

### Diagrama de comunicação:

![Diagrama do Sistema](.github/diagrama.png)

Apesar do desafio solicitar o uso de **AWS SQS** para mensageria e **AWS S3** para armazenamento de arquivos, optei por utilizar tecnologias alternativas (**RabbitMQ** para mensageria e armazenamento local de arquivos). A integração com os serviços da AWS pode ser implementada posteriormente, seguindo a mesma lógica e arquitetura.

<br>

## 🔗 Link do Desafio
O desafio original pode ser encontrado no repositório oficial da Anota.ai:
[Desafio Backend - Anota.ai](https://github.com/githubanotaai/new-test-backend-nodejs).

<br>

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem principal para o desenvolvimento.
- **Spring Boot 3**: Framework para criação da API REST.
- **MongoDB**: Banco de dados NoSQL para armazenamento dos produtos e categorias, com uso de **Change Streams** para captura de mudanças em tempo real.
- **Swagger**: Documentação automática da api.
- **RabbitMQ**: Mensageria para processamento assíncrono de eventos.
- **Docker & Docker Compose**: Orquestração dos serviços.

<br>

## 📝 Metodologia

- **Clean Architecture**: Separação clara das responsabilidades em camadas (entities, use cases, controllers, repositories), seguindo os princípios de Clean Architecture.
- **SOLID**: Aplicação dos princípios SOLID para garantir um código modular, extensível e de fácil manutenção.
- **Processamento assíncrono**: Utiliza RabbitMQ para tratar eventos sem impactar a performance da API.

<br>

## 📦 Como Executar o Projeto

### 1️⃣ Clonar o repositório

```sh
git clone https://github.com/leandrosantino/catalogmanager.git
cd catalogmanager
```

### 2️⃣ Configurar o ambiente
Certifique-se de ter Docker e Docker Compose instalados.

### 3️⃣ Compilar o projeto
```sh
./gradlew bootJar
```

### 4️⃣ Subir os containers
```sh
docker-compose up -d
```
A API estará disponível em http://localhost:8080

<br>

## 📌 Documentação da API
Após subir os containers, acesse o endpoint abaixo para visualizar a documentação da API.

```
GET /swagger-ui/index.html
```

<br>

## 🛠️ Próximos Passos
- **Integração com AWS S3**: Substituir o armazenamento local pelo AWS S3 para persistência do JSON do catálogo.

- **Integração com AWS SQS**: Substituir o RabbitMQ pelo AWS SQS para mensageria.

- **Testes Automatizados**: Adicionar testes unitários e de integração para garantir a robustez do sistema.
