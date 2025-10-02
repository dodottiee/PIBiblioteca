package projetoBiblioteca.projetoBiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBiblioteca.projetoBiblioteca.dto.ClienteDTO;
import projetoBiblioteca.projetoBiblioteca.model.Cliente;
import projetoBiblioteca.projetoBiblioteca.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody ClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criarCliente(dto));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @RequestBody ClienteDTO dto
    ) {
        return ResponseEntity.ok(clienteService.atualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
