package negocio;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DataUtilsTest {
	
	
	@Test
	public void adicionarDias_quandoRecebe28DeSetembro2023e5_retorna02Outubro2023() {
		//Prepara��o dos dados
		GregorianCalendar dataPreFormatada = new GregorianCalendar(2023,Calendar.SEPTEMBER,28); 
		Date data = dataPreFormatada.getTime();
		int qtdDias = 5;
		Calendar esperado = new GregorianCalendar(2023,Calendar.OCTOBER,3);
		
		//Roteiro
		Date resultado = DataUtils.adicionarDias(data, qtdDias); 
		
		//Resultado
		assertEquals(esperado.getTime(), resultado);
	}
	@Test
	public void verificarDiaSemana_quandoRecebe25DeDezembro2023e2_retornaTrue() {
		//Prepara��o dos dados
		Calendar dataPreFormatada = new GregorianCalendar(2023,Calendar.DECEMBER,25); //Segunda 
		Date data = dataPreFormatada.getTime();
		int diaSemana = 2; //segunda
		Boolean esperado = true;
		//Roteiro
		Boolean resultado = DataUtils.verificarDiaSemana(data, diaSemana);
		
		//Resultado
		assertEquals(esperado, resultado);
	}
	@Test
	public void verificarDiaSemana_quandoRecebe11DeDezembro2023e1_retornaFalse() {
		//Prepara��o dos dados
		Calendar dataPreFormatada = new GregorianCalendar(2023,Calendar.DECEMBER,11); //Segunda
		Date data = dataPreFormatada.getTime();
		int diaSemana = 1; //Domingo
		Boolean esperado = false;
		//Roteiro
		Boolean resultado = DataUtils.verificarDiaSemana(data, diaSemana);
		
		//Resultado
		assertEquals(esperado, resultado);
	}
	@Test
	public void obterDataComDiferencaDias_quandoRecebe10_retornaDiaAtualMais10() {
		//Prepara��o dos dados
		Calendar dataPreFormatada = Calendar.getInstance(); 
		dataPreFormatada.add(Calendar.DAY_OF_MONTH,10);
		Date dataEsperada = dataPreFormatada.getTime();
		//Roteiro
		Date resultado = DataUtils.obterDataComDiferencaDias(10); 
		//Resultado
		assertEquals(dataEsperada, resultado);
	}

}
