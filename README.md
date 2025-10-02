# Sistema de Gerenciamento de Biblioteca

Um sistema completo para gerenciamento de bibliotecas, com frontend em React e backend em Java com Spring Boot. Este projeto foi desenvolvido para fins acadêmicos.

---

## Tecnologias Utilizadas

-   **Frontend**: React
-   **Backend**: Java 21, Spring Boot
-   **Banco de Dados**: MySQL
-   **Containerização**: Docker, Docker Compose

---

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

-   Git (https://git-scm.com)
-   Node.js v18+ (https://nodejs.org/en/)
-   JDK 21 (https://www.oracle.com/java/technologies/downloads/#java21)
-   Docker e Docker Compose (https://www.docker.com/get-started)

---

## Como Executar o Projeto

O método recomendado para executar o projeto é utilizando Docker, pois ele configura todo o ambiente de forma automática.

1.  **Clone o repositório para sua máquina local:**
    ```bash
    git clone <url-do-seu-repositorio>
    ```

2.  **Acesse a pasta raiz do projeto:**
    ```bash
    cd biblioteca-management-system
    ```

3.  **Suba os contêineres com o Docker Compose:**
    O comando `--build` garante que as imagens serão criadas do zero.
    ```bash
    docker-compose up --build
    ```

Após a execução, a aplicação estará disponível nos seguintes endereços:

-   **Frontend (React)**: `http://localhost:3000`
-   **Backend (Spring Boot)**: `http://localhost:8080`
-   **Documentação da API (Swagger/OpenAPI)**: `http://localhost:8080/swagger-ui.html`

---

## Endpoints da API

A documentação completa da API pode ser acessada via Swagger. Abaixo estão os principais endpoints disponíveis.

#### Clientes

-   `POST /cliente`: Cria um novo cliente.
-   `GET /cliente`: Lista todos os clientes.
-   `PUT /cliente/{id}`: Atualiza um cliente existente.
-   `DELETE /cliente/{id}`: Exclui um cliente.

#### Livros

-   `POST /livro`: Adiciona um novo livro ao acervo.
-   `GET /livro`: Lista todos os livros.
-   `PUT /livro/{id}`: Atualiza as informações de um livro.
-   `DELETE /livro/{id}`: Remove um livro.

---

## Autores
    
-   **Bruno Duran**
-   GitHub: https://github.com/BrunoDuranTeodoro
-   **Gabriel Correa**
-   GitHub: https://github.com/biels25
-   **Gabriel Cortez**
-   GitHub: https://github.com/cortezinho
-   **Heloisa  Vichiatto**
-   GitHub: https://github.com/dodottie
-   **João Vitor Pinheiro**
-   GitHub: https://github.com/jvpinheiro1
