package app;

//O veiculo do motorista nao atende a categoria da corrida
public class VeiculoIncompativelException extends TransporteException {

	private static final long serialVersionUID = 1L;

	public VeiculoIncompativelException(String mensagem) {
		super(mensagem);
	}
}
