package app;

//CPF ou placa ja cadastrados, ou veiculo que ja tem motorista
public class RegistroDuplicadoException extends TransporteException {

	private static final long serialVersionUID = 1L;

	public RegistroDuplicadoException(String mensagem) {
		super(mensagem);
	}
}
