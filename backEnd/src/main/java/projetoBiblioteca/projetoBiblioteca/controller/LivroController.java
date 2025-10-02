package projetoBiblioteca.projetoBiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBiblioteca.projetoBiblioteca.dto.LivroDTO;
import projetoBiblioteca.projetoBiblioteca.model.Livro;
import projetoBiblioteca.projetoBiblioteca.service.LivroService;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody LivroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criarLivro(dto));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(livroService.listarTodos());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(
            @PathVariable Long id,
            @RequestBody LivroDTO dto
    ) {
        return ResponseEntity.ok(livroService.atualizarLivro(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        livroService.excluirLivro(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/por-emprestimo/{emprestimoId}")
    public ResponseEntity<List<Livro>> listarPorEmprestimo(@PathVariable Long emprestimoId) {
        List<Livro> livros = livroService.listarPorEmprestimo(emprestimoId); // Chama o service
        return ResponseEntity.ok(livros);
    }

}
