package projetoBiblioteca.projetoBiblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    private String titulo;

    private String autor;

    @Column(unique = true)
    private Long isbn;

    private String genero;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    @JsonIgnore // <-- Anotação corrigida aqui
    private List<EmprestimoLivro> emprestimoLivros;

    // Getters and Setters
}