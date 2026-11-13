package app;

import java.io.Serializable;

public class PagCartao implements FormaPagamento, Serializable{

	private static final long serialVersionUID = 1L;
	private static final double ACRESCIMO = 0.03;

	@Override
	public double aplicar(double valor) {
		return valor * (1 + ACRESCIMO);
	}

	@Override
	public String getNome() {
		return "Cartão";
	}

	@Override
	public String toString() {
		return getNome();
	}
}
