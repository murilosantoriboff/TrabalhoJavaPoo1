package app;

//Nota fora de 1 a 5 ou corrida ja avaliada
public class AvaliacaoInvalidaException extends TransporteException {

	private static final long serialVersionUID = 1L;

	public AvaliacaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
