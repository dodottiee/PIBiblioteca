package projetoBiblioteca.projetoBiblioteca.handler;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String titulo;
    private Integer status;
    private String detalhes;
    private LocalDateTime timestamp;

    public ErrorResponse(String titulo, int status, String detalhes) {
        this.titulo = titulo;
        this.status = status;
        this.detalhes = detalhes;
        this.timestamp = LocalDateTime.now();
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

}
