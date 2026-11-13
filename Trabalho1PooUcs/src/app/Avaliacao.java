package app;

import java.io.Serializable;

public class Avaliacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private int nota;
	private String comentario;

	public Avaliacao(int nota, String comentario) {

		this.nota = nota;
		this.comentario = comentario;
	}

	public int getNota() {
		return nota;
	}
	public String getComentario() {
		return comentario;
	}

	@Override
	public String toString() {
		if (comentario == null || comentario.trim().isEmpty()) {
			return "Nota: " + nota;
		}
		return "Nota: " + nota + " | Comentario: " + comentario;
	}

}
