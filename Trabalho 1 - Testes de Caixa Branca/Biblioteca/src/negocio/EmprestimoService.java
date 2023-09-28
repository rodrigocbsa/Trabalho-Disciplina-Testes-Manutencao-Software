package negocio;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import excecoes.DadosNaoDefinidosException;
import excecoes.EmprestimoNaoAutorizadoException;
import excecoes.LivroIndisponivelException;
import persistencia.EmprestimoDAO;

public class EmprestimoService {
	
	private Acervo acervo;
	private EmprestimoDAO emprestimoDAO;

	public Emprestimo realizarEmprestimo(Cliente cliente, List<Livro> livros) throws LivroIndisponivelException, EmprestimoNaoAutorizadoException {
		if (cliente == null) {
			throw new DadosNaoDefinidosException("Cliente vazio.");
		}
		
		if (livros == null || livros.isEmpty()) {
			throw new DadosNaoDefinidosException("Livros vazios.");
		}
		
		int diasPermitidos = Livro.getNumeroDiasEmprestimoPadrao();
		for (Livro livro : livros) {
			if (acervo.obterQuantidadeEmEstoque(livro) == 0) {
				throw new LivroIndisponivelException(); 
			} else {
				int limiteDias = livro.calcularNumeroDiasEmprestimo(cliente, new Date());
				if(limiteDias < diasPermitidos)
				diasPermitidos = limiteDias; 
			}
		}
		
		if (diasPermitidos <= 0) {
			throw new EmprestimoNaoAutorizadoException();
        }
		
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setLivros(livros);
		emprestimo.setCliente(cliente);
		emprestimo.setDataEmprestimo(new Date());
		
		Date dataDevolucao = new Date();
		dataDevolucao = DataUtils.adicionarDias(dataDevolucao, diasPermitidos);
		
		emprestimo.setDataDevolucao(dataDevolucao);
		
		emprestimoDAO.salvar(emprestimo);
		
		return emprestimo;
	}
	
	
	public void renovarEmprestimo(Emprestimo emprestimo, int diasAdicionais) {
        // Implemente a lógica para renovar o empréstimo
        // O prazo de devolução deve ser estendido em "diasAdicionais" dias
    }
}

