package app;

public class CatConforto extends CategoriaCorrida {

    private static final double ACRESCIMO = 0.20;
    private static final int LUGARES_MINIMOS = 4;

    public CatConforto() {
        super("Conforto");
    }

    @Override
    public double aplicarAcrescimo(double valor) {
        return valor * (1 + ACRESCIMO);
    }

    @Override
    public boolean aceitaVeiculo(Veiculo v) {
        return v.getQtdPassageiros() >= LUGARES_MINIMOS;
    }
}
