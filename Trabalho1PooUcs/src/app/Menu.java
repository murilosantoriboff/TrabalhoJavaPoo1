package app;

import java.util.List;
import java.util.Scanner;

public class Menu {

	private Scanner sc = new Scanner(System.in);
	private EmpresaTransporte empresa = new EmpresaTransporte();

	public Menu() {

	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.iniciar();
		System.exit(0);
	}

	public void iniciar() {
		int opcao;

		do {
			System.out.println();
			System.out.println("=========================================");
			System.out.println(" SISTEMA DE TRANSPORTE");
			System.out.println("=========================================");
			System.out.println("1 - CADASTROS");
			System.out.println("2 - CORRIDAS");
			System.out.println("3 - CONSULTAS");
			System.out.println("4 - ABRIR TELA DE CORRIDAS");
			System.out.println("5 - ABRIR TELA DE AVALIACAO");
			System.out.println("0 - Sair");
			System.out.println("=========================================");
			opcao = lerInteiro("Opcao: ", "Opcao");

			switch (opcao) {
			case 1:
				menuCadastros();
				break;
			case 2:
				menuCorridas();
				break;
			case 3:
				menuConsultas();
				break;
			case 4:
				System.out.println("Tela de corridas aberta. Feche a janela para voltar ao console.");
				new TelaCorrida(empresa).setVisible(true);
				break;
			case 5:
				System.out.println("Tela de avaliacao aberta. Feche a janela para voltar ao console.");
				new TelaAvaliacao(empresa).setVisible(true);
				break;
			case 0:
				System.out.println("Sistema encerrado.");
				break;
			default:
				System.out.println("Opcao invalida!");
			}
		} while (opcao != 0);
	}


