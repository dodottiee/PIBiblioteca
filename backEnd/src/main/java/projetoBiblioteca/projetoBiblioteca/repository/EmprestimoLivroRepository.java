package projetoBiblioteca.projetoBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBiblioteca.projetoBiblioteca.model.EmprestimoLivro;

public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, Long> {
}