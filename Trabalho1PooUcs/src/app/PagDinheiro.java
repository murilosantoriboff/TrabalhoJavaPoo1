package app;

public class PagDinheiro implements FormaPagamento{

	@Override
	public double aplicar(double valor) {
		return valor;
	}

	@Override
	public String getNome() {
		return "Dinheiro";
	}
}
