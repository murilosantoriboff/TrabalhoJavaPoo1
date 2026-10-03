package app;

public class PagCartao implements FormaPagamento{

	private static final double ACRESCIMO = 0.03;

	@Override
	public double aplicar(double valor) {
		return valor * (1 + ACRESCIMO);
	}

	@Override
	public String getNome() {
		return "Cartão";
	}
}
