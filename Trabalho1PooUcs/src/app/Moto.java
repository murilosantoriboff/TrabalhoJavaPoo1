package app;

public class Moto extends Veiculo {

	public Moto(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		super(placa, modelo, marca, ano, qtdPassageiros);
		valorKm();
		valorVeiculo();
	}
	
	public Moto () {
		
	}


	private void valorKm() {
		valorKm = 1.50;
	}

	private void valorVeiculo() {
		valorVeiculo = 3.00;
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
		return 1;
	}
}
