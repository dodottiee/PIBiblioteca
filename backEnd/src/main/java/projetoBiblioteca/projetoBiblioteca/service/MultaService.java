package projetoBiblioteca.projetoBiblioteca.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetoBiblioteca.projetoBiblioteca.model.Emprestimo;
import projetoBiblioteca.projetoBiblioteca.model.Multa;
import projetoBiblioteca.projetoBiblioteca.repository.EmprestimoRepository;
import projetoBiblioteca.projetoBiblioteca.repository.MultaRepository;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    private static final BigDecimal VALOR_MULTA_DIARIA = new BigDecimal("2.50");

    public void calcularMulta(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));

        if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataEmprestimo().plusDays(7))) { // Exemplo: 7 dias de prazo
            long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataEmprestimo().plusDays(7), emprestimo.getDataDevolucao());
            BigDecimal valorMulta = VALOR_MULTA_DIARIA.multiply(new BigDecimal(diasAtraso));

            Multa multa = new Multa();
            multa.setEmprestimo(emprestimo);
            multa.setValor(valorMulta);
            multa.setPaga(false);
            multaRepository.save(multa);
        }
    }
}