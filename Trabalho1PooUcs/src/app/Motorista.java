package app;

public class Motorista extends Pessoa{
	private String cnh;
	private Veiculo veiculo;
	private boolean disponibilidade;
	//adicionar lista avaliações
	
	//construtor
	public Motorista(String nome, String cpf, String telefone) {
		super(nome, cpf, telefone);
		
	}

	//getters e setters
	public String getCnh() {
		return cnh;
	}


	public void setCnh(String cnh) {
		this.cnh = cnh;
	}


	public Veiculo getVeiculo() {
		return veiculo;
	}


	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}


	public boolean getDisponibilidade() {
		return disponibilidade;
	}


	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	//metodos adicionais
	public void disponibilidade() {
		if(this.getDisponibilidade()== true) {
			System.out.println("o motorista " + getNome() + " esta disponivel");
		}
	}
	
	//ver se precisa dos metodos ocupar e liberar
	//adicionar metodos adicionarAvaliacao e calcularMediaAvaliacoes()
	

}
