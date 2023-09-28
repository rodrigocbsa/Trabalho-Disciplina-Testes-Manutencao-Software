package excecoes;

public class EmprestimoNaoAutorizadoException extends Exception {

	private static final long serialVersionUID = -8255112106701384869L;

	public EmprestimoNaoAutorizadoException() {
        super("O usuario está bloqueado.");
    }
}
