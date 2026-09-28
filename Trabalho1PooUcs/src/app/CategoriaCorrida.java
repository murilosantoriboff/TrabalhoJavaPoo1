package app;


public abstract class CategoriaCorrida {

    private String nome;

    protected CategoriaCorrida(String nome) {
        this.nome = nome;
    }

    public abstract double aplicarAcrescimo(double valor);

    public abstract boolean aceitaVeiculo(Veiculo v);

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}