# PIBiblioteca
📖 Sistema de Gerenciamento de BibliotecaEste projeto é um sistema de gerenciamento para uma biblioteca, desenvolvido como parte de um trabalho acadêmico. A aplicação conta com um frontend em React e um backend monolítico utilizando Java com Spring Boot, tudo orquestrado com Docker.✨ Tecnologias UtilizadasO projeto foi construído utilizando as seguintes tecnologias:Frontend: ReactBackend: Java 21, Spring Boot 3.xBanco de Dados: MySQLGerenciador de Pacotes: MavenContainerização: Docker & Docker ComposeDevOps: GitHub Actions para CI/CD🏛️ Canvas da ArquiteturaO diagrama abaixo ilustra a arquitetura geral do sistema e como os componentes se comunicam.   +-----------------------+
   |  USUÁRIO              |
   |  (Desenvolvedor)      |
   +-----------+-----------+
               |
               | Interage com
               v
   +-------------------------------------------------------------+
   |   REPOSITÓRIO GITHUB (biblioteca-management-system)         |
   |                                                             |
   |  [ frontend/ ]  [ backend/ ]  [ docs/ ]  [ .github/ ]       |
   |                                                             |
   |  +-------------------------------------------------------+  |
   |  | docker-compose.yml                                    |  |
   |  +---------------------+---------------------------------+  |
   |                        |                                    |
   |                        | Orquestra o ambiente local         |
   |                        v                                    |
   |  +-------------------------------------------------------+  |
   |  | AMBIENTE CONTAINERIZADO (DOCKER)                      |  |
   |  |                                                       |  |
   |  |  +---------------+      API REST      +-------------+ |  |
   |  |  | FRONTEND      | <---------------> | BACKEND     | |  |
   |  |  | (React)       |     (HTTP)        | (Spring)    | |  |
   |  |  | Porta: 3000   |                   | Porta: 8080 | |  |
   |  |  +---------------+                   +------+------+ |  |
   |  |                                              |        |  |
   |  |                                       JDBC   |        |  |
   |  |                                              v        |  |
   |  |                                      +-------------+ |  |
   |  |                                      | BANCO DE    | |  |
   |  |                                      | DADOS       | |  |
   |  |                                      | (MySQL)     | |  |
   |  |                                      +-------------+ |  |
   |  +-------------------------------------------------------+  |
   +-------------------------------------------------------------+

⚙️ Pré-requisitosAntes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:GitNode.js (versão 18 ou superior)JDK 21Docker e Docker Compose🏁 Como Executar o ProjetoO método recomendado para executar o projeto é utilizando Docker, pois ele configura todo o ambiente de forma automática.# 1. Clone o repositório para sua máquina local
git clone <url-do-seu-repositorio>

# 2. Acesse a pasta raiz do projeto
cd biblioteca-management-system

# 3. Suba os contêineres com o Docker Compose
# O comando '--build' garante que as imagens serão criadas do zero
docker-compose up --build
Após a execução, a aplicação estará disponível nos seguintes endereços:Frontend (React): http://localhost:3000Backend (Spring Boot): http://localhost:8080Documentação da API (Swagger/OpenAPI): http://localhost:8080/swagger-ui.html📝 Endpoints da APIA documentação completa da API pode ser acessada via Swagger, mas aqui estão os principais endpoints disponíveis:ClientesPOST /cliente: Cria um novo cliente.GET /cliente: Lista todos os clientes.PUT /cliente/{id}: Atualiza um cliente existente.DELETE /cliente/{id}: Exclui um cliente.LivrosPOST /livro: Adiciona um novo livro ao acervo.GET /livro: Lista todos os livros.PUT /livro/{id}: Atualiza as informações de um livro.DELETE /livro/{id}: Remove um livro.Feito com ❤️ para o projeto da faculdade.
