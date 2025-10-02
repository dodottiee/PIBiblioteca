package projetoBiblioteca.projetoBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBiblioteca.projetoBiblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

