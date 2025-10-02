package projetoBiblioteca.projetoBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetoBiblioteca.projetoBiblioteca.model.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIsbnIn(List<Long> isbns); // Novo método

    @Query("SELECT l FROM Livro l JOIN l.emprestimoLivros el WHERE el.emprestimo.id = :emprestimoId")
    List<Livro> findByEmprestimoId(@Param("emprestimoId") Long emprestimoId);

    Optional<Livro> findByIsbn(Long isbn);


}
