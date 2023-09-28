package persistencia;

import java.util.List;

import negocio.Emprestimo;

public interface EmprestimoDAO {
    
    void salvar(Emprestimo emprestimo);

    void atualizar(Emprestimo emprestimo);

    void remover(Emprestimo emprestimo);

    Emprestimo buscarPorId(int id);

    List<Emprestimo> obterTodosOsEmprestimos();

    List<Emprestimo> obterEmprestimosPendentes();
}

