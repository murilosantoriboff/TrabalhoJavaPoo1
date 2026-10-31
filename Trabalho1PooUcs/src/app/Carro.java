package app;

public class Carro extends Veiculo {

	public Carro(){
	
	}
	
	public Carro(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		super(placa, modelo, marca, ano, qtdPassageiros);
		valorKm();
		valorVeiculo();
	}

	
	private void valorKm() {
		valorKm = 2.00;
	}

	private void valorVeiculo() {
		valorVeiculo = 5.00;
	}

	@Override
	public double calcularTarifa(double distancia) {
	    return valorVeiculo + valorKm * distancia;
	}

	@Override
	public int getMinimoLugares() {
		return 1;
	}

	@Override
	public int getMaximoLugares() {
		return 4;
	}
}
