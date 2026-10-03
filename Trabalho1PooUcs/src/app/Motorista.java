package app;

import java.util.ArrayList;

public class Motorista extends Pessoa{
	private String cnh;
	private Veiculo veiculo;
	private boolean disponibilidade;
	

	
	private ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

	
	public Motorista(String nome, String cpf, String telefone, String cnh, Veiculo veiculo) {
		super(nome, cpf, telefone);
		this.cnh = cnh;
		this.veiculo = veiculo;
		this.disponibilidade = true;
	}

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

		if(avaliacoes.isEmpty()) {
			return 0;
		}

		double soma = 0;

		for(int i = 0; i < avaliacoes.size(); i++) {
			soma += avaliacoes.get(i).getNota();
		}

		return soma / avaliacoes.size();
	}

	public void listarAvaliacoes() {

		System.out.println("---Avaliações (" + avaliacoes.size() + ")---");

		for(int i = 0; i < avaliacoes.size(); i++) {
			System.out.println(avaliacoes.get(i));
		}
	}

	@Override
	public String toString() {
		String situacao;

		if(disponibilidade) {
			situacao = "Disponível";
		}
		else {
			situacao = "Em corrida";
		}

		return super.toString() + ", CNH: " + this.cnh + ", Situação: " + situacao;
	}

}
