package negocio;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import persistencia.LivroDAO;

public class MulticondicaoTest {

	@InjectMocks
	private Acervo acervo;
	
	@Mock
	private LivroDAO livroDAO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	//Todas as condições são verdadeiras
	@Test
	public void validarLivros_todasAsInformacoesSaoInvalidas() {
    	// Preparação dos Dados
		List<Livro> livros = new ArrayList<Livro>();
		Livro livro1 = new Livro(0, null, null, null, 0, 0, null, null);
		livros.add(livro1);
		Mockito.when(livroDAO.encontrarTodosOsLivros()).thenReturn(livros);
		//Roteiro
		List<Livro> invalidos = acervo.validarLivros();
		//Validação
		assertEquals(invalidos, livros);
	}
	
	//Todas as condições 3 primeiras verdadeiras e 4 ultimas falsas
	@Test
	public void validarLivros_as3PrimeirasInformacoesSaoInvalidas() {
	   	// Preparação dos Dados
		List<Livro> livros = new ArrayList<Livro>();
		Livro livro2 = new Livro(0, "", "", "", 2012, 100, acervo, new Date());
		livros.add(livro2);
		Mockito.when(livroDAO.encontrarTodosOsLivros()).thenReturn(livros);
		//Roteiro
		List<Livro> invalidos = acervo.validarLivros();
		//Validação
		assertEquals(invalidos, livros);
	}
		
	//Todas as condições falsas
	@Test
	public void validarLivros_todasAsInformacoesSaoValidas() {
		// Preparação dos Dados
		List<Livro> livros = new ArrayList<Livro>();
		Livro livro2 = new Livro(1, "Livro", "Autor", "Descricao", 2012, 100, acervo, new Date());
		livros.add(livro2);
		Mockito.when(livroDAO.encontrarTodosOsLivros()).thenReturn(livros);
		//Roteiro
		List<Livro> invalidos = acervo.validarLivros();
		//Validação
		assertEquals(invalidos, new ArrayList<>());
	}

}
