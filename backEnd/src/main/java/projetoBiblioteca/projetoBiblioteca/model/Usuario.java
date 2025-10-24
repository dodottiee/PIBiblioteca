package projetoBiblioteca.projetoBiblioteca.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String login;
    
    @NotBlank
    private String senha;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Sem roles por enquanto
    }

    @Override
    public String getPassword() {
        return this.senha; // Retorna a senha hashed
    }

    @Override
    public String getUsername() {
        return this.login; // Retorna o campo usado como username
    }

    // Os métodos abaixo controlam o status da conta.
    // Para simplificar, vamos considerar que as contas estão sempre ativas.
    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Conta nunca está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credenciais (senha) nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // Conta está sempre habilitada
    }
}
