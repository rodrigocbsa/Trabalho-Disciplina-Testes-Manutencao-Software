package negocio;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import excecoes.DadosNaoDefinidosException;

public class RenovarEmprestimoTest {
	
	@InjectMocks
	private EmprestimoService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
	}
	@Test
	public void renovarEmprestimo_quandoRecebeUmEmprestimoDia25DeOutubroe5_retorna30deOutubro() {
		//Preparação
		Emprestimo emprestimo = new Emprestimo();
		Cliente cliente = new Cliente(1, "Ana", "ana@cefet-rj.br", "2222226555", TipoCliente.PADRAO);
		emprestimo.setCliente(cliente);
		emprestimo.setRenovacoesDisponiveis(1);
		Date datadev = new GregorianCalendar(2023,Calendar.OCTOBER,25).getTime();
		Date dataemp = new GregorianCalendar(2023,Calendar.OCTOBER,20).getTime();
		emprestimo.setDataDevolucao(datadev);
		emprestimo.setDataEmprestimo(dataemp);
		Date esperado = new GregorianCalendar(2023,Calendar.OCTOBER,30).getTime();
		int dias = 5;
		//Roteiro
		service.renovarEmprestimo(emprestimo, dias);
		Date resultado = emprestimo.getDataDevolucao();
		

		//Resultado
		assertEquals(esperado,resultado);
	}
	
	@Test(expected=DadosNaoDefinidosException.class)
	public void renovarEmprestimo_quandoRecebeUmEmprestimoeMenos2_RetornaErro(){
		//Preparação
		Emprestimo emprestimo = new Emprestimo();
		Cliente cliente = new Cliente(1, "Ana", "ana@cefet-rj.br", "2222226555", TipoCliente.PADRAO);
		emprestimo.setCliente(cliente);
		emprestimo.setRenovacoesDisponiveis(1);
		Date data = new GregorianCalendar(2023,Calendar.OCTOBER,20).getTime();
		Date dataemp = new GregorianCalendar(2023,Calendar.OCTOBER,10).getTime();
		emprestimo.setDataDevolucao(data);
		emprestimo.setDataEmprestimo(dataemp);
		int dias = -2;
		
		//Roteiro
		service.renovarEmprestimo(emprestimo, dias);
		
		
		//Resultado
		//Espera lançamento de exceção
	}
	@Test(expected=DadosNaoDefinidosException.class)
	public void renovarEmprestimo_quandoRecebeUmEmprestimoComDataNulae1_lancaDadosNaoDefinidosException(){
		//Preparação
		Emprestimo emprestimo = new Emprestimo();
		Cliente cliente = new Cliente(1, "Ana", "ana@cefet-rj.br", "2222226555", TipoCliente.PADRAO);
		emprestimo.setCliente(cliente);
		emprestimo.setRenovacoesDisponiveis(1);
		Date data = null;
		Date dataemp = new GregorianCalendar(2023,Calendar.SEPTEMBER,29).getTime();
		emprestimo.setDataDevolucao(data);
		emprestimo.setDataEmprestimo(dataemp);
		int dias = 1;
		
		//Roteiro
		service.renovarEmprestimo(emprestimo, dias);
		
		
		//Resultado
		//Espera lançamento de exceção
	}
	@Test(expected = DadosNaoDefinidosException.class)
	public void renovarEmprestimo_quandoRecebeUmEmprestimoAtrasado_lancaDadosNaoDefinidosException() {
		//Preparação
		Emprestimo emprestimo = new Emprestimo();
		Cliente cliente = new Cliente(1, "Ana", "ana@cefet-rj.br", "2222226555", TipoCliente.PADRAO);
		emprestimo.setCliente(cliente);
		emprestimo.setRenovacoesDisponiveis(1);
		Date datadev = new GregorianCalendar(2023,Calendar.SEPTEMBER,25).getTime();
		Date dataemp = new GregorianCalendar(2023,Calendar.SEPTEMBER,20).getTime();
		emprestimo.setDataDevolucao(datadev);
		emprestimo.setDataEmprestimo(dataemp);
		int dias = 10;
		
		//Roteiro
		service.renovarEmprestimo(emprestimo, dias);
	}
	@Test(expected = DadosNaoDefinidosException.class)
	public void renovarEmprestimo_quandoRecebeUmEmprestimoSemRenovacao_lancaDadosNaoDefinidosException() {
		//Preparação
		Emprestimo emprestimo = new Emprestimo();
		Cliente cliente = new Cliente(1, "Ana", "ana@cefet-rj.br", "2222226555", TipoCliente.PADRAO);
		emprestimo.setCliente(cliente);
		emprestimo.setRenovacoesDisponiveis(0);
		Date datadev = new GregorianCalendar(2023,Calendar.OCTOBER,20).getTime();
		Date dataemp = new GregorianCalendar(2023,Calendar.OCTOBER,10).getTime();
		emprestimo.setDataDevolucao(datadev);
		emprestimo.setDataEmprestimo(dataemp);
		int dias = 10;
		
		//Roteiro
		service.renovarEmprestimo(emprestimo, dias);
	}
	@Test
	public void renovarEmprestimo_quandoRecebeUmClienteFuncionarioEmEmprestimoSemrenovacao_retornaValoresAtribuidos() {
		//Preparação
		Emprestimo emprestimo = new Emprestimo();
		Cliente cliente = new Cliente(2, "Carolina", "carolina@cefet-rj.br", "2222226555", TipoCliente.FUNCIONARIO_BIBLIOTECA);
		emprestimo.setCliente(cliente);
		emprestimo.setRenovacoesDisponiveis(0);
		Date datadev = new GregorianCalendar(2023,Calendar.OCTOBER,20).getTime();
		Date dataemp = new GregorianCalendar(2023,Calendar.OCTOBER,10).getTime();
		emprestimo.setDataDevolucao(datadev);
		emprestimo.setDataEmprestimo(dataemp);
		int dias = 10;
		
		//Roteiro
		service.renovarEmprestimo(emprestimo, dias);
		
		//resultado
		Date resultado = new GregorianCalendar(2023,Calendar.OCTOBER,30).getTime();
		assertEquals(resultado,emprestimo.getDataDevolucao());
	}
	@Test(expected = DadosNaoDefinidosException.class)
	public void renovarEmprestimo_quandoRecebeUmClientePremiumEmEmprestimoSemrenovacao_lancaDadosNaoDefinidosException() {
		//Preparação
		Emprestimo emprestimo = new Emprestimo();
		Cliente cliente = new Cliente(2, "Carolina", "carolina@cefet-rj.br", "2222226555", TipoCliente.PREMIUM);
		emprestimo.setCliente(cliente);
		emprestimo.setRenovacoesDisponiveis(0);
		Date datadev = new GregorianCalendar(2023,Calendar.OCTOBER,20).getTime();
		Date dataemp = new GregorianCalendar(2023,Calendar.OCTOBER,10).getTime();
		emprestimo.setDataDevolucao(datadev);
		emprestimo.setDataEmprestimo(dataemp);
		int dias = 10;
		
		service.renovarEmprestimo(emprestimo, dias);
	}
}
