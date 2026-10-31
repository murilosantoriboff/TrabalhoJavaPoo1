package app;

public class TransicaoInvalidaException extends TransporteException {

	private static final long serialVersionUID = 1L;

	public TransicaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
