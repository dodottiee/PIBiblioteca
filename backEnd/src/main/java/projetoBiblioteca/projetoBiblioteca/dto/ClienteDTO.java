package projetoBiblioteca.projetoBiblioteca.dto;

import projetoBiblioteca.projetoBiblioteca.model.Cliente;

public class ClienteDTO {
    private String nome;
    private String sobrenome;
    private String cpf;

    public static Cliente fromDTO(ClienteDTO clienteDTOdto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTOdto.getNome());
        cliente.setSobrenome(clienteDTOdto.getSobrenome());
        cliente.setCpf(clienteDTOdto.getCpf());
        return cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
