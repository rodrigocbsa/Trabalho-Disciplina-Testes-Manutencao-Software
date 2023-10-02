package negocio;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class AcervoTest {
	
	private Acervo acervo;
	private Livro livro;
	
	@Before
	public void setUp() {
		acervo = new Acervo();
		livro = new Livro(1, "Livro", "Autor", "Descricao", 2012, 100, acervo, new Date());
	}

	@Test
	public void removerLivro_quandoLivroExistir_deveAtualizarQuantidade() {
		// Preparação dos Dados
		acervo.adicionarLivro(livro, 10);
		//Roteiro
		acervo.removerLivro(livro, 2);
		//Validação
		assertEquals(8, acervo.obterQuantidadeEmEstoque(livro));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removerLivro_quandoQuantidadeMenorQueLivrosExistentes_deveLancarExecao() {
		// Preparação dos Dados
		acervo.adicionarLivro(livro, 1);
		//Roteiro
		acervo.removerLivro(livro, 2);
		//Validação
		//lançamento de exeção
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removerLivro_quandoLivroNãoExistirNoAcervo_deveLancarExecao() {
		// Preparação dos Dados
		//Não adiciona o livro no acervo
		//Roteiro
		acervo.removerLivro(livro, 2);
		//Validação
		//lançamento de exeção
	}

	@Test
	public void ajustarEstoque_quandoLivroExistir_deveAtualizarQuantidade() {
		// Preparação dos Dados
		acervo.adicionarLivro(livro, 5);
		//Roteiro
		acervo.ajustarEstoque(livro, 5);
		//Validação
		assertEquals(10, acervo.obterQuantidadeEmEstoque(livro));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ajustarEstoque_quandoQuantidadeMenorQueLivrosExistentes_deveLancarExecao() {
		// Preparação dos Dados
		acervo.adicionarLivro(livro, 4);
		//Roteiro
		acervo.ajustarEstoque(livro, -5);
		//Validação
		//lançamento de exeção
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void ajustarEstoque_quandoLivroNãoExistirNoAcervo_deveLancarExecao() {
		// Preparação dos Dados
		//Não adiciona o livro no acervo
		//Roteiro
		acervo.ajustarEstoque(livro, -5);
		//Validação
		//lançamento de exeção
	}
	
	@Test
	public void adicionarLivro_quandoReceberLivroExistente_deveAtualizarQuantidade() {
		// Preparação dos Dados
		acervo.adicionarLivro(livro, 2);
		
		//Roteiro
		acervo.adicionarLivro(livro, 3);
		//Validação
		assertEquals(5, acervo.obterQuantidadeEmEstoque(livro));
	}

}
