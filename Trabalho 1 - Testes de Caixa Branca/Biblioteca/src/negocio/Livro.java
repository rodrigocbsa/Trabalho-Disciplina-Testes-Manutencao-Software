package negocio;

import java.util.Date;

import excecoes.DadosNaoDefinidosException;
import excecoes.EmprestimoNaoAutorizadoException;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private String descricao;
    private int anoPublicacao;
    private int quantidadePaginas;
    private Acervo acervo;
    private Date dataLancamento;
    private static final int numeroDiasEmprestimo = 14;

	public Livro(int idLivro, String titulo, String autor, String descricao, int anoPublicacao, int quantidadePaginas, Acervo acervo, Date dataLancamento) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.anoPublicacao = anoPublicacao;
        this.quantidadePaginas = quantidadePaginas;
        this.acervo = acervo;
        this.dataLancamento = dataLancamento;
    }

    public static int getNumeroDiasEmprestimoPadrao() {
		return numeroDiasEmprestimo;
	}

	public int getIdLivro() {
        return idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getQuantidadePaginas() {
        return quantidadePaginas;
    }

    public void setQuantidadePaginas(int quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public Acervo getAcervo() {
        return acervo;
    }

    public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

    public boolean estaEmAcervo() {
    	int quantidade = 0;
    	try {
    		quantidade = acervo.obterQuantidadeEmEstoque(this);
    	} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
        return quantidade > 0; 
    }
    

    public int calcularNumeroDiasEmprestimo(Cliente cliente, Date dataAtual) throws EmprestimoNaoAutorizadoException {
    	int numeroDias = numeroDiasEmprestimo;
        if (ehLancamento(dataAtual)) {
            numeroDias = numeroDias/2;
        } else {
            // Se não for um lançamento, verificar outras condições 
            if (quantidadeExemplaresNoAcervo() > 1) {
            	numeroDias = numeroDias + numeroDias/2;
            }

            if (cliente.temAtrasos()) {
                // Se o cliente tiver atrasos, reduzir o prazo em 1 dia para cada atraso
                int atrasos = cliente.getTotalAtrasos();
                if (atrasos > 1) {
                	numeroDias -= atrasos;
                }
            }

            if (cliente.getTipo() == TipoCliente.PREMIUM) {
                // Se o cliente for premium, adicionar dias ao prazo
            	numeroDias += numeroDias/2;
            }

            if (cliente.getTipo() == TipoCliente.FUNCIONARIO_BIBLIOTECA) {
                // Se o cliente for funcionário da biblioteca, adicionar dias ao prazo
            	numeroDias += numeroDias;
            }
        }

        return numeroDias;
    }

    public boolean ehLancamento(Date dataAtual) {
    	boolean lancamento = false;
        if (dataLancamento == null) {
            throw new DadosNaoDefinidosException("Data de lançamento não definida.");
        }
        
        long trintaDiasAtras = dataAtual.getTime() - (30L * 24L * 60L * 60L * 1000L);

        if(dataLancamento.getTime() >= trintaDiasAtras) {
        	lancamento = true; 
        }
        
        return lancamento;
    }


    private int quantidadeExemplaresNoAcervo() {
    	int quantidade = 0;
    	try {
    		quantidade = acervo.obterQuantidadeEmEstoque(this);
    	} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
        return quantidade;
    }
}