	private void menuCadastros() {
		int opcao;

		do {
			System.out.println();
			System.out.println("=========================================");
			System.out.println(" SISTEMA DE TRANSPORTE - Cadastros");
			System.out.println("=========================================");
			System.out.println("1 - Cadastrar passageiro");
			System.out.println("2 - Cadastrar motorista");
			System.out.println("3 - Cadastrar veiculo");
			System.out.println("0 - Voltar");
			System.out.println("=========================================");
			opcao = lerInteiro("Opcao: ", "Opcao");

			try {
				switch (opcao) {
				case 1:
					cadastrarPassageiro();
					break;
				case 2:
					cadastrarMotorista();
					break;
				case 3:
					cadastrarVeiculo();
					break;
				case 0:
					break;
				default:
					System.out.println("Opcao invalida!");
				}
			} catch (TransporteException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} while (opcao != 0);
	}

	private void cadastrarPassageiro() throws TransporteException {
		String nome = lerNome("Digite o nome do passageiro: ");
		String cpf = lerCpfPassageiro();
		String telefone = lerTelefone("Digite o telefone do passageiro (DDD + numero): ");

		empresa.cadastrarPassageiro(new Passageiro(nome, cpf, telefone));
		System.out.println("Passageiro cadastrado!");
	}

	private void cadastrarMotorista() throws TransporteException {
		System.out.println("Pesquise o veiculo do motorista por parte da placa, marca ou modelo (Enter lista todos): ");
		Veiculo veiculo = escolher("Veiculos sem motorista", empresa.buscarVeiculosLivres(sc.nextLine()));
		if (veiculo == null) {
			return;
		}

		String nome = lerNome("Digite o nome do motorista: ");
		String cpf = lerCpfMotorista();
		String telefone = lerTelefone("Digite o telefone do motorista (DDD + numero): ");
		String cnh = lerCnh();

		empresa.cadastrarMotorista(new Motorista(nome, cpf, telefone, cnh, veiculo));
		System.out.println("Motorista cadastrado e veiculo associado!");
	}

	private void cadastrarVeiculo() throws TransporteException {
		String tipo = escolher("Tipo do veiculo", List.of("Carro", "Moto", "Van"));
		if (tipo == null) {
			return;
		}

		String placa = lerPlaca();
		String modelo = lerObrigatorio("Digite o modelo do veiculo (Civic, Astra, etc..): ", "Modelo");
		String marca = lerObrigatorio("Digite a marca do veiculo (Honda, Chevrolet, etc..): ", "Marca");
		int ano = lerAno();

		while (true) {
			int lugares = lerInteiro("Digite a quantidade de passageiros (sem contar o motorista): ", "Quantidade");
			Veiculo veiculo;
			if (tipo.equals("Carro")) {
				veiculo = new Carro(placa, modelo, marca, ano, lugares);
			} else if (tipo.equals("Moto")) {
				veiculo = new Moto(placa, modelo, marca, ano, lugares);
			} else {
				veiculo = new Van(placa, modelo, marca, ano, lugares);
			}

			try {
				Validacao.validarLugares(lugares, veiculo.getMinimoLugares(), veiculo.getMaximoLugares());
				empresa.cadastrarVeiculo(veiculo);
				System.out.println("Veiculo cadastrado!");
				return;
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}


	private void menuCorridas() {
		int opcao;

		do {
			System.out.println();
			System.out.println("=========================================");
			System.out.println(" SISTEMA DE TRANSPORTE - Corridas");
			System.out.println("=========================================");
			System.out.println("1 - Solicitar corrida");
			System.out.println("2 - Aceitar corrida");
			System.out.println("3 - Iniciar corrida");
			System.out.println("4 - Finalizar corrida");
			System.out.println("5 - Cancelar corrida");
			System.out.println("6 - Avaliar motorista");
			System.out.println("0 - Voltar");
			System.out.println("=========================================");
			opcao = lerInteiro("Opcao: ", "Opcao");

			try {
				switch (opcao) {
				case 1:
					solicitarCorrida();
					break;
				case 2:
					aceitarCorrida();
					break;
				case 3:
					iniciarCorrida();
					break;
				case 4:
					finalizarCorrida();
					break;
				case 5:
					cancelarCorrida();
					break;
				case 6:
					avaliarMotorista();
					break;
				case 0:
					break;
				default:
					System.out.println("Opcao invalida!");
				}
			} catch (TransporteException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} while (opcao != 0);
	}

	private void solicitarCorrida() throws TransporteException {
		Passageiro p = selecionarPassageiro();
		if (p == null) {
			return;
		}

		String origem = lerObrigatorio("Digite o endereco de origem: ", "Origem");
		String destino = lerObrigatorio("Digite o endereco de destino: ", "Destino");
		double distancia = lerDistancia();

		CategoriaCorrida cat = escolher("Categoria da corrida", List.of(new CatEconomica(), new CatConforto(), new CatPremium()));
		if (cat == null) {
			return;
		}
		FormaPagamento fp = escolher("Forma de pagamento", List.of(new PagPix(), new PagCartao(), new PagDinheiro()));
		if (fp == null) {
			return;
		}

		Corrida nova = empresa.solicitarCorrida(p, origem, destino, distancia, cat, fp);
		System.out.println("Corrida " + nova.getId() + " solicitada!");
	}

	private void aceitarCorrida() throws TransporteException {
		Corrida c = selecionarCorrida(List.of(StatusCorrida.SOLICITADA));
		if (c == null) {
			return;
		}

		System.out.println("Pesquise o motorista por parte do nome (Enter lista todos): ");
		Motorista m = escolher("Motoristas disponiveis", empresa.buscarMotoristasDisponiveis(sc.nextLine()));
		if (m == null) {
			return;
		}

		c.aceitar(m);
		System.out.println("Corrida " + c.getId() + " aceita pelo motorista " + m.getNome() + "!");
	}

	private void iniciarCorrida() throws TransporteException {
		Corrida c = selecionarCorrida(List.of(StatusCorrida.ACEITA));
		if (c == null) {
			return;
		}

		c.iniciar();
		System.out.println("Corrida " + c.getId() + " iniciada!");
	}

	private void finalizarCorrida() throws TransporteException {
		Corrida c = selecionarCorrida(List.of(StatusCorrida.EM_ANDAMENTO));
		if (c == null) {
			return;
		}

		c.finalizar();
		System.out.println("Corrida " + c.getId() + " finalizada!");
		System.out.println(String.format("Valor da corrida: R$ %.2f", c.getValorBase()));
		System.out.println("Valor final: " + c.getValorFormatado());
	}

	private void cancelarCorrida() throws TransporteException {
		Corrida c = selecionarCorrida(List.of(StatusCorrida.SOLICITADA, StatusCorrida.ACEITA));
		if (c == null) {
			return;
		}

		c.cancelar();
		System.out.println("Corrida " + c.getId() + " cancelada!");
	}

	private void avaliarMotorista() throws TransporteException {
		Corrida c = selecionarCorrida(List.of(StatusCorrida.FINALIZADA));
		if (c == null) {
			return;
		}

		int nota = lerNota();
		System.out.println("Digite um comentario (opcional, Enter para pular): ");
		String comentario = sc.nextLine().trim();

		c.avaliar(nota, comentario);
		System.out.println("Avaliacao registrada!");
	}

	private void menuConsultas() {
		int opcao;

		do {
			System.out.println();
			System.out.println("=========================================");
			System.out.println(" SISTEMA DE TRANSPORTE - Consultas");
			System.out.println("=========================================");
			System.out.println("1 - Listar corridas");
			System.out.println("2 - Listar passageiros");
			System.out.println("3 - Listar motoristas");
			System.out.println("4 - Listar veiculos");
			System.out.println("5 - Consultar corrida");
			System.out.println("6 - Consultar passageiro");
			System.out.println("7 - Consultar motorista");
			System.out.println("8 - Consultar veiculo");
			System.out.println("0 - Voltar");
			System.out.println("=========================================");
			opcao = lerInteiro("Opcao: ", "Opcao");

			switch (opcao) {
			case 1:
				imprimirLista("Corridas", empresa.buscarCorridas(""));
				break;
			case 2:
				imprimirLista("Passageiros", empresa.buscarPassageiros(""));
				break;
			case 3:
				imprimirLista("Motoristas", empresa.buscarMotoristas(""));
				break;
			case 4:
				imprimirLista("Veiculos", empresa.buscarVeiculos(""));
				break;
			case 5:
				consultarCorrida();
				break;
			case 6:
				consultarPassageiro();
				break;
			case 7:
				consultarMotorista();
				break;
			case 8:
				consultarVeiculo();
				break;
			case 0:
				break;
			default:
				System.out.println("Opcao invalida!");
			}
		} while (opcao != 0);
	}

	private void consultarCorrida() {
		System.out.println("Pesquise a corrida por parte do nome do passageiro (Enter lista todas): ");
		Corrida c = escolher("Corridas encontradas", empresa.buscarCorridas(sc.nextLine()));
		if (c != null) {
			System.out.println(c);
			if (c.getAvaliacao() != null) {
				System.out.println("   Avaliacao: " + c.getAvaliacao());
			}
		}
	}

	private void consultarPassageiro() {
		Passageiro p = selecionarPassageiro();
		if (p != null) {
			System.out.println(p);
			imprimirLista("Corridas de " + p.getNome(), p.getCorridas());
		}
	}

	private void consultarMotorista() {
		System.out.println("Pesquise o motorista por parte do nome (Enter lista todos): ");
		Motorista m = escolher("Motoristas encontrados", empresa.buscarMotoristas(sc.nextLine()));
		if (m != null) {
			System.out.println(m);
			System.out.println("Veiculo: " + m.getVeiculo());
			System.out.println(String.format("Media das avaliacoes: %.1f", m.calcularMediaAvaliacoes()));
			imprimirLista("Avaliacoes de " + m.getNome(), m.getAvaliacoes());
		}
	}

	private void consultarVeiculo() {
		System.out.println("Pesquise o veiculo por parte da placa, marca ou modelo (Enter lista todos): ");
		Veiculo v = escolher("Veiculos encontrados", empresa.buscarVeiculos(sc.nextLine()));
		if (v != null) {
			System.out.println(v);
		}
	}

	private Passageiro selecionarPassageiro() {
		System.out.println("Pesquise o passageiro por parte do nome (Enter lista todos): ");
		return escolher("Passageiros encontrados", empresa.buscarPassageiros(sc.nextLine()));
	}

	private Corrida selecionarCorrida(List<StatusCorrida> situacoes) {
		System.out.println("Pesquise a corrida por parte do nome do passageiro (Enter lista todas): ");
		return escolher("Corridas encontradas", empresa.buscarCorridas(sc.nextLine(), situacoes));
	}

	private <T> T escolher(String titulo, List<T> itens) {
		if (itens.isEmpty()) {
			System.out.println("Nada encontrado.");
			return null;
		}

		System.out.println("--- " + titulo + " ---");
		for (int i = 0; i < itens.size(); i++) {
			System.out.println((i + 1) + " - " + itens.get(i));
		}
		System.out.println("0 - Cancelar");

		while (true) {
			int opcao = lerInteiro("Escolha um numero da lista: ", "Opcao");
			if (opcao == 0) {
				return null;
			}
			if (opcao >= 1 && opcao <= itens.size()) {
				return itens.get(opcao - 1);
			}
			System.out.println("Digite um numero que esta na lista!");
		}
	}

	private <T> void imprimirLista(String titulo, List<T> itens) {
		System.out.println("--- " + titulo + " ---");
		if (itens.isEmpty()) {
			System.out.println("Nada encontrado.");
		}
		for (T item : itens) {
			System.out.println(item);
		}
	}


	private int lerInteiro(String mensagem, String campo) {
		while (true) {
			System.out.println(mensagem);
			try {
				return Validacao.converterInteiro(sc.nextLine(), campo);
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String lerObrigatorio(String mensagem, String campo) {
		while (true) {
			System.out.println(mensagem);
			String digitado = sc.nextLine().trim();
			try {
				Validacao.validarObrigatorio(digitado, campo);
				return digitado;
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String lerNome(String mensagem) {
		while (true) {
			System.out.println(mensagem);
			String digitado = sc.nextLine().trim();
			try {
				Validacao.validarNome(digitado);
				return digitado;
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String lerCpfPassageiro() {
		while (true) {
			System.out.println("Digite o CPF do passageiro (apenas numeros): ");
			String digitado = sc.nextLine().trim();
			try {
				Validacao.validarCpf(digitado);
				empresa.verificarCpfPassageiroLivre(digitado);
				return digitado;
			} catch (TransporteException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String lerCpfMotorista() {
		while (true) {
			System.out.println("Digite o CPF do motorista (apenas numeros): ");
			String digitado = sc.nextLine().trim();
			try {
				Validacao.validarCpf(digitado);
				empresa.verificarCpfMotoristaLivre(digitado);
				return digitado;
			} catch (TransporteException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String lerTelefone(String mensagem) {
		while (true) {
			System.out.println(mensagem);
			String digitado = sc.nextLine().trim();
			try {
				Validacao.validarTelefone(digitado);
				return digitado;
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String lerCnh() {
		while (true) {
			System.out.println("Digite a CNH do motorista (11 numeros): ");
			String digitado = sc.nextLine().trim();
			try {
				Validacao.validarCnh(digitado);
				return digitado;
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String lerPlaca() {
		while (true) {
			System.out.println("Digite a placa do veiculo: ");
			String digitado = Validacao.normalizarPlaca(sc.nextLine());
			try {
				Validacao.validarPlaca(digitado);
				empresa.verificarPlacaLivre(digitado);
				return digitado;
			} catch (TransporteException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private int lerAno() {
		while (true) {
			int ano = lerInteiro("Digite o ano do veiculo: ", "Ano");
			try {
				Validacao.validarAno(ano);
				return ano;
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private double lerDistancia() {
		while (true) {
			System.out.println("Digite a distancia estimada em km: ");
			try {
				return Validacao.converterDistancia(sc.nextLine());
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private int lerNota() {
		while (true) {
			int nota = lerInteiro("Digite a nota (1 a 5): ", "Nota");
			try {
				Validacao.validarNota(nota);
				return nota;
			} catch (DadoInvalidoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
