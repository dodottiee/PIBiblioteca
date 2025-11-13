package projetoBiblioteca.projetoBiblioteca.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // A configuração de CORS aqui também é importante
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(Arrays.asList("*")); 
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
                    configuration.setAllowedHeaders(Arrays.asList("*"));
                    return configuration;
                }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Permissões de endpoint
                        .requestMatchers(HttpMethod.GET, "/livro/**").permitAll()
                        
                        // --- CORREÇÃO AQUI ---
                        // Adicionada permissão para CRIAR livros (POST /livro)
                        .requestMatchers(HttpMethod.POST, "/livro").permitAll() 
                        // --- FIM DA CORREÇÃO ---

                        .requestMatchers(HttpMethod.GET, "/cliente/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/emprestimo/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cliente").permitAll()
                        .requestMatchers(HttpMethod.POST, "/emprestimo/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/emprestimo/devolver/**").permitAll() 
                        .requestMatchers(HttpMethod.GET, "/relatorio/**").permitAll()
                        
                        .anyRequest().authenticated()
                );

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}