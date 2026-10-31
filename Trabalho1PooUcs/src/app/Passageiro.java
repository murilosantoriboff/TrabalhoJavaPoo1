package app;

import java.util.ArrayList;
import java.util.List;

public class Passageiro extends Pessoa{

	private List<Corrida> corridas;

	public Passageiro(String nome, String cpf, String telefone) {
		super(nome, cpf, telefone);
		this.corridas = new ArrayList<>();
	}

	public void adicionarCorrida(Corrida corrida) {
		corridas.add(corrida);
	}

	public List<Corrida> getCorridas() {
		return new ArrayList<>(corridas);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
