package app;

import java.util.ArrayList;

public class Passageiro extends Pessoa{

	private ArrayList<Corrida> corridas = new ArrayList<>();

	public Passageiro(String nome, String cpf, String telefone) {
		super(nome, cpf, telefone);
	}

	public void adicionarCorrida(Corrida corrida) {
		corridas.add(corrida);
	}

	public void listarCorridas() {

		System.out.println("---Corridas do passageiro " + getNome() + "---");

		if(corridas.isEmpty()) {
			System.out.println("Nenhuma corrida.");
		}

		for(int i=0;i<corridas.size();i++) {
			System.out.println(corridas.get(i));
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
