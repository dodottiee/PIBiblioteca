package projetoBiblioteca.projetoBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBiblioteca.projetoBiblioteca.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}