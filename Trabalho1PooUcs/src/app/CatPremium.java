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
        throw new UnsupportedOperationException("CatPremium.aplicarAcrescimo ainda não foi implementado.");
    }

    @Override
    public boolean aceitaVeiculo(Veiculo v) {
        throw new UnsupportedOperationException("CatPremium.aceitaVeiculo ainda não foi implementado.");
    }
}
