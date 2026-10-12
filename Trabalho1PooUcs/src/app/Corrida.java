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

	public Corrida() {
		
	}
	
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

	public void aceitar(Motorista m) {
	    if(this.status != StatusCorrida.SOLICITADA) {
	        System.out.println("So e possivel aceitar uma corrida solicitada!");
	        return;
	    }
	    if(m.getDisponibilidade() == false) {
	        System.out.println("O motorista " + m.getNome() + " nao esta disponivel!");
	        return;
	    }
	    if(categoria.aceitaVeiculo(m.getVeiculo()) == false) {
	        System.out.println("O veiculo do motorista não atende a categoria " + categoria.getNome() + "!");
	        return;
	    }

	    m.ocupar();
	    this.motorista = m;
	    this.status = StatusCorrida.ACEITA;
	}

	public void iniciar() {
	    if(this.status != StatusCorrida.ACEITA) {
	        System.out.println("Nao e possivel iniciar uma corrida ainda nao aceita ou em andamento!");
	        return;
	    }
	    this.status = StatusCorrida.EM_ANDAMENTO;
	}

	public void finalizar() {
	    if(this.status != StatusCorrida.EM_ANDAMENTO) {
	        System.out.println("Nao e possivel finalizar uma corrida nao iniciada!");
	        return;
	    }
	    calcularValor();
	    motorista.desocupar();
	    this.status = StatusCorrida.FINALIZADA;
	}

	public void cancelar() {
	    if(this.status != StatusCorrida.SOLICITADA && this.status != StatusCorrida.ACEITA) {
	        System.out.println("Sa e possivel cancelar uma corrida solicitada ou aceita!");
	        return;
	    }
	    if(motorista != null) {
	        motorista.desocupar();
	    }
	    this.status = StatusCorrida.CANCELADA;
	}

	public void avaliar(int nota, String comentario) {
	    if(this.status != StatusCorrida.FINALIZADA) {
	        System.out.println("Sa e possivel avaliar uma corrida finalizada!");
	        return;
	    }
	    if(this.avaliacao != null) {
	        System.out.println("Essa corrida já foi avaliada!");
	        return;
	    }
	    if(nota < 1 || nota > 5) {
	        System.out.println("A nota deve ser de 1 a 5!");
	        return;
	    }
	    this.avaliacao = new Avaliacao(nota, comentario);
	    motorista.adicionarAvaliacao(nota, comentario);
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

		return "Corrida: " + id + " | Situacao: " + status
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

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public void setCategoria(CategoriaCorrida categoria) {
		this.categoria = categoria;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
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
	

	public void setStatus(StatusCorrida status) {
		this.status = status;
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
