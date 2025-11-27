package projetoBiblioteca.projetoBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import projetoBiblioteca.projetoBiblioteca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método necessário pelo UserDetailsService para carregar o usuário pelo nome de usuário (login)
    UserDetails findByLogin(String login);
}