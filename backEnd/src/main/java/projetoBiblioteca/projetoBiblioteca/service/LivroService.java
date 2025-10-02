package projetoBiblioteca.projetoBiblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBiblioteca.projetoBiblioteca.dto.LivroDTO;
import projetoBiblioteca.projetoBiblioteca.model.Livro;
import projetoBiblioteca.projetoBiblioteca.repository.LivroRepository;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro criarLivro(LivroDTO livroDTO) {
        return livroRepository.save(LivroDTO.fromDTO(livroDTO)); // Assume que existe LivroDTO.fromDTO()
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro atualizarLivro(Long idLivro, LivroDTO livroDTOdto) {
        Livro livro = livroRepository.findById(idLivro).orElseThrow();
        livro.setTitulo(livroDTOdto.getTitulo());
        livro.setAutor(livroDTOdto.getAutor());
        livro.setIsbn(livroDTOdto.getIsbn());
        livro.setGenero(livroDTOdto.getGenero());
        return livroRepository.save(livro);
    }

    public void excluirLivro(Long idLivro) {
        livroRepository.deleteById(idLivro);
    }
    public List<Livro> listarPorEmprestimo(Long emprestimoId) {
        return livroRepository.findByEmprestimoId(emprestimoId);
    }

    public Livro findByIsbn(Long isbn) {
        return livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ISBN: " + isbn));
    }
}
