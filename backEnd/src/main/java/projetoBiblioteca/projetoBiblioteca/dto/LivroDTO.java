package projetoBiblioteca.projetoBiblioteca.dto;

import lombok.Data;
import projetoBiblioteca.projetoBiblioteca.model.Livro;
import projetoBiblioteca.projetoBiblioteca.model.StatusLivro;

@Data
public class LivroDTO {
    private  String titulo;
    private String autor;
    private Long isbn;
    private String genero;
    private StatusLivro status;

    public static LivroDTO fromEntity(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setIsbn(livro.getIsbn());
        dto.setGenero(livro.getGenero());
        dto.setStatus(livro.getStatus());
        return dto;
    }
    public static Livro fromDTO(LivroDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getAutor());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setGenero(dto.getGenero());
        livro.setStatus(dto.getStatus());
        return livro;
    }

    // Getters and Setters
}