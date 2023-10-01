package negocio;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ClientTest {
	
	public static Cliente cliente;
	public static TipoCliente tipo;
	
	@BeforeClass
	public static void setUp() {
		cliente = new Cliente(1,"Celso","celso@cefet-rj.br","",tipo.PADRAO);
	}

	@Test
	public void validarTelefone_quandorecebe2220203040_retornaTrue() {
		//Preparação
		String telefone = "2220203040";
		boolean espera = true;
		
		
		//Rotereiro
		boolean resultado = cliente.validarTelefone(telefone);
		
		//execução
		assertEquals(espera, resultado);
		
	}

}
