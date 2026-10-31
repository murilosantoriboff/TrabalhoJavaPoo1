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

	public void aceitar(Motorista m) throws TransicaoInvalidaException, MotoristaIndisponivelException, VeiculoIncompativelException {
	    if(this.status != StatusCorrida.SOLICITADA) {
	        throw new TransicaoInvalidaException("So e possivel aceitar uma corrida solicitada!");
	    }
	    if(m == null) {
	        throw new MotoristaIndisponivelException("Selecione um motorista!");
	    }
	    if(m.getDisponibilidade() == false) {
	        throw new MotoristaIndisponivelException("O motorista " + m.getNome() + " nao esta disponivel!");
	    }
	    if(categoria.aceitaVeiculo(m.getVeiculo()) == false) {
	        throw new VeiculoIncompativelException("O veiculo do motorista nao atende a categoria " + categoria.getNome() + "!");
	    }

	    m.ocupar();
	    this.motorista = m;
	    this.status = StatusCorrida.ACEITA;
	}

	public void iniciar() throws TransicaoInvalidaException {
	    if(this.status != StatusCorrida.ACEITA) {
	        throw new TransicaoInvalidaException("Nao e possivel iniciar uma corrida ainda nao aceita ou em andamento!");
	    }
	    this.status = StatusCorrida.EM_ANDAMENTO;
	}

	public void finalizar() throws TransicaoInvalidaException {
	    if(this.status != StatusCorrida.EM_ANDAMENTO) {
	        throw new TransicaoInvalidaException("Nao e possivel finalizar uma corrida nao iniciada!");
	    }
	    calcularValor();
	    motorista.desocupar();
	    this.status = StatusCorrida.FINALIZADA;
	}

	public void cancelar() throws TransicaoInvalidaException {
	    if(this.status != StatusCorrida.SOLICITADA && this.status != StatusCorrida.ACEITA) {
	        throw new TransicaoInvalidaException("So e possivel cancelar uma corrida solicitada ou aceita!");
	    }
	    if(motorista != null) {
	        motorista.desocupar();
	    }
	    this.status = StatusCorrida.CANCELADA;
	}

	public void avaliar(int nota, String comentario) throws TransicaoInvalidaException, AvaliacaoInvalidaException {
	    if(this.status != StatusCorrida.FINALIZADA) {
	        throw new TransicaoInvalidaException("So e possivel avaliar uma corrida finalizada!");
	    }
	    if(this.avaliacao != null) {
	        throw new AvaliacaoInvalidaException("Essa corrida ja foi avaliada!");
	    }
	    if(nota < 1 || nota > 5) {
	        throw new AvaliacaoInvalidaException("A nota deve ser de 1 a 5!");
	    }
	    this.avaliacao = new Avaliacao(nota, comentario);
	    motorista.adicionarAvaliacao(nota, comentario);
	}

	private void calcularValor() {
		this.valorBase = motorista.getVeiculo().calcularTarifa(distancia);
		double valorComCategoria = categoria.aplicarAcrescimo(valorBase);
		this.valorFinal = formaPagamento.aplicar(valorComCategoria);
	}

	public String getValorFormatado() {
		if(status == StatusCorrida.FINALIZADA) {
			return String.format("R$ %.2f", valorFinal);
		}
		return "a calcular";
	}

	@Override
	public String toString() {
		String nomeMotorista = "-";
		String veiculo = "-";

		if(motorista != null) {
			nomeMotorista = motorista.getNome();
			veiculo = motorista.getVeiculo().toString();
		}

		return "Corrida: " + id + " | Situacao: " + status
				+ "\n   Passageiro: " + passageiro.getNome() + " | Motorista: " + nomeMotorista
				+ "\n   Veiculo: " + veiculo
				+ "\n   Origem: " + origem + " | Destino: " + destino + " | Distancia: " + distancia + " km"
				+ "\n   Categoria: " + categoria.getNome() + " | Pagamento: " + formaPagamento.getNome()
				+ " | Valor: " + getValorFormatado();
	}

	
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
