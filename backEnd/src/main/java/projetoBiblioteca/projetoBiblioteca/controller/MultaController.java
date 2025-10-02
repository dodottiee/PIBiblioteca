package projetoBiblioteca.projetoBiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBiblioteca.projetoBiblioteca.service.MultaService;

@RestController
@RequestMapping("/multa")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @PostMapping("/calcular/{emprestimoId}")
    public ResponseEntity<Void> calcular(@PathVariable Long emprestimoId) {
        multaService.calcularMulta(emprestimoId);
        return ResponseEntity.ok().build();
    }
}