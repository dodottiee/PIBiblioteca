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
  
  // ID do cliente (Certifique-se que este ID existe na tabela 'cliente' do seu banco)
  const CLIENTE_ID = 1; 

  const getRandomColor = () => {
    return bookColors[Math.floor(Math.random() * bookColors.length)];
  };

  // Função auxiliar para evitar erros com datas vindas do Java
  const parseDate = (dateData) => {
    if (!dateData) return new Date();
    // Se vier como array [2024, 12, 10]
    if (Array.isArray(dateData)) {
        return new Date(dateData[0], dateData[1] - 1, dateData[2]);
    }
    // Se vier como string "2024-12-10"
    return new Date(dateData);
  };

  const fetchData = async () => {
    const headers = { 'Content-Type': 'application/json' };

    try {
      // 1. Buscar todos os livros disponíveis
      const livrosResponse = await fetch(`${API_URL}/livro`, { headers });
      if (!livrosResponse.ok) throw new Error('Erro ao buscar livros');
      const todosLivros = await livrosResponse.json();
      
      setBooks(todosLivros.filter(livro => livro.status === 'DISPONIVEL'));

      // 2. Buscar histórico de empréstimos do usuário
      const emprestimosResponse = await fetch(`${API_URL}/emprestimo`, { headers });
      if (!emprestimosResponse.ok) throw new Error('Erro ao buscar empréstimos');
      const todosEmprestimos = await emprestimosResponse.json();
      
      // Filtra apenas os empréstimos deste cliente E que ainda NÃO foram devolvidos
      const emprestimosFiltrados = todosEmprestimos.filter(emp => {
        // Verifica se é o cliente logado
        const isCliente = emp.cliente && emp.cliente.id === CLIENTE_ID;
        // Verifica se NÃO tem data de devolução (ou seja, está ativo)
        const isAtivo = !emp.dataDevolucao;
        
        return isCliente && isAtivo;
      });

      // Processa os dados para exibição (Cores e Datas)
      const emprestimosProcessados = emprestimosFiltrados.map(emp => {
          const dataEmp = parseDate(emp.dataEmprestimo);
          const dataDev = new Date(dataEmp);
          dataDev.setDate(dataDev.getDate() + 7); // Calcula vencimento para 7 dias depois
          
          return {
            ...emp,
            color: getRandomColor(),
            dueDate: dataDev.toLocaleDateString('pt-BR'),
            isDevolvido: false // Como filtramos, todos aqui são false
          };
        });

      setMyBooks(emprestimosProcessados);

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
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(emprestimoDTO)
      });

      if (!response.ok) {
        const erro = await response.json();
        throw new Error(erro.detalhes || 'Erro ao registrar empréstimo');
      }

      fetchData(); // Recarrega as listas

    } catch (error) {
      console.error("Erro ao emprestar livro:", error);
      alert(error.message);
    }
  };

  const removeBookFromMyList = async (emprestimo) => {
    try {
      const response = await fetch(`${API_URL}/emprestimo/devolver/${emprestimo.id}`, {
        method: 'POST'
      });

      if (!response.ok) throw new Error('Erro ao devolver livro');

      fetchData(); // Recarrega e o livro sairá da lista pois agora terá data de devolução

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