package projetoBiblioteca.projetoBiblioteca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica para todas as rotas
                .allowedOriginPatterns("*") // Permite qualquer requisiçãpo
                .allowedMethods("*") // Permite qualquer metodo
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite credenciais (cookies, autenticação, etc.)
    }

}