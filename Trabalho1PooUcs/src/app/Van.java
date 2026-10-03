package app;

public class Van extends Veiculo {

	public Van() {

	}

	public Van(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		super(placa, modelo, marca, ano, qtdPassageiros);
		valorKm();
		valorVeiculo();
	}

	public void valorKm() {
		valorKm = 3.00;
	}

	public void valorVeiculo() {
		valorVeiculo = 8.00;
	}

	@Override
	public double calcularTarifa(double distancia) {
		return valorVeiculo + valorKm * distancia;
	}

}
