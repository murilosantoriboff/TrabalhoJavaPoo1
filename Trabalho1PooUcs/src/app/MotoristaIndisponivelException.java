package app;


public class MotoristaIndisponivelException extends TransporteException {

	private static final long serialVersionUID = 1L;

	public MotoristaIndisponivelException(String mensagem) {
		super(mensagem);
	}
}
