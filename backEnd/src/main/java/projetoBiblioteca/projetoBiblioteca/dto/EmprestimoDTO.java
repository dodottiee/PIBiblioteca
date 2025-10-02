package projetoBiblioteca.projetoBiblioteca.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class EmprestimoDTO {
    private Long clienteId;
    private List<Long> livroIds;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    // Getters and Setters
}