import React, { useState, useEffect } from 'react';
import perfil from '../assets/images/perfil.jpg'; // Certifique-se que o caminho da imagem está correto

function Profile() {
  // Inicializa o estado com o valor do localStorage ou 'Visitante'
  const [username, setUsername] = useState(localStorage.getItem('username') || 'Visitante');

  // Efeito para capturar o username da URL (caso venha do Login em outra porta)
  useEffect(() => {
    const queryParams = new URLSearchParams(window.location.search);
    const userUrl = queryParams.get('username');

    if (userUrl) {
      // 1. Salva no localStorage para as próximas vezes
      localStorage.setItem('username', userUrl);
      
      // 2. Atualiza o estado para refletir na tela imediatamente
      setUsername(userUrl);
      console.log(userUrl)
      
      // 3. Limpa a URL (remove o ?username=...) para ficar mais limpa
      window.history.replaceState({}, document.title, "/");
    }
  }, []);

  // Função para deslogar
  const handleLogout = () => {
    // 1. Limpa os dados de autenticação
    localStorage.removeItem('token');
    localStorage.removeItem('username');

    // 2. Redireciona para a tela de login
    // IMPORTANTE: Ajuste a porta se o seu projeto de Login estiver em outra (ex: 5174)
    // Aqui está configurado para voltar para si mesmo ou para a porta do Login
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
        
        {/* Dados estáticos para exemplo */}
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