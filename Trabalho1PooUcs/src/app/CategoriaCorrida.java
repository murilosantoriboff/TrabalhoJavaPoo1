package app;

import java.io.Serializable;

public abstract class CategoriaCorrida implements Serializable{

    private static final long serialVersionUID = 1L;
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