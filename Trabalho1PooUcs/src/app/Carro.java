package app;

public class Carro extends Veiculo {

	//Construtores
	public Carro() {

	}

	public Carro(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		super(placa, modelo, marca, ano, qtdPassageiros);
		valorKm();
		valorVeiculo();
	}

	//Metodos
	public void valorKm() {
		valorKm = 2.00;
	}

	public void valorVeiculo() {
		valorVeiculo = 5.00;
	}

	@Override
	public double calcularTarifa(double distancia) {
		return 0;
	}
}
