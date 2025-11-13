import React, { useState } from 'react';
import Modal from '../Modal';

function BooksList({ books, onAddBook }) {
  const [searchTerm, setSearchTerm] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);
  const [dueDate, setDueDate] = useState('');

  // O model Livro.java tem 'titulo' e 'autor', então o filtro funciona
  const filteredBooks = books.filter(book =>
    book.titulo.toLowerCase().includes(searchTerm.toLowerCase()) ||
    book.autor.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleBookClick = (book) => {
    setSelectedBook(book);
    setShowModal(true);
  };

  const handleConfirm = () => {
    // Garante que o objeto do livro e a data sejam enviados
    onAddBook(selectedBook, dueDate); // Passa o livro e a data
    setShowModal(false);
    setDueDate('');
    setSelectedBook(null);
  };

  const handleCancel = () => {
    setShowModal(false);
    setDueDate('');
    setSelectedBook(null);
  };

  return (
    <div className="books-section">
      <h2>Livros</h2>
      <input
        className="search-input"
        type="text"
        placeholder="Search"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <div className="books-list">
        <ul>
          {/* A key deve ser o ID único do livro vindo da API (idLivro do Livro.java) */}
          {filteredBooks.map((book) => (
            <li key={book.idLivro} className="book-item" onClick={() => handleBookClick(book)}>
              <div className="book-info">
                <span className="book-title">{book.titulo}</span>
                <span className="book-author">{book.autor}</span>
              </div>
            </li>
          ))}
        </ul>
      </div>

      <Modal
        show={showModal}
        title="Adicionar Livro"
        message={`Deseja adicionar "${selectedBook?.titulo}" aos seus livros?`}
        onConfirm={handleConfirm}
        onCancel={handleCancel}
      >
        <p>Data de devolução (Opcional):</p>
        <input
          type="date"
          value={dueDate}
          onChange={(e) => setDueDate(e.target.value)}
          // Define data mínima como hoje
          min={new Date().toISOString().split('T')[0]}
        />
      </Modal>
    </div>
  );
}

export default BooksList;