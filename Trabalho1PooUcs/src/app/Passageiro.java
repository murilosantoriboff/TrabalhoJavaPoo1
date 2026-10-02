package app;

import java.util.ArrayList;

public class Passageiro extends Pessoa{
	
	public Passageiro(String nome, String cpf, String telefone) {
		super(nome, cpf, telefone);
		
	}


	private ArrayList<Corrida> corridas = new ArrayList<>();

	
	public void adicionarcorrida(Corrida corrida) {
		corridas.add(corrida);
	}

}
