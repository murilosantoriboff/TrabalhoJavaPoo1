package app;

import java.io.Serializable;

public class PagPix implements FormaPagamento, Serializable{

	private static final long serialVersionUID = 1L;
	private static final double DESCONTO = 0.05;

	@Override
	public double aplicar(double valor) {
		return valor * (1 - DESCONTO);
	}

	@Override
	public String getNome() {
		return "Pix";
	}

	@Override
	public String toString() {
		return getNome();
	}
}
