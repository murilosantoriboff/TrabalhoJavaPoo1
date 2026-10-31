package app;

public class Van extends Veiculo {

	public Van(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		super(placa, modelo, marca, ano, qtdPassageiros);
		valorKm();
		valorVeiculo();
	}
	
	public Van() {
		
	}

	private void valorKm() {
		valorKm = 3.00;
	}

	private void valorVeiculo() {
		valorVeiculo = 8.00;
	}

	@Override
	public double calcularTarifa(double distancia) {
		return valorVeiculo + valorKm * distancia;
	}

	@Override
	public int getMinimoLugares() {
		return 6;
	}

	@Override
	public int getMaximoLugares() {
		return 19;
	}

}
