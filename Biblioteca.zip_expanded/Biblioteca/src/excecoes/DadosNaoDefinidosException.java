package excecoes;

public class DadosNaoDefinidosException extends RuntimeException {

	private static final long serialVersionUID = 8951258828547987048L;

	public DadosNaoDefinidosException(String mensagem) {
        super(mensagem);
    }
}
