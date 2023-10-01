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
        // Implemente a l�gica para renovar o empr�stimo
        // O prazo de devolu��o deve ser estendido em "diasAdicionais" dias
		//Instancia a Data dentro de Emprestimo
		Date dataDevolucao = emprestimo.getDataDevolucao();
		Date dataEmprestimo = emprestimo.getDataEmprestimo();
		
		Date Atraso = Calendar.getInstance().getTime();
		
		if((diasAdicionais > 0) && (dataDevolucao != null && dataEmprestimo.compareTo(dataDevolucao)< 0) && dataDevolucao.compareTo(Atraso) >= 0) {
			if(emprestimo.getRenovacoesDisponiveis()> 0 || emprestimo.getCliente().getTipo() == TipoCliente.FUNCIONARIO_BIBLIOTECA) {
			emprestimo.setDataDevolucao(DataUtils.adicionarDias(dataDevolucao, diasAdicionais));
			emprestimo.setRenovacoesDisponiveis(emprestimo.getRenovacoesDisponiveis()-1);
			}else
				throw new DadosNaoDefinidosException("Sem Renovações disponiveis");	
		}
		
		else if(dataDevolucao == null || dataEmprestimo.compareTo(dataDevolucao)> 0 || diasAdicionais <= 0) {
			throw new DadosNaoDefinidosException("Dados não definidos ou invalidos");
		} else if(dataDevolucao.compareTo(Atraso) < 0) {
			emprestimo.getCliente().registrarAtraso();
			throw new DadosNaoDefinidosException("Emprestimo em Atraso, não é permitido renovar emprestimo");
		}
	}
}
