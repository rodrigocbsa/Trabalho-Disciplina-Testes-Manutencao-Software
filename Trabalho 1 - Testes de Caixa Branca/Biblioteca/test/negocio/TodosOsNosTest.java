package negocio;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import excecoes.DadosNaoDefinidosException;
import excecoes.EmprestimoNaoAutorizadoException;
import excecoes.LivroIndisponivelException;
import persistencia.EmprestimoDAO;

public class TodosOsNosTest {
	
	@InjectMocks
	private EmprestimoService service;
	
	@Mock
	private EmprestimoDAO emprestimoDAO;
	
	@Mock
	private Acervo acervo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	//Cliente vazio lança DadosNaoDefinidosException
	@Test(expected = DadosNaoDefinidosException.class)
	public void realizarEmprestimo_todosOsNos_caminho1() throws Exception{
		//Preparação dos Dados
		Cliente cliente = null;
		List<Livro> livro = null;
		//Roteiro
		service.realizarEmprestimo(cliente, livro);
		//Verificação
		//lançamento de exeção
	}
	
	//Lista de livros vazia lança DadosNaoDefinidosException
	@Test(expected = DadosNaoDefinidosException.class)
	public void realizarEmprestimo_todosOsNos_caminho2() throws Exception{
		//Preparação dos Dados
		Cliente cliente = new Cliente("Ana");
		List<Livro> livro = null;
		//Roteiro
		service.realizarEmprestimo(cliente, livro);
		//Verificação
		//lançamento de exeção
	}
	
	//Livro null deve lançar LivroIndisponivelException
	@Test(expected = LivroIndisponivelException.class)
	public void realizarEmprestimo_todosOsNos_caminho3() throws Exception{
		//Preparação dos Dados
		Cliente cliente = new Cliente("Ana");
		List<Livro> livros = new ArrayList<Livro>();
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2023");
		Livro livro1 = new Livro(1, "O Mundo de Sofia", "autor", "filosofia", 2012, 510, acervo, data);
		livros.add(livro1);
		//Roteiro
		service.realizarEmprestimo(cliente, livros);
		//Verificação
		//lançamento de exeção
	}
	
	
	//Dias permitidos igual a zero deve lançar EmprestimoNaoAutorizadoException
		@Test(expected = EmprestimoNaoAutorizadoException.class)
		public void realizarEmprestimo_todosOsNos_caminho4() throws Exception{
			//Preparação dos Dados
			Cliente cliente = new Cliente("Ana");
			List<Livro> livros = new ArrayList<Livro>();
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse("30/09/2023");
			// Cria um livro mock que retorna zero ao calcular o número de dias de empréstimo
		    Livro livro1 = mock(Livro.class);
		    when(livro1.calcularNumeroDiasEmprestimo(cliente, data)).thenReturn(0);
		    
			Mockito.when(acervo.obterQuantidadeEmEstoque(livro1)).thenReturn(1);
			livros.add(livro1);
	
			//Roteiro
			service.realizarEmprestimo(cliente, livros);
			//Verificação
			//lançamento de exeção
		}
	
	// Teste para empréstimo autorizado e bem-sucedido
    @Test
    public void realizarEmprestimo_todosOsNos_caminho5() throws Exception {
    	// Preparação dos Dados
        Cliente cliente = new Cliente("João"); // Cria um objeto real Cliente
        
        List<Livro> livros = new ArrayList<>();
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2023");
        
        // Crie um livro disponível no acervo
        Livro livro1 = new Livro(1, "O Mundo de Sofia", "autor", "filosofia", 2012, 510, acervo, data); // Cria um objeto real Livro
        Mockito.when(acervo.obterQuantidadeEmEstoque(livro1)).thenReturn(1);
        livros.add(livro1);

        // Roteiro
        Emprestimo emprestimo = service.realizarEmprestimo(cliente, livros);

        // Verificação
        verify(emprestimoDAO).salvar(emprestimo); // Verifica se o método salvar do DAO foi chamado
    }
	
	
}
