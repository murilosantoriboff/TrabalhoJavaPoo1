package app;

public abstract class Pessoa {
	private String nome;
	private String CPF;
	private String telefone;
	
	
	//construtor
	public Pessoa(String nome, String cpf, String telefone) {
		super();
		this.nome = nome;
		this.CPF = cpf;
		this.telefone = telefone;
	}	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cpf) {
		CPF = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
