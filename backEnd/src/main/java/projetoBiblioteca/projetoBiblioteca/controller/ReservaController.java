package projetoBiblioteca.projetoBiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoBiblioteca.projetoBiblioteca.dto.ReservaDTO;
import projetoBiblioteca.projetoBiblioteca.model.Reserva;
import projetoBiblioteca.projetoBiblioteca.service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody ReservaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.criarReserva(dto));
    }
}