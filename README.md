# sistema-arquivos-virtual

Sistema de Arquivos Virtual

Este projeto implementa um sistema de arquivos virtual que permite criar, listar, atualizar e deletar diretórios e arquivos de forma hierárquica. O backend é desenvolvido com Spring Boot e o frontend com Angular.

Funcionalidades

API REST para operações CRUD em diretórios e arquivos.

Exibição de uma listagem hierárquica de diretórios no frontend.

Suporte a Docker para facilitar a execução do projeto.


Requisitos
Antes de iniciar, você precisa ter instalado:

Java 17

Node.js (Versão 16 ou superior)
Angular CLI
Docker (opcional)
Instruções para Rodar o Projeto
Backend:
Clone o repositório:
git clone https://github.com/lari-sant/sistema-arquivos-virtual.git
cd sistema-arquivos-virtual/backend

Compile o projeto:
./mvnw clean install

Execute o projeto:
./mvnw spring-boot:run
A API estará disponível em http://localhost:8080/api.

Frontend
Navegue até a pasta do frontend:
cd ../frontend

Instale as dependências:
npm install

Execute o frontend:
ng serve
O frontend estará disponível em http://localhost:4200.

Utilizando Docker

Backend com Docker
Navegue até a pasta do backend:
cd backend

Crie a imagem Docker:
docker build -t virtual-filesystem-backend .

Execute o container Docker:
docker run -p 8080:8080 virtual-filesystem-backend
A API estará disponível em http://localhost:8080/api.

Frontend com Docker
Navegue até a pasta do frontend:
cd ../frontend

Crie a imagem Docker:
docker build -t virtual-filesystem-frontend .

Execute o container Docker:
docker run -p 4200:8080 virtual-filesystem-frontend
O frontend estará disponível em http://localhost:4200.

Testes Unitários (Backend)
Para rodar os testes unitários no backend, execute:
./mvnw test

Estrutura do Projeto
backend/: Contém o código do Spring Boot.
frontend/: Contém o código do Angular.
docker/: Arquivos relacionados à configuração Docker.
Documentação da API
A API REST suporta as seguintes operações:

GET /api/directories: Retorna a listagem de todos os diretórios.
GET /api/directories/{id}: Retorna um diretório específico por ID.
POST /api/directories: Cria um novo diretório.
PUT /api/directories/{id}: Atualiza um diretório existente.
DELETE /api/directories/{id}: Remove um diretório.




