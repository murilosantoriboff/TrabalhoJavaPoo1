package app;

public class PagPix implements FormaPagamento{

	private static final double DESCONTO = 0.05;

	@Override
	public double aplicar(double valor) {
		return valor * (1 - DESCONTO);
	}

	@Override
	public String getNome() {
		return "Pix";
	}
}
