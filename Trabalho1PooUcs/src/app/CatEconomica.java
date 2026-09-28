package app;

public class CatEconomica extends CategoriaCorrida {

    public CatEconomica() {
        super("Econômica");
    }

    @Override
    public double aplicarAcrescimo(double valor) {
        return valor;
    }

    @Override
    public boolean aceitaVeiculo(Veiculo v) {
        return true;
    }
}
