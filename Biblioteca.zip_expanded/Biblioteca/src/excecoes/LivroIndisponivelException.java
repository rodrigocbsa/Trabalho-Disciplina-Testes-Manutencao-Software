package excecoes;

public class LivroIndisponivelException extends Exception {

	private static final long serialVersionUID = -6609775905618749266L;

	public LivroIndisponivelException() {
        super("Livro indisponível.");
    }
}
