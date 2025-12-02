import React from 'react';
import perfil from '../assets/images/perfil.jpg';

function Profile() {
  // Recupera o nome salvo no login. Se não houver, usa "Visitante"
  const username = localStorage.getItem('username') || 'Visitante';

  // Função para deslogar
  const handleLogout = () => {
    // 1. Limpa os dados de autenticação
    localStorage.removeItem('token');
    localStorage.removeItem('username');

    // 2. Redireciona para a tela de login
    // Ajuste a porta '5173' se o seu projeto de Login estiver em outra porta
    window.location.href = 'http://localhost:5173/';
  };

  return (
    <div className="profile-section">
      <h2>Perfil</h2>
      <div className="profile-image-container">
        <img src={perfil} alt="Profile" />
      </div>
      <div className="profile-info">
        {/* Exibe o nome dinâmico aqui */}
        <h3>{username}</h3>
        
        {/* Estes dados ainda são estáticos, pois o backend (Login) 
            atualmente só retorna o token, sem ID ou outros detalhes. */}
        <p>ID: 123456</p>
        <p>Membro desde<br/>15 de Março de 2020</p>
        <p>Gênero favorito<br/>Ficção</p>
        <p>Livro favorito<br/>Dom Casmurro</p>
        <p>Autor favorito<br/>Machado de Assis</p>
      </div>
      
      {/* Botão com a ação de sair */}
      <button className="logout-btn" onClick={handleLogout}>
        Sair
      </button>
    </div>
  );
}

export default Profile;