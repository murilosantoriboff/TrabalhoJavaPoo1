package app;

public class Moto extends Veiculo {

	public Moto() {

	}

	public Moto(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		super(placa, modelo, marca, ano, qtdPassageiros);
	}

	public void valorKm() {
		valorKm = 1.50;
	}

	public void valorVeiculo() {
		valorVeiculo = 3.00;
	}

	@Override
	public double calcularTarifa(double distancia) {

		return 0;
	}
}
