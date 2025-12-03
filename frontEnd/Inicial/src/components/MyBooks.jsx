import React, { useState } from 'react';
import Modal from '../Modal';

function MyBooks({ myBooks, onRemoveBook }) {
  const [searchTerm, setSearchTerm] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);

  const filteredBooksRead = myBooks.filter(emprestimo => {
    // Proteção: usa '?.' para não quebrar se o livro vier nulo do banco
    const livro = emprestimo.emprestimoLivros?.[0]?.livro;
    
    // Se por algum erro de banco o livro for null, exibimos mesmo assim para debug
    if (!livro) return true; 
    
    return livro.titulo.toLowerCase().includes(searchTerm.toLowerCase()) ||
           livro.autor.toLowerCase().includes(searchTerm.toLowerCase());
  });

  const handleBookClick = (emprestimo) => {
    // Só permite devolver se AINDA NÃO foi devolvido
    if (!emprestimo.isDevolvido) {
        setSelectedBook(emprestimo);
        setShowModal(true);
    }
  };

  const handleConfirm = () => {
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
        placeholder="Buscar nos meus livros..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <div className="my-books-list">
        <ul>
          {filteredBooksRead.length === 0 && (
             <li style={{ padding: '20px', color: '#666', textAlign: 'center' }}>
               Nenhum livro encontrado.
             </li>
          )}

          {filteredBooksRead.map((emprestimo) => {
            // Tenta pegar os dados do livro com segurança
            const livro = emprestimo.emprestimoLivros?.[0]?.livro;
            const tituloExibicao = livro ? livro.titulo : "Livro não identificado";
            const autorExibicao = livro ? livro.autor : "";

            return (
              <li
                key={emprestimo.id}
                className="book-item"
                onClick={() => handleBookClick(emprestimo)}
                style={{ 
                    backgroundColor: emprestimo.color || '#fff',
                    // Deixa transparente e muda o cursor se já estiver devolvido
                    opacity: emprestimo.isDevolvido ? 0.6 : 1, 
                    cursor: emprestimo.isDevolvido ? 'default' : 'pointer'
                }}
              >
                <div className="book-info">
                  <span className="book-title">
                    {tituloExibicao} {emprestimo.isDevolvido && "(Devolvido)"}
                  </span>
                  <span className="book-author">{autorExibicao}</span>
                </div>
              </li>
            );
          })}
        </ul>
      </div>

      <Modal
        show={showModal}
        title="Devolver Livro"
        message={`Data de devolução prevista: ${selectedBook?.dueDate}. Deseja devolver este livro agora?`}
        onConfirm={handleConfirm}
        onCancel={handleCancel}
      />
    </div>
  );
}

export default MyBooks;