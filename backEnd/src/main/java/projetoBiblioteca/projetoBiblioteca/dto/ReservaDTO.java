package projetoBiblioteca.projetoBiblioteca.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservaDTO {
    private Long clienteId;
    private Long livroId;
    private LocalDate dataReserva;

    // Getters and Setters
}