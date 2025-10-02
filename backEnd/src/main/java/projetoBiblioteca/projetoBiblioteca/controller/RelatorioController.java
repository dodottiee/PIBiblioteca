package projetoBiblioteca.projetoBiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoBiblioteca.projetoBiblioteca.model.Livro;
import projetoBiblioteca.projetoBiblioteca.service.RelatorioService;
import java.util.Map;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/livros-mais-emprestados")
    public ResponseEntity<Map<Livro, Long>> getLivrosMaisEmprestados() {
        return ResponseEntity.ok(relatorioService.getLivrosMaisEmprestados());
    }
}