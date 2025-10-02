package projetoBiblioteca.projetoBiblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetoBiblioteca.projetoBiblioteca.model.EmprestimoLivro;
import projetoBiblioteca.projetoBiblioteca.model.Livro;
import projetoBiblioteca.projetoBiblioteca.repository.EmprestimoLivroRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private EmprestimoLivroRepository emprestimoLivroRepository;

    public Map<Livro, Long> getLivrosMaisEmprestados() {
        List<EmprestimoLivro> emprestimos = emprestimoLivroRepository.findAll();
        return emprestimos.stream()
                .collect(Collectors.groupingBy(EmprestimoLivro::getLivro, Collectors.counting()));
    }
}