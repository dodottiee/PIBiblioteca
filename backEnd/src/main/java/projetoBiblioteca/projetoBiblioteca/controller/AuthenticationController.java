package projetoBiblioteca.projetoBiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetoBiblioteca.projetoBiblioteca.dto.AuthenticationDTO;
import projetoBiblioteca.projetoBiblioteca.model.Usuario;
import projetoBiblioteca.projetoBiblioteca.repository.UsuarioRepository;
import projetoBiblioteca.projetoBiblioteca.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    // DTO de resposta para o login (o frontend espera { token: "..." })
    private record LoginResponseDTO(String token) {}

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        // Cria o token de autenticação do Spring Security com as credenciais
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());
        
        // Tenta autenticar
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        // Gera o token JWT
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        
        // Retorna a resposta
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthenticationDTO data) {
        // Verifica se o usuário já existe para evitar duplicidade
        if (this.repository.findByLogin(data.getLogin()) != null) {
            // Retorna 409 Conflict se o usuário já existe
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Criptografa a senha e salva o novo usuário
        String encryptedPassword = passwordEncoder.encode(data.getSenha());
        Usuario newUser = new Usuario(null, data.getLogin(), encryptedPassword);
        
        this.repository.save(newUser);

        // Retorna 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}