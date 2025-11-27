package projetoBiblioteca.projetoBiblioteca.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import projetoBiblioteca.projetoBiblioteca.model.Usuario;

@Service
public class TokenService {

    // A chave secreta é carregada do application.properties. 
    // É crucial que esta chave tenha pelo menos 32 caracteres (256 bits) para HS256.
    @Value("${api.security.token.secret:minha-super-chave-secreta-de-32-bytes}") 
    private String secret;

    /**
     * Gera a chave de assinatura a partir da string secreta.
     * Esta chave é usada tanto para assinar quanto para validar o token.
     */
    private SecretKey getSigningKey() {
        // Keys.hmacShaKeyFor cria uma SecretKey segura a partir do array de bytes.
        return Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    /**
     * Gera a data de expiração (2 horas a partir de agora).
     */
    private Date genExpirationDate(){
        // Converte de LocalDateTime para o objeto Date, que é o formato esperado pelo JJWT.
        return Date.from(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")));
    }
    
    /**
     * Gera o token JWT para o usuário.
     */
    public String generateToken(Usuario usuario){
        try {
            return Jwts.builder()
                    .setIssuer("auth-api") // Emissor do token
                    .setSubject(usuario.getLogin()) // Usuário (login)
                    .setExpiration(genExpirationDate()) // Define a expiração
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Assina com a chave e algoritmo
                    .compact(); // Constrói e serializa
        } catch (Exception exception) {
            // Captura qualquer exceção durante a criação, como chave fraca (JWTSigningException)
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    /**
     * Valida o token e retorna o login do usuário (Subject).
     * Retorna uma string vazia em caso de token inválido ou expirado.
     */
    public String validateToken(String token){
        try {
            // O parser verifica a assinatura, a expiração e o emissor.
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Chave para validar a assinatura
                    .build()
                    .parseClaimsJws(token) // Faz o parse e a validação do token
                    .getBody()
                    .getSubject(); // Retorna o campo Subject (login do usuário)
        } catch (SignatureException e) {
            // Assinatura inválida (token adulterado)
            return ""; 
        } catch (Exception exception){
            // Captura outras exceções (token expirado, malformado, etc.)
            return ""; 
        }
    }
}