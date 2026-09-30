package app;

public class Corrida {

	private int id;
	private static int contador;
	private Passageiro passageiro;
	private Motorista motorista;
	private String origem;
	private String destino;
	private double distancia;
	private CategoriaCorrida categoria;
	private FormaPagamento formaPagamento;
	private StatusCorrida status;
	private double valorBase;
	private double valorFinal;
	private Avaliacao avalicao;

	//Construtor
	public Corrida() {

	}

	//Métodos
	public void aceitar(Motorista m) {
		
	}
	
	public void iniciar() {
		
	}
	
	public void finalizar() {
		
	}
	
	public void cancelar() {
		
	}
	
	public void avaliar(int nota, String comentario) {
		
	}
	
	//Getters / setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Corrida.contador = contador;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public CategoriaCorrida getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaCorrida categoria) {
		this.categoria = categoria;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public StatusCorrida getStatus() {
		return status;
	}

	public void setStatus(StatusCorrida status) {
		this.status = status;
	}

	public double getValorBase() {
		return valorBase;
	}

	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Avaliacao getAvalicao() {
		return avalicao;
	}

	public void setAvalicao(Avaliacao avalicao) {
		this.avalicao = avalicao;
	}

}
