package projetoBiblioteca.projetoBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoBiblioteca.projetoBiblioteca.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}