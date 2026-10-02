package app;

public abstract class Veiculo {

	private String placa;
	private String modelo;
	private String marca;
	private int ano;
	private int qtdPassageiros;
	protected double valorKm;
	protected double valorVeiculo;

	public Veiculo() {

	}

	public Veiculo(String placa, String modelo, String marca, int ano, int qtdPassageiros) {
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.qtdPassageiros = qtdPassageiros;
	}

	public abstract double calcularTarifa(double distancia);

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getQtdPassageiros() {
		return qtdPassageiros;
	}

	public void setQtdPassageiros(int qtdPassageiros) {
		this.qtdPassageiros = qtdPassageiros;
	}

	public double getValorKm() {
		return valorKm;
	}

	public void setValorKm(double valorKm) {
		this.valorKm = valorKm;
	}

	public double getValorVeiculo() {
		return valorVeiculo;
	}

	public void setValorVeiculo(double valorVeiculo) {
		this.valorVeiculo = valorVeiculo;
	}

	@Override
	public String toString() {
		return "Marca: " + this.marca + ", Modelo: " + this.modelo + ", Placa: " + this.placa;
	}
}
