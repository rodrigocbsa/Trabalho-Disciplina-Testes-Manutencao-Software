package persistencia;

import java.util.List;

import negocio.Livro;


public interface LivroDAO {

	public void inserirLivro(Livro Livro);

	public void atualizarLivro(Livro Livro);

	public void removerLivro(Livro Livro);

	public Livro encontrarLivroPorId(int idLivro);

	public List<Livro> encontrarTodosOsLivros();

}
