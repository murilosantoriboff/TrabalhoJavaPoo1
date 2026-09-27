package app;

public class Pessoa {
	private String nome;
	private String CPF;
	private String telefone;
	
	
	//construtor
	public Pessoa(String nome, String cPF, String telefone) {
		super();
		this.nome = nome;
		CPF = cPF;
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
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
