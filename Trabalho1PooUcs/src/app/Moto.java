package app;

public class Moto extends Veiculo {

	public Moto() {
		
	}
	public Moto(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		super(placa, modelo, marca, ano, qtdPassageiros);
		valorKm();
		valorVeiculo();
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
}
