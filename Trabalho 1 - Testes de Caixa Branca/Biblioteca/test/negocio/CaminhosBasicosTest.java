package negocio;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CaminhosBasicosTest {

	//Caminho 1: I,1,2,12,O
	//data de lançamento do livro tem menos que 30 dias
	@Test
	public void calcularNumeroDiasEmprestimo_caminhosBasico_caminho1() throws Exception{
		//Preparação dos Dados
		Cliente cliente = new Cliente("João");
		// Configurando a data de lançamento do livro com mais de 30 dias atrás
	    Livro livro = new Livro(1, "Livro Teste", "Autor", "Descrição", 2023, 200, null, new Date());
		//Roteiro
        int resultado = livro.calcularNumeroDiasEmprestimo(cliente, new Date());
        //Verificação
        assertEquals(7, resultado);
	}
	
	//Caminho 2: i,1,3,5,8,10,12,o
	//data de lançamento do livro tem mais de 30 dias
	@Test
	public void calcularNumeroDiasEmprestimo_caminhosBasico_caminho2() throws Exception{
		// Preparação dos Dados
        Cliente cliente = new Cliente("João");
        // Configurando a data de lançamento do livro com mais de 30 dias atrás
        Date dataLancamento = new Date(System.currentTimeMillis() - 31L * 24L * 60L * 60L * 1000L); // 31 dias atrás
        Acervo acervo = new Acervo();
        Livro livro = new Livro(1, "Livro Teste", "Autor", "Descrição", 2023, 200, acervo, dataLancamento);
        
        // Roteiro
        int resultado = livro.calcularNumeroDiasEmprestimo(cliente, new Date());
        // Verificação
        assertEquals(14, resultado);
    } 
	 
	//Caminho 3: i,1,3,5,8,10,11,o
	// cliente é FUNCIONARIO da BIBLIOTECA
	@Test
	public void calcularNumeroDiasEmprestimo_caminhosBasico_caminho3() throws Exception{
		// Preparação dos Dados
	    Cliente cliente = new Cliente(0, "João", "email@email.com", "(00)0000-0000", TipoCliente.FUNCIONARIO_BIBLIOTECA);
	    
	    // Configurando a data de lançamento do livro com mais de 30 dias atrás
	    Date dataLancamento = new Date(System.currentTimeMillis() - 31L * 24L * 60L * 60L * 1000L); // 31 dias atrás
	    Acervo acervo = new Acervo();
	    Livro livro = new Livro(1, "Livro Teste", "Autor", "Descrição", 2023, 200, acervo, dataLancamento);
 
	    // Roteiro
	    int resultado = livro.calcularNumeroDiasEmprestimo(cliente, new Date());
	    // Verificação
	    assertEquals(28, resultado);
	}  
	
	//Caminho 4: i,1,3,4,5,8,10,12,o
	// quantidadeExemplaresNoAcervo maior que um
	@Test
	public void calcularNumeroDiasEmprestimo_caminhosBasico_caminho4() throws Exception{
		// Preparação dos Dados
	    Cliente cliente = new Cliente("João");
	    
	    // Configurando a data de lançamento do livro com mais de 30 dias atrás
	    Date dataLancamento = new Date(System.currentTimeMillis() - 31L * 24L * 60L * 60L * 1000L); // 31 dias atrás
	    Acervo acervo = new Acervo();
	    Livro livro = new Livro(1, "Livro Teste", "Autor", "Descrição", 2023, 200, acervo, dataLancamento);
	    acervo.adicionarLivro(livro, 2);
	    
	    // Roteiro
	    int resultado = livro.calcularNumeroDiasEmprestimo(cliente, new Date());
	    // Verificação
	    assertEquals(21, resultado);
	} 
	
	//Caminho 5: i,1,3,4,5,6,7,8,10,12,o
	// cliente 1 tem atraso
	@Test
	public void calcularNumeroDiasEmprestimo_caminhosBasico_caminho5() throws Exception{
		// Preparação dos Dados
	    Cliente cliente = new Cliente("João");
	    cliente.registrarAtraso();
	    
	    // Configurando a data de lançamento do livro com mais de 30 dias atrás
	    Date dataLancamento = new Date(System.currentTimeMillis() - 31L * 24L * 60L * 60L * 1000L); // 31 dias atrás
	    Acervo acervo = new Acervo();
	    Livro livro = new Livro(1, "Livro Teste", "Autor", "Descrição", 2023, 200, acervo, dataLancamento);
	    acervo.adicionarLivro(livro, 2);
	    
	    // Roteiro
	    int resultado = livro.calcularNumeroDiasEmprestimo(cliente, new Date());
	    // Verificação
	    assertEquals(21, resultado);
	}
	
	//Caminho 7: i,1,3,4,5,6,7,8,10,12,o
	// cliente mais 1 tem atraso e é PREMIUM
	@Test
	public void calcularNumeroDiasEmprestimo_caminhosBasico_caminho7() throws Exception{
		// Preparação dos Dados
	    Cliente cliente = new Cliente(0, "João", "email@email.com", "(00)0000-0000", TipoCliente.PREMIUM);
	    cliente.registrarAtraso();
	    cliente.registrarAtraso();
		    
	    // Configurando a data de lançamento do livro com mais de 30 dias atrás
	    Date dataLancamento = new Date(System.currentTimeMillis() - 31L * 24L * 60L * 60L * 1000L); // 31 dias atrás
	    Acervo acervo = new Acervo();
	    Livro livro = new Livro(1, "Livro Teste", "Autor", "Descrição", 2023, 200, acervo, dataLancamento);
	    acervo.adicionarLivro(livro, 2);
		    
	    // Roteiro
	    int resultado = livro.calcularNumeroDiasEmprestimo(cliente, new Date());
	    // Verificação
	    assertEquals(28, resultado);
	}
	
} 
