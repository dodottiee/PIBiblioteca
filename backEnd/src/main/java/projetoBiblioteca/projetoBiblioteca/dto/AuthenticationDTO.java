package projetoBiblioteca.projetoBiblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
}