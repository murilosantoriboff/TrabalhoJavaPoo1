package app;


public class CatPremium extends CategoriaCorrida {

    private static final double ACRESCIMO = 0.50;
    private static final int LUGARES_MINIMOS = 4;
    private static final int ANO_MINIMO = 2022;

    public CatPremium() {
        super("Premium");
    }

    @Override
    public double aplicarAcrescimo(double valor) {
        return valor * (1 + ACRESCIMO);
    }

    @Override
    public boolean aceitaVeiculo(Veiculo v) {
        return v.getQtdPassageiros() >= LUGARES_MINIMOS && v.getAno() >= ANO_MINIMO;
    }
}
