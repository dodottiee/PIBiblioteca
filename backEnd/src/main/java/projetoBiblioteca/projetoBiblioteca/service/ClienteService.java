package projetoBiblioteca.projetoBiblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBiblioteca.projetoBiblioteca.dto.ClienteDTO;
import projetoBiblioteca.projetoBiblioteca.model.Cliente;
import projetoBiblioteca.projetoBiblioteca.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(ClienteDTO clienteDTO) {
        return clienteRepository.save(ClienteDTO.fromDTO(clienteDTO));
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(Long idCliente, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow();
        cliente.setNome(clienteDTO.getNome());
        cliente.setSobrenome(clienteDTO.getSobrenome());
        return clienteRepository.save(cliente);
    }

    public void excluirCliente(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }
}
