package app;

import java.time.Year;

//O Menu e as telas usam as mesmas regras.
public class Validacao {

	public static void validarObrigatorio(String texto, String campo) throws DadoInvalidoException {
		if (texto == null || texto.trim().isEmpty()) {
			throw new DadoInvalidoException("Preencha o campo " + campo + "!");
		}
	}

	public static void validarNome(String nome) throws DadoInvalidoException {
		// \p{L} aceita qualquer letra, inclusive com acento
		if (nome == null || !nome.trim().matches("\\p{L}[\\p{L} .'-]*")) {
			throw new DadoInvalidoException("Nome invalido! Use apenas letras.");
		}
	}

	public static void validarCpf(String cpf) throws DadoInvalidoException {
		if (cpf == null || !cpf.trim().matches("\\d{11}")) {
			throw new DadoInvalidoException("CPF invalido! Digite exatamente 11 numeros.");
		}
	}

	public static void validarTelefone(String telefone) throws DadoInvalidoException {
		if (telefone == null || !telefone.trim().matches("\\d{10,11}")) {
			throw new DadoInvalidoException("Telefone invalido! Digite 10 ou 11 numeros (DDD + numero).");
		}
	}

	public static void validarCnh(String cnh) throws DadoInvalidoException {
		if (cnh == null || !cnh.trim().matches("\\d{11}")) {
			throw new DadoInvalidoException("CNH invalida! Digite exatamente 11 numeros.");
		}
	}

	public static void validarPlaca(String placa) throws DadoInvalidoException {
		if (placa == null || !normalizarPlaca(placa).matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
			throw new DadoInvalidoException("Placa invalida! Use o formato ABC1234 ou ABC1D23.");
		}
	}

	public static String normalizarPlaca(String placa) {
		return placa.trim().toUpperCase().replace("-", "").replace(" ", "");
	}

	public static void validarAno(int ano) throws DadoInvalidoException {
		int anoMaximo = Year.now().getValue() + 1;
		if (ano < 1950 || ano > anoMaximo) {
			throw new DadoInvalidoException("Ano invalido! Digite um ano entre 1950 e " + anoMaximo + ".");
		}
	}

	public static void validarLugares(int quantidade, int minimo, int maximo) throws DadoInvalidoException {
		if (quantidade < minimo || quantidade > maximo) {
			throw new DadoInvalidoException("Quantidade invalida! Para este veiculo, digite de " + minimo + " a " + maximo + ".");
		}
	}

	public static void validarNota(int nota) throws DadoInvalidoException {
		if (nota < 1 || nota > 5) {
			throw new DadoInvalidoException("A nota deve ser de 1 a 5!");
		}
	}

	public static void validarDistancia(double distancia) throws DadoInvalidoException {
		if (Double.isNaN(distancia) || Double.isInfinite(distancia) || distancia <= 0) {
			throw new DadoInvalidoException("A distancia deve ser maior que zero!");
		}
	}

	public static int converterInteiro(String texto, String campo) throws DadoInvalidoException {
		try {
			return Integer.parseInt(texto.trim());
		} catch (NumberFormatException e) {
			throw new DadoInvalidoException(campo + ": digite apenas numeros inteiros!");
		}
	}

	public static double converterDistancia(String texto) throws DadoInvalidoException {
		double distancia;
		try {
			distancia = Double.parseDouble(texto.trim().replace(",", "."));
		} catch (NumberFormatException e) {
			throw new DadoInvalidoException("Distancia invalida! Digite apenas numeros. Exemplo: 12,5");
		}
		validarDistancia(distancia);
		return distancia;
	}
}
