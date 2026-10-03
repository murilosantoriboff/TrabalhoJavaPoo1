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
	private Avaliacao avaliacao;

	public Corrida(Passageiro passageiro, String origem, String destino, double distancia,
			CategoriaCorrida categoria, FormaPagamento formaPagamento) {
		contador++;
		this.id = contador;
		this.passageiro = passageiro;
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
		this.categoria = categoria;
		this.formaPagamento = formaPagamento;
		this.status = StatusCorrida.SOLICITADA;
	}

	public boolean aceitar(Motorista m) {
		if(this.status != StatusCorrida.SOLICITADA) {
			System.out.println("Só é possivel aceitar uma corrida solicitada!");
			return false;
		}
		if(m.getDisponibilidade() == false) {
			System.out.println("O motorista " + m.getNome() + " não está disponivel!");
			return false;
		}
		if(categoria.aceitaVeiculo(m.getVeiculo()) == false) {
			System.out.println("O veiculo do motorista não atende a categoria " + categoria.getNome() + "!");
			return false;
		}

		m.ocupar();
		this.motorista = m;
		this.status = StatusCorrida.ACEITA;
		return true;
	}

	public boolean iniciar() {
		if(this.status != StatusCorrida.ACEITA) {
			System.out.println("Não é possivel iniciar uma corrida ainda não aceita ou em andamento!");
			return false;
		}

		this.status = StatusCorrida.EM_ANDAMENTO;
		return true;
	}

	public boolean finalizar() {
		if(this.status != StatusCorrida.EM_ANDAMENTO) {
			System.out.println("Não é possivel finalizar uma corrida não iniciada!");
			return false;
		}

		calcularValor();
		motorista.desocupar();
		this.status = StatusCorrida.FINALIZADA;
		return true;
	}

	public boolean cancelar() {
		if(this.status != StatusCorrida.SOLICITADA && this.status != StatusCorrida.ACEITA) {
			System.out.println("Só é possivel cancelar uma corrida solicitada ou aceita!");
			return false;
		}

		if(motorista != null) {
			motorista.desocupar();
		}
		this.status = StatusCorrida.CANCELADA;
		return true;
	}

	public boolean avaliar(int nota, String comentario) {
		if(this.status != StatusCorrida.FINALIZADA) {
			System.out.println("Só é possivel avaliar uma corrida finalizada!");
			return false;
		}
		if(this.avaliacao != null) {
			System.out.println("Essa corrida já foi avaliada!");
			return false;
		}
		if(nota < 1 || nota > 5) {
			System.out.println("A nota deve ser de 1 a 5!");
			return false;
		}

		this.avaliacao = new Avaliacao(nota, comentario);
		motorista.adicionarAvaliacao(nota, comentario);
		return true;
	}

	private void calcularValor() {
		this.valorBase = motorista.getVeiculo().calcularTarifa(distancia);
		double valorComCategoria = categoria.aplicarAcrescimo(valorBase);
		this.valorFinal = formaPagamento.aplicar(valorComCategoria);
	}

	@Override
	public String toString() {
		String nomeMotorista = "-";
		String veiculo = "-";
		String valor = "a calcular";

		if(motorista != null) {
			nomeMotorista = motorista.getNome();
			veiculo = motorista.getVeiculo().toString();
		}
		if(status == StatusCorrida.FINALIZADA) {
			valor = String.format("R$ %.2f", valorFinal);
		}

		return "Corrida: " + id + " | Situação: " + status
				+ "\n   Passageiro: " + passageiro.getNome() + " | Motorista: " + nomeMotorista
				+ "\n   Veiculo: " + veiculo
				+ "\n   Origem: " + origem + " | Destino: " + destino + " | Distancia: " + distancia + " km"
				+ "\n   Categoria: " + categoria.getNome() + " | Pagamento: " + formaPagamento.getNome()
				+ " | Valor: " + valor;
	}

	//Getters
	public int getId() {
		return id;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public CategoriaCorrida getCategoria() {
		return categoria;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public StatusCorrida getStatus() {
		return status;
	}

	public double getValorBase() {
		return valorBase;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

}
