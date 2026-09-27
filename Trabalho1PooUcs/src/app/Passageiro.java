package app;

import java.util.ArrayList;

public class Passageiro {
	
	//lista de corridas
	ArrayList<Corrida> corridas = new ArrayList<>();
	
	//metodo adicionar corrida
	public void adicionarcorrida(Corrida corrida) {
		corridas.add(corrida);
	}

}
