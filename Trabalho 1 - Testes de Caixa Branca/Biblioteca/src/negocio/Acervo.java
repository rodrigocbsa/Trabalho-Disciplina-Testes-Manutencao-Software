package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.LivroDAO;

public class Acervo {
    private Map<Livro, Integer> livros;
    private LivroDAO livroDAO;

    public Acervo() {
        livros = new HashMap<>();
    }

    public void adicionarLivro(Livro livro, int quantidade) {
        if (livros.containsKey(livro)) {
            livros.put(livro, livros.get(livro) + quantidade);
        } else {
            livros.put(livro, quantidade);
        }
    }

    public void removerLivro(Livro livro, int quantidade) {
        if (livros.containsKey(livro)) {
            int quantidadeAtual = livros.get(livro);
            if (quantidadeAtual >= quantidade) {
                livros.put(livro, quantidadeAtual - quantidade);
            } else {
                throw new IllegalArgumentException("Quantidade a remover � maior que a quantidade atual em estoque");
            }
        } else {
            throw new IllegalArgumentException("Livro n�o encontrado na Acervo");
        }
    }

    public int obterQuantidadeEmEstoque(Livro livro) {
        if (livros.containsKey(livro)) {
            return livros.get(livro);
        } else {
            return 0;
        }
    }

    public void ajustarEstoque(Livro livro, int quantidade) {
        if (livros.containsKey(livro)) {
            int quantidadeAtual = livros.get(livro);
            int novaQuantidade = quantidadeAtual + quantidade;
            if (novaQuantidade < 0) {
                throw new IllegalArgumentException("Quantidade a remover � maior que a quantidade atual em estoque");
            } else {
                livros.put(livro, novaQuantidade);
            }
        } else {
            throw new IllegalArgumentException("Livro n�o encontrado na Acervo");
        }
    }

    public List<Livro> validarLivros() {
        List<Livro> livros = livroDAO.encontrarTodosOsLivros();
        List<Livro> invalidos = new ArrayList<>();
        
        for (Livro livro : livros) {
            boolean valido = true;
            if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
                valido = false;
            }
            if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
                valido = false;
            }
            if (livro.getDescricao() == null || livro.getDescricao().trim().isEmpty()) {
                valido = false;
            }
            if (livro.getAnoPublicacao() <= 0) {
                valido = false;
            } 
            if (livro.getQuantidadePaginas() <= 0) {
                valido = false;
            }
            if (livro.getAcervo() == null) {
                valido = false;
            }
            if (livro.getDataLancamento() == null) {
                valido = false;
            }

            if (!valido) {
                invalidos.add(livro);
            }
        }
        
        return invalidos;
    }

}
