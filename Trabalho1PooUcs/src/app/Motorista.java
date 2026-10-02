package app;

import java.util.ArrayList;

public class Motorista extends Pessoa{
	private String cnh;
	private Veiculo veiculo;
	private boolean disponibilidade;
	
	//lista avaliacoes
	private ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
	
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
			System.out.println("O motorista " + getNome() + " esta disponivel!");
		}
	}
	
	public void ocupar() {
		this.setDisponibilidade(false);
	}
	
	public void desocupar() {
		this.setDisponibilidade(true);
	}
	
	public void adicionarAvaliacao(int nota, String descricao) {
		
		Avaliacao avaliacao = new Avaliacao(nota, descricao);
		
		avaliacoes.add(avaliacao);
		
	}

	public double calcularMediaAvaliacoes() {
		
		int soma = 0;
		
		for(int i = 0; i < avaliacoes.size(); i++) {
			soma += avaliacoes.get(i).getNota();
		}
		
		soma /= avaliacoes.size();
		return (soma);
	}

}
