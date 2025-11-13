import React, { useState, useEffect } from 'react';
import './styles/main.css';
import Profile from './components/Profile';
import BooksList from './components/BookList';
import MyBooks from './components/MyBooks';

// URL base da sua API Spring Boot
const API_URL = 'http://localhost:8080';

const bookColors = [
  '#ffe0b2', // Laranja claro
  '#b3e5fc', // Azul claro
  '#e1f5fe', // Azul mais claro
  '#c8e6c9', // Verde claro
  '#ffcdd2', // Vermelho claro
  '#f0f4c3', // Amarelo claro
  '#d1c4e9', // Roxo claro
];

function App() {
  // Estado para livros disponíveis
  const [books, setBooks] = useState([]);
  // Estado para empréstimos ativos (Meus Livros)
  const [myBooks, setMyBooks] = useState([]);
  
  // Como seu Profile.jsx é estático, vamos usar um ID de cliente fixo
  // Em um app real, este ID viria do login
  const CLIENTE_ID = 1; 

  const getRandomColor = () => {
    return bookColors[Math.floor(Math.random() * bookColors.length)];
  };

  // Função para buscar todos os dados da API
  const fetchData = async () => {
    try {
      // 1. Buscar todos os livros
      const livrosResponse = await fetch(`${API_URL}/livro`);
      if (!livrosResponse.ok) throw new Error('Erro ao buscar livros');
      const todosLivros = await livrosResponse.json();
      
      // Filtra apenas os livros DISPONIVEIS
      // O backend retorna 'idLivro' (Model Livro.java)
      setBooks(todosLivros.filter(livro => livro.status === 'DISPONIVEL'));

      // 2. Buscar todos os empréstimos
      const emprestimosResponse = await fetch(`${API_URL}/emprestimo`);
      if (!emprestimosResponse.ok) throw new Error('Erro ao buscar empréstimos');
      const todosEmprestimos = await emprestimosResponse.json();
      
      // Adiciona uma cor aleatória para cada empréstimo (para manter o visual)
      const emprestimosComCor = todosEmprestimos
        // Filtra apenas empréstimos ativos (sem data de devolução)
        .filter(emp => !emp.dataDevolucao) 
        .map(emp => {
          // Calcula data de devolução (Ex: 7 dias após data do empréstimo)
          // A dataEmprestimo vem como "YYYY-MM-DD"
          const dataEmp = new Date(emp.dataEmprestimo + 'T00:00:00'); // Ajusta fuso
          const dataDev = new Date(dataEmp.getTime() + 7 * 24 * 60 * 60 * 1000);
          
          return {
            ...emp,
            color: getRandomColor(),
            dueDate: dataDev.toLocaleDateString('pt-BR') // Formata para "DD/MM/YYYY"
          };
        });

      setMyBooks(emprestimosComCor);

    } catch (error) {
      console.error("Erro ao buscar dados da API:", error);
    }
  };

  // useEffect para buscar os dados quando o componente montar
  useEffect(() => {
    fetchData();
  }, []); // O array vazio [] garante que isso rode apenas uma vez

  // Função chamada pelo BookList.jsx para criar um empréstimo
  const addBookToMyList = async (book, dueDate) => {
    const emprestimoDTO = {
      clienteId: CLIENTE_ID, 
      livroIds: [book.idLivro], // A API espera 'idLivro' (Model Livro.java)
      dataEmprestimo: new Date().toISOString().split('T')[0], // Data de hoje (YYYY-MM-DD)
      dataDevolucao: dueDate || null // Data de devolução do modal
    };

    try {
      const response = await fetch(`${API_URL}/emprestimo`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(emprestimoDTO)
      });

      if (!response.ok) {
        const erro = await response.json(); // Tenta ler o erro do backend
        throw new Error(erro.detalhes || 'Erro ao registrar empréstimo');
      }

      // Após o sucesso, atualiza as listas
      fetchData(); 

    } catch (error) {
      console.error("Erro ao emprestar livro:", error);
      alert(error.message); // Mostra o erro para o usuário
    }
  };

  // Função chamada pelo MyBooks.jsx para devolver um livro (Empréstimo)
  const removeBookFromMyList = async (emprestimo) => {
    try {
      // O endpoint de devolução espera o ID do *Empréstimo*
      const response = await fetch(`${API_URL}/emprestimo/devolver/${emprestimo.id}`, {
        method: 'POST'
      });

      if (!response.ok) {
        throw new Error('Erro ao devolver livro');
      }

      // Após o sucesso, atualiza as listas
      fetchData();

    } catch (error) {
      console.error("Erro ao devolver livro:", error);
      alert(error.message);
    }
  };

  return (
    <div className="app">
      <Profile />
      <BooksList books={books} onAddBook={addBookToMyList} />
      <MyBooks myBooks={myBooks} onRemoveBook={removeBookFromMyList} />
    </div>
  );
}

export default App;