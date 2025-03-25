# Documentação da Aplicação de Votação Cooperativista

## Objetivo

A aplicação tem como objetivo simular e gerenciar sessões de votação em um sistema de cooperativas. Cada associado pode votar em uma pauta específica, sendo que cada voto é registrado como "Sim" ou "Não". As funcionalidades incluem o cadastro de novas pautas, a abertura de sessões de votação, o recebimento de votos e a contabilização do resultado.

### Funcionalidades da API REST:
- **Cadastrar uma nova pauta**
- **Abrir uma sessão de votação em uma pauta**
- **Receber votos dos usuários** (os votos são "Sim" ou "Não", e cada usuário pode votar apenas uma vez por pauta)
- **Contabilizar os votos e apresentar o resultado da votação**

A aplicação deve ser executada na nuvem, com persistência dos dados para que os votos e pautas não se percam durante reinicializações.

## Tecnologias Utilizadas
- **Spring Boot** (Java 21)
- **Maven 3.8.6**
- **PostgreSQL 17**

## Como Rodar a Aplicação

### Usando Maven

1. **Limpeza e compilação do projeto**:
   Execute o seguinte comando para limpar e compilar o projeto:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   
2. **Rode os Testes**:
    Execute o seguinte comando para rodar os testes

    ```bash
   mvn test
    ```


## Informações Adicionais

A aplicação utiliza **Spring Boot** como framework para o backend, com **Maven 3.8.6** como ferramenta de build e **Java 21** como linguagem de programação. O banco de dados utilizado é o **PostgreSQL 18**.
