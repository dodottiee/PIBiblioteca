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
  const [books, setBooks] = useState([]);
  const [myBooks, setMyBooks] = useState([]);
  
  // ID fixo para teste, já que removemos a autenticação
  const CLIENTE_ID = 1; 

  const getRandomColor = () => {
    return bookColors[Math.floor(Math.random() * bookColors.length)];
  };

  const fetchData = async () => {
    // Headers simples, sem Authorization
    const headers = {
        'Content-Type': 'application/json'
    };

    try {
      // 1. Buscar todos os livros
      const livrosResponse = await fetch(`${API_URL}/livro`, { headers });
      if (!livrosResponse.ok) throw new Error('Erro ao buscar livros');
      const todosLivros = await livrosResponse.json();
      
      setBooks(todosLivros.filter(livro => livro.status === 'DISPONIVEL'));

      // 2. Buscar todos os empréstimos
      const emprestimosResponse = await fetch(`${API_URL}/emprestimo`, { headers });
      if (!emprestimosResponse.ok) throw new Error('Erro ao buscar empréstimos');
      const todosEmprestimos = await emprestimosResponse.json();
      
      const emprestimosComCor = todosEmprestimos
        .filter(emp => !emp.dataDevolucao) 
        .map(emp => {
          const dataEmp = new Date(emp.dataEmprestimo + 'T00:00:00');
          const dataDev = new Date(dataEmp.getTime() + 7 * 24 * 60 * 60 * 1000);
          
          return {
            ...emp,
            color: getRandomColor(),
            dueDate: dataDev.toLocaleDateString('pt-BR')
          };
        });

      setMyBooks(emprestimosComCor);

    } catch (error) {
      console.error("Erro ao buscar dados da API:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const addBookToMyList = async (book, dueDate) => {
    const emprestimoDTO = {
      clienteId: CLIENTE_ID, 
      livroIds: [book.idLivro], 
      dataEmprestimo: new Date().toISOString().split('T')[0], 
      dataDevolucao: dueDate || null 
    };

    try {
      const response = await fetch(`${API_URL}/emprestimo`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }, // Sem token
        body: JSON.stringify(emprestimoDTO)
      });

      if (!response.ok) {
        const erro = await response.json();
        throw new Error(erro.detalhes || 'Erro ao registrar empréstimo');
      }

      fetchData(); 

    } catch (error) {
      console.error("Erro ao emprestar livro:", error);
      alert(error.message);
    }
  };

  const removeBookFromMyList = async (emprestimo) => {
    try {
      const response = await fetch(`${API_URL}/emprestimo/devolver/${emprestimo.id}`, {
        method: 'POST'
        // Sem headers de autorização
      });

      if (!response.ok) {
        throw new Error('Erro ao devolver livro');
      }

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