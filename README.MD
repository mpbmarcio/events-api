# Events Application

![Java](https://img.shields.io/badge/Java-21-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.13-brightgreen?logo=springboot)
![MariaDB](https://img.shields.io/badge/MariaDB-10.11-orange?logo=mariadb)
![JWT](https://img.shields.io/badge/JWT-Auth0-red?logo=jwt)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-yellow?logo=swagger)

Aplicação backend desenvolvida em **Spring Boot** para gerenciamento de eventos, usuários, conferências, sessões e inscrições.  
Conta com **segurança implementada via Spring Security e autenticação JWT**, garantindo acesso controlado aos endpoints da API.  
Banco de dados gerenciado com **MariaDB** e versionado com **Flyway**.

---

## 🚀 Tecnologias
- Java 21
- Spring Boot 3.5.13
- Spring Web
- Spring Data JPA
- Spring Security + JWT (Auth0)
- Flyway
- MariaDB (compatível também com MySQL e Postgres)
- Swagger/OpenAPI (Springdoc)
- Maven

---

## 📂 Estrutura de Pastas

- **src/main/java/br/com/mpb/events/**
    - `config/` → Configurações gerais
    - `controller/` → Controllers REST
    - `dto/` → Data Transfer Objects
    - `exception/` → Tratamento de exceções
    - `model/` → Entidades JPA
    - `repository/` → Interfaces JPA
    - `security/` → Configurações de segurança e filtros JWT
    - `service/` → Regras de negócio
    - `util/` → Utilitários

- **src/main/resources/**
    - `application.properties` → Configuração da aplicação
    - `db/migration/` → Scripts Flyway (V1__..., V2__...)

- **src/test/java/br/com/mpb/events/** → Testes unitários e de integração

---

## ▶️ Como rodar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/events.git
   ```
2. Configure variáveis de ambiente para usuário e senha do banco:
    ```bash
    export DB_MYSQL_USERNAME=seu_usuario
    export DB_MYSQL_PASSWORD=sua_senha
    ```
3. Execute as migrations automaticamente com o Flyway (já integrado).

4. Rode a aplicação:
    ```bash
    mvn spring-boot:run
   ```
5. Acesse:
    ```html
    http://localhost:8080
    ```

## 🔑 Autenticação e Segurança
- Implementada com **Spring Security**.
- Autenticação via **JWT** (Json Web Token).
- Endpoints protegidos exigem token válido no header:
    ```
    Authorization: Bearer <token>
    ```
  
## 📖 Endpoints Principais

### Autenticação
- `POST /auth/login` → autenticar usuário e gerar token JWT

### Usuários
- `POST /users` → criar usuário
- `GET /users` → listar todos os usuários
- `POST /change-password` → alterar senha do usuário autenticado

### Conferências
- `POST /conferences` → criar conferência
- `GET /conferences` → listar conferências
- `GET /conferences/{id}` → buscar conferência por ID

### Sessões
- `POST /sessions` → criar sessão
- `GET /sessions` → listar sessões

### Inscrições
- `POST /subscriptions` → criar inscrição
- `GET /subscriptions` → listar todas as inscrições
- `GET /subscriptions/user/{id}` → listar inscrições de um usuário específico
- `GET /subscriptions/session/{id}` → listar inscrições de uma sessão específica

## 📦 Exemplos de Request/Response

### Login
**Request**
POST /auth/login
```json
{
    "email": "user@email.com",
    "password": "123456"
}
```
**Response**
200 OK
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### Criar Usuário
**Request**
POST /users
```json
{
    "name": "Márcio",
    "email": "marcio@email.com",
    "password": "123456"
}
```
**Response**
201 Created
```json
{
    "id": 1,
    "name": "Márcio",
    "email": "marcio@email.com"
}
```
### Alterar Senha
**Request**
POST /change-password
```json
{
    "email": "marcio@email.com",
    "oldPassword": "123456",
    "newPassword": "novaSenha123"
}
```
**Response**
200 OK
```json
"Senha alterada com sucesso!"
```

### Tratamento de Exceções
- Retorno padronizado de erro `404 Not Found` via `ExceptionHandlerController`

## 📖 Documentação da API
- Swagger UI disponível em:
    ```
    http://localhost:8080/swagger-ui/index.html
    ```
> ⚠️ Por padrão, o Swagger UI é protegido pelo Spring Security.  
> Em `SecurityConfig`, os endpoints `/swagger-ui/**` e `/v3/api-docs/**` foram liberados.  
> Dependência utilizada: `springdoc-openapi-starter-webmvc-ui:2.8.9`.  
> 📘 O endpoint `/v3/api-docs` fornece a definição da API em JSON e é consumido automaticamente pelo Swagger UI — não é necessário acessá-lo diretamente.

## 📚 Documentação complementar
- [Guia de Convenções](README_REPOSITORY_SERVICE_NAMING_CONVENTION.MD)
- [Guia de Security](README_SECURITY.MD)


## 👨‍💻 Autor
- Márcio Pires Barboza

## 📜 Licença
- Este projeto está sob a licença MIT.



