📖 Sistema de Gerenciamento de Biblioteca<p align="center"><img src="https://www.google.com/search?q=https://img.shields.io/badge/status-em%2520desenvolvimento-yellow%3Fstyle%3Dfor-the-badge" alt="Status do Projeto"/><img src="https://www.google.com/search?q=https://img.shields.io/badge/licen%25C3%25A7a-MIT-blue%3Fstyle%3Dfor-the-badge" alt="Licença MIT"/></p><p align="center">Um sistema completo para gerenciamento de bibliotecas, com frontend em React e backend em Java/Spring Boot.</p><p align="center"><a href="#-tecnologias-utilizadas">Tecnologias</a> •<a href="#-canvas-da-arquitetura">Arquitetura</a> •<a href="#-como-executar-o-projeto">Como Executar</a> •<a href="#-endpoints-da-api">API</a> •<a href="#-autor">Autor</a></p>✨ Tecnologias UtilizadasEste projeto foi construído utilizando tecnologias modernas e robustas do mercado:CategoriaTecnologiaFrontendBackendBanco de DadosDevOps🏛️ Canvas da ArquiteturaO diagrama abaixo ilustra a arquitetura geral do sistema e como os componentes se comunicam de forma orquestrada.   +-----------------------+
   |  👤 USUÁRIO           |
   |  (Desenvolvedor)      |
   +-----------+-----------+
               |
               | ➡️ Interage com
               v
   +-------------------------------------------------------------+
   |   📦 REPOSITÓRIO GITHUB (biblioteca-management-system)      |
   |                                                             |
   |  [ frontend/ ]  [ backend/ ]  [ docs/ ]  [ .github/ ]       |
   |                                                             |
   |  +-------------------------------------------------------+  |
   |  | 📜 docker-compose.yml                                 |  |
   |  +---------------------+---------------------------------+  |
   |                        |                                    |
   |                        | ⚙️ Orquestra o ambiente local       |
   |                        v                                    |
   |  +-------------------------------------------------------+  |
   |  | 🐳 AMBIENTE CONTAINERIZADO (DOCKER)                     |  |
   |  |                                                       |  |
   |  |  +---------------+      API REST      +-------------+ |  |
   |  |  | 💻 FRONTEND   | <---------------> | ☕ BACKEND    | |  |
   |  |  | (React)       |     (HTTP)        | (Spring)    | |  |
   |  |  | 🚪 Porta: 3000 |                   | 🚪 Porta: 8080| |  |
   |  |  +---------------+                   +------+------+ |  |
   |  |                                              |        |  |
   |  |                                       💾 JDBC |        |  |
   |  |                                              v        |  |
   |  |                                      +-------------+ |  |
   |  |                                      | 🛢️ BANCO DE | |  |
   |  |                                      |    DADOS    | |  |
   |  |                                      |   (MySQL)   | |  |
   |  |                                      +-------------+ |  |
   |  +-------------------------------------------------------+  |
   +-------------------------------------------------------------+
⚙️ Pré-requisitosAntes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:GitNode.js v18+JDK 21Docker e Docker Compose🏁 Como Executar o ProjetoO método recomendado para executar o projeto é utilizando Docker, pois ele configura todo o ambiente de forma automática e isolada.# 1. Clone o repositório para sua máquina local
git clone <url-do-seu-repositorio>

# 2. Acesse a pasta raiz do projeto
cd biblioteca-management-system

# 3. Suba os contêineres com o Docker Compose
# O comando '--build' garante que as imagens serão criadas do zero
docker-compose up --build
Após a execução, a aplicação estará disponível nos seguintes endereços:🖥️ Frontend (React): http://localhost:3000⚙️ Backend (Spring Boot): http://localhost:8080📚 Documentação da API (Swagger/OpenAPI): http://localhost:8080/swagger-ui.html📝 Endpoints da APIA documentação completa da API pode ser acessada via Swagger. Abaixo estão os principais endpoints disponíveis:🧑 ClientesPOST /cliente: Cria um novo cliente.GET /cliente: Lista todos os clientes.PUT /cliente/{id}: Atualiza um cliente existente.DELETE /cliente/{id}: Exclui um cliente.📖 LivrosPOST /livro: Adiciona um novo livro ao acervo.GET /livro: Lista todos os livros.PUT /livro/{id}: Atualiza as informações de um livro.DELETE /livro/{id}: Remove um livro.👨‍💻 AutorEste projeto foi desenvolvido por:GabrielLinkedIn: [Seu LinkedIn Aqui]GitHub: @biels25<p align="center">Feito com ❤️ para o projeto da faculdade.</p>
