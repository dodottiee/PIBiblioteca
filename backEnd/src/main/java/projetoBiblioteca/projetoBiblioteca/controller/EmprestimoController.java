package projetoBiblioteca.projetoBiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBiblioteca.projetoBiblioteca.dto.EmprestimoDTO;
import projetoBiblioteca.projetoBiblioteca.model.Emprestimo;
import projetoBiblioteca.projetoBiblioteca.service.EmprestimoService;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> criar(@RequestBody EmprestimoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.criarEmprestimo(dto));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listar() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @PostMapping("/devolver/{id}")
    public ResponseEntity<Void> devolver(@PathVariable Long id) {
        emprestimoService.devolverLivro(id);
        return ResponseEntity.noContent().build();
    }
}