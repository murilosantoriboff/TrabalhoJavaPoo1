package app;

import java.io.Serializable;

public class PagDinheiro implements FormaPagamento, Serializable{

	private static final long serialVersionUID = 1L;
	@Override
	public double aplicar(double valor) {
		return valor;
	}

	@Override
	public String getNome() {
		return "Dinheiro";
	}

	@Override
	public String toString() {
		return getNome();
	}
}
