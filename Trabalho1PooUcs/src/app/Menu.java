package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private Scanner sc = new Scanner(System.in);
	private EmpresaTransporte empresa = new EmpresaTransporte();
	private Corrida corrida = new Corrida();
	
	public Menu() {
		
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.iniciar();
	}
	
	public void iniciar() {
		int opcao;
		
		do {
			System.out.println("=========================================");
			System.out.println(" SISTEMA DE TRANSPORTE - Corridas");
			System.out.println("=========================================");
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Menu Cadastros");
			System.out.println("2 - Menu Corridas");
			System.out.println("3 - Menu Consultas");
			System.out.println("0 - Fechar menu");
			System.out.println("=========================================");
			try {
				opcao = Integer.parseInt(sc.nextLine().trim());
			} catch(NumberFormatException e) {
				System.out.println("Opcao invalida! Digite apenas o numero da opcao.");
				opcao = -1;
				continue;
			}
			
			
			switch(opcao) {
			case 1:
				menuCadastros();
				break;
			case 2:
				menuCorridas();
				break;
			case 3:
				menuConsultas();
				break;
			case 0:
				System.out.println("Sistema encerrado.");
				break;
			default:
				System.out.println("Opcao invalida!");
			}
	} while(opcao != 0);
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
			System.out.println("3 - Cadastrar veículo");
			System.out.println("0 - Voltar");
			System.out.println("=========================================");
			System.out.println("Opção: ");
			try {
				opcao = Integer.parseInt(sc.nextLine().trim());
			} catch(NumberFormatException e) {
				System.out.println("Opcao invalida! Digite apenas o numero da opcao.");
				opcao = -1;
				continue;
			}

			switch(opcao) {
			case 1:
				empresa.cadastrarPassageiro(sc);
				break;
			case 2:
				empresa.cadastrarMotorista(sc);
				break;
			case 3:
				empresa.cadastrarVeiculo(sc);
				break;
			case 0:
				break;
			default:
				System.out.println("Opcao invalida!");
			}
		} while(opcao != 0);
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
			System.out.println("Opção: ");
			try {
				opcao = Integer.parseInt(sc.nextLine().trim());
			} catch(NumberFormatException e) {
				System.out.println("Opcao invalida! Digite apenas o numero da opcao.");
				opcao = -1;
				continue;
			}

			switch(opcao) {
			case 1:
			    Passageiro p = buscaPassageiro(sc);

			    if (p == null) {
			        System.out.println("Passageiro não encontrado!");
			        break;
			    }

			    System.out.println("Digite a origem:");
			    String origem = sc.nextLine();

			    System.out.println("Digite o destino:");
			    String destino = sc.nextLine();

			    System.out.println("Digite a distância em km:");
			    double distancia;

			    //tenta ler a distância digitada pelo usuário e se protege caso ele digite algo que não seja um número
			    try {
			        distancia = Double.parseDouble(sc.nextLine());
			    } catch (NumberFormatException e) {
			        System.out.println("Distância inválida!");
			        break;
			    }

			    System.out.println("Escolha a categoria da corrida:");
			    System.out.println("1 - Econômica");
			    System.out.println("2 - Conforto");
			    System.out.println("3 - Premium");

			    CategoriaCorrida cat;

			    switch (sc.nextLine()) {
			        case "1":
			            cat = new CatEconomica();
			            break;
			        case "2":
			            cat = new CatConforto();
			            break;
			        case "3":
			            cat = new CatPremium();
			            break;
			        default:
			            System.out.println("Categoria inválida!");
			            break;
			    }

			    System.out.println("Escolha a forma de pagamento:");
			    System.out.println("1 - Dinheiro");
			    System.out.println("2 - Cartão");
			    System.out.println("3 - Pix");

			    FormaPagamento fp;

			    switch (sc.nextLine()) {
			        case "1":
			            fp = new PagDinheiro();
			            corrida.setStatus(StatusCorrida.SOLICITADA);
			            System.out.println("Corrida Solicitada com sucesso!");
			            break;
			        case "2":
			            fp = new PagCartao();
			            corrida.setStatus(StatusCorrida.SOLICITADA);
			            System.out.println("Corrida Solicitada com sucesso!");
			            break;
			        case "3":
			            fp = new PagPix();
			            corrida.setStatus(StatusCorrida.SOLICITADA);
			            System.out.println("Corrida Solicitada com sucesso!");
			            break;
			        default:
			            System.out.println("Forma de pagamento inválida!");
			            break;
			    }

			    break;
			case 2:
				corrida.aceitar(buscaMotorista(sc));
				break;
			case 3:
				corrida.iniciar();
				break;
			case 4:
				corrida.finalizar();
				break;
			case 5:
				corrida.cancelar();
				break;
			case 6:
				corrida.avaliar(0, null);
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while(opcao != 0);
	}
	
	private void menuConsultas() {
		int opcao = 0;

		do {
			System.out.println();
			System.out.println("=========================================");
			System.out.println(" SISTEMA DE TRANSPORTE - Consultas");
			System.out.println("=========================================");
			System.out.println("1 - Listar corridas");
			System.out.println("2 - Listar passageiros");
			System.out.println("3 - Listar motoristas");
			System.out.println("4 - Consultar veiculo");
			System.out.println("5 - Consultar passageiro");
			System.out.println("6 - Consultar motorista");
			System.out.println("0 - Voltar");
			System.out.println("=========================================");
			System.out.println("Opção: ");
			try {
				opcao = Integer.parseInt(sc.nextLine().trim());
			} catch(NumberFormatException e) {
				System.out.println("Opcao invalida! Digite apenas o numero da opcao.");
				opcao = -1;
				continue;
			}
			
			switch(opcao) {
			case 1:
				empresa.listarCorridasSolicitadas();
				break;
			case 2:
				empresa.listarPassageiros();
				break;
			case 3:
				empresa.listarMotoristasDisponiveis();
				break;
			case 4:
				buscaVeiculo(sc);
			case 5:
				buscaPassageiro(sc);
				break;
			case 6:
				buscaMotorista(sc);
				break;
			case 0:
				break;
			default:
				System.out.println("Opcao invalida!");
			}
		} while(opcao != 0);
	}
	
	private Passageiro buscaPassageiro(Scanner in) {
		Passageiro p = empresa.buscarPassageiroPorCpf(in);
		if(p == null) {
			System.out.println("Passageiro nao encontrado!");
			return null;
		}
		return p;
		
	}
	
	private Motorista buscaMotorista(Scanner in) {
		Motorista m = empresa.buscarMotoristaPorCpf(in);
		if(m == null) {
			System.out.println("Motorista nao encontrado!");
			return null;
		}
		return m;
	}
	
	private Veiculo buscaVeiculo(Scanner in) {
		Veiculo v = empresa.buscarVeiculoPorPlaca(in);
		//buscarVeiculoPorPlaca ja imprime o veículo (ou a mensagem de nao encontrado) e retorna null
		if(v != null) {
			System.out.println(v.toString());
		}
		return v;
	}
}
