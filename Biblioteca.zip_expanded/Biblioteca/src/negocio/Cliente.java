package negocio;

public class Cliente {
	private int id;
	private String nome;
	private String email;
	private String telefone;
	private TipoCliente tipo;
	private int numeroDeAtrasos;

	public Cliente(int id, String nome, String email, String telefone, TipoCliente tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.tipo = tipo;
	}
	
	public Cliente(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getTotalAtrasos() {
		return numeroDeAtrasos;
	}
	
	public void registrarAtraso() {
		this.numeroDeAtrasos++;
	}

	public boolean validarTelefone(String telefone) {
        // Remove todos os caracteres n�o num�ricos
        String numeroLimpo = telefone.replaceAll("[^0-9]", "");

        // Verifica se o n�mero resultante possui a quantidade correta de d�gitos (10 ou 11 d�gitos)
        if (numeroLimpo.length() != 10 && numeroLimpo.length() != 11) {
            return false;
        }

        // Verifica se os dois primeiros d�gitos formam um DDD v�lido
        String ddd = numeroLimpo.substring(0, 2);
        // Lista de DDDs v�lidos (exemplo)
        String[] dddsValidos = new String[90]; // 90 DDDs v�lidos (de 11 a 99)
        for (int i = 11; i <= 99; i++) {
            dddsValidos[i - 11] = String.format("%02d", i);
        }
        boolean dddEhValido = false;
        for (String dddValido : dddsValidos) {
            if (ddd.equals(dddValido)) {
            	dddEhValido = true; 
                break;
            }
        }

        // Verifica se os d�gitos restantes formam um n�mero v�lido (n�o come�a com zero)
        String numeroTelefone = numeroLimpo.substring(2);
        boolean numeroValido = !numeroTelefone.startsWith("0");

        // Retorna true apenas se todas as condi��es forem atendidas
        return dddEhValido && numeroValido;
    }
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}

	public TipoCliente getTipo() {
		return this.tipo;
	}

	public boolean temAtrasos() {
		return this.numeroDeAtrasos > 0;
	}
}