import React, { useState } from 'react';
import Modal from '../Modal';

// --- MUDANÇA AQUI ---
// O prop 'myBooks' agora é uma lista de Empréstimos
function MyBooks({ myBooks, onRemoveBook }) {
  const [searchTerm, setSearchTerm] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null); // 'selectedBook' agora será o objeto Empréstimo

  // A lógica de filtro precisa olhar para os livros dentro do empréstimo
  const filteredBooksRead = myBooks.filter(emprestimo => {
    // Vamos assumir que cada empréstimo tem pelo menos um livro
    // (Model Emprestimo.java -> List<EmprestimoLivro> emprestimoLivros)
    const livro = emprestimo.emprestimoLivros[0]?.livro;
    if (!livro) return false; // Se não houver livro, não mostra
    
    // O model Livro.java tem 'titulo' e 'autor'
    return livro.titulo.toLowerCase().includes(searchTerm.toLowerCase()) ||
           livro.autor.toLowerCase().includes(searchTerm.toLowerCase());
  });

  // 'emprestimo' é o objeto completo do empréstimo
  const handleBookClick = (emprestimo) => {
    setSelectedBook(emprestimo); // Salva o empréstimo selecionado
    setShowModal(true);
  };

  const handleConfirm = () => {
    // 'selectedBook' é o empréstimo. onRemoveBook (removeBookFromMyList)
    // precisa do objeto empréstimo.
    onRemoveBook(selectedBook);
    setShowModal(false);
    setSelectedBook(null);
  };

  const handleCancel = () => {
    setShowModal(false);
    setSelectedBook(null);
  };

  return (
    <div className="my-books-section">
      <h2>Meus Livros</h2>
      <input
        className="search-input"
        type="text"
        placeholder="Search"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <div className="my-books-list">
        <ul>
          {filteredBooksRead.map((emprestimo) => {
            // Pega o primeiro livro do empréstimo para exibir
            // (Model EmprestimoLivro.java -> Livro livro)
            const livro = emprestimo.emprestimoLivros[0]?.livro;
            if (!livro) return null; // Não renderiza se não houver livro

            return (
              <li
                key={emprestimo.id} // A key agora é o ID do empréstimo
                className="book-item"
                onClick={() => handleBookClick(emprestimo)}
                style={{ backgroundColor: emprestimo.color }} // Usa a cor do objeto empréstimo
              >
                <div className="book-info">
                  <span className="book-title">{livro.titulo}</span>
                  <span className="book-author">{livro.autor}</span>
                </div>
              </li>
            );
          })}
        </ul>
      </div>

      <Modal
        show={showModal}
        title="Devolver Livro"
        // Usa o 'dueDate' que adicionamos no App.jsx
        message={`Data de devolução: ${selectedBook?.dueDate || 'Não especificada'}. Deseja devolver o livro agora?`}
        onConfirm={handleConfirm}
        onCancel={handleCancel}
      />
    </div>
  );
}

export default MyBooks;