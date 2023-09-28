package negocio;

import java.util.Date;
import java.util.List;

public class Emprestimo {
    private Cliente cliente;
    private List<Livro> livros;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int renovacoesDisponiveis;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

	public int getRenovacoesDisponiveis() {
		return renovacoesDisponiveis;
	}

	public void setRenovacoesDisponiveis(int renovacoesDisponiveis) {
		this.renovacoesDisponiveis = renovacoesDisponiveis;
	}

}

