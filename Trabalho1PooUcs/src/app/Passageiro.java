package app;

import java.util.ArrayList;

public class Passageiro extends Pessoa{
	
	public Passageiro(String nome, String cpf, String telefone) {
		super(nome, cpf, telefone);
		
	}

	//lista de corridas
	ArrayList<Corrida> corridas = new ArrayList<>();
	
	//metodo adicionar corrida
	public void adicionarcorrida(Corrida corrida) {
		corridas.add(corrida);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
