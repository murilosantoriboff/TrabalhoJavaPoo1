package app;

//Dado digitado em formato invalido (CPF, placa, distancia...)
public class DadoInvalidoException extends TransporteException {

	private static final long serialVersionUID = 1L;

	public DadoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
