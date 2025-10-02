package projetoBiblioteca.projetoBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBiblioteca.projetoBiblioteca.model.Multa;

public interface MultaRepository extends JpaRepository<Multa, Long> {
}