package projetoBiblioteca.projetoBiblioteca.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetoBiblioteca.projetoBiblioteca.dto.EmprestimoDTO;
import projetoBiblioteca.projetoBiblioteca.model.Cliente;
import projetoBiblioteca.projetoBiblioteca.model.Emprestimo;
import projetoBiblioteca.projetoBiblioteca.model.EmprestimoLivro;
import projetoBiblioteca.projetoBiblioteca.model.Livro;
import projetoBiblioteca.projetoBiblioteca.model.StatusLivro;
import projetoBiblioteca.projetoBiblioteca.repository.ClienteRepository;
import projetoBiblioteca.projetoBiblioteca.repository.EmprestimoRepository;
import projetoBiblioteca.projetoBiblioteca.repository.LivroRepository;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Emprestimo criarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Cliente cliente = clienteRepository.findById(emprestimoDTO.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        List<Livro> livros = livroRepository.findAllById(emprestimoDTO.getLivroIds());

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDTO.getDataDevolucao());

        List<EmprestimoLivro> emprestimoLivros = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getStatus() != StatusLivro.DISPONIVEL) {
                throw new RuntimeException("O livro " + livro.getTitulo() + " não está disponível.");
            }
            livro.setStatus(StatusLivro.EMPRESTADO);
            EmprestimoLivro emprestimoLivro = new EmprestimoLivro();
            emprestimoLivro.setEmprestimo(emprestimo);
            emprestimoLivro.setLivro(livro);
            emprestimoLivros.add(emprestimoLivro);
        }

        emprestimo.setEmprestimoLivros(emprestimoLivros);

        return emprestimoRepository.save(emprestimo);
    }

    public void devolverLivro(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));
        emprestimo.setDataDevolucao(LocalDate.now());

        for (EmprestimoLivro emprestimoLivro : emprestimo.getEmprestimoLivros()) {
            Livro livro = emprestimoLivro.getLivro();
            livro.setStatus(StatusLivro.DISPONIVEL);
            livroRepository.save(livro);
        }

        emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }
}