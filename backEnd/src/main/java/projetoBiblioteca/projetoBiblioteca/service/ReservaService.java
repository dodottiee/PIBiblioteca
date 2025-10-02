package projetoBiblioteca.projetoBiblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBiblioteca.projetoBiblioteca.dto.ReservaDTO;
import projetoBiblioteca.projetoBiblioteca.model.Cliente;
import projetoBiblioteca.projetoBiblioteca.model.Livro;
import projetoBiblioteca.projetoBiblioteca.model.Reserva;
import projetoBiblioteca.projetoBiblioteca.model.StatusLivro;
import projetoBiblioteca.projetoBiblioteca.repository.ClienteRepository;
import projetoBiblioteca.projetoBiblioteca.repository.LivroRepository;
import projetoBiblioteca.projetoBiblioteca.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Reserva criarReserva(ReservaDTO reservaDTO) {
        Cliente cliente = clienteRepository.findById(reservaDTO.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Livro livro = livroRepository.findById(reservaDTO.getLivroId()).orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getStatus() == StatusLivro.DISPONIVEL) {
            throw new RuntimeException("O livro já está disponível para empréstimo.");
        }

        livro.setStatus(StatusLivro.RESERVADO);
        livroRepository.save(livro);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setLivro(livro);
        reserva.setDataReserva(reservaDTO.getDataReserva());

        // Lógica de notificação (ex: enviar email)
        System.out.println("Notificação: O livro " + livro.getTitulo() + " foi reservado por " + cliente.getNome());

        return reservaRepository.save(reserva);
    }
}