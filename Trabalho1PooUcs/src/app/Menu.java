package app;

import java.util.Scanner;

public class Menu {

	private Scanner sc = new Scanner(System.in);
	private EmpresaTransporte empresa = new EmpresaTransporte();
	
	
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
				solicitarCorrida();
			    break;
			case 2: {
			    Corrida c = buscaCorrida();
			    if(c == null) break;
			    Motorista m = buscaMotorista(sc);
			    if(m == null) break;
			    c.aceitar(m);
			    break;
			}
			case 3: {
			    Corrida c = buscaCorrida();
			    if(c != null) c.iniciar();
			    break;
			}
			case 4: {
			    Corrida c = buscaCorrida();
			    if(c != null) {
			        c.finalizar();
			        System.out.println("Valor da Corrida: R$ "+c.getValorBase());
			        System.out.println(c);
			    }
			    break;
			}
			case 5: {
			    Corrida c = buscaCorrida();
			    if(c != null) c.cancelar();
			    break;
			}
			case 6: {
			    Corrida c = buscaCorrida();
			    if(c == null) break;
			    System.out.println("Nota (1 a 5):");
			    int nota;
			    try {
			        nota = Integer.parseInt(sc.nextLine().trim());
			    } catch(NumberFormatException e) {
			        System.out.println("Nota invalida! Digite apenas numeros.");
			        break;
			    }
			    System.out.println("Comentario:");
			    String comentario = sc.nextLine();
			    c.avaliar(nota, comentario);
			    break;
			}
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
				break;
			case 5: {
			    Passageiro p = buscaPassageiro(sc);
			    if(p != null) {
			        System.out.println(p);
			        p.listarCorridas();
			    }
			    break;
			}
			case 6: {
			    Motorista m = buscaMotorista(sc);
			    if(m != null) {
			        System.out.println(m);
			        System.out.println("Media das avaliacoes: " + m.calcularMediaAvaliacoes());
			        m.listarAvaliacoes();
			    }
			    break;
			}
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
		//buscarVeiculoPorPlaca ja imprime o veiculo (ou a mensagem de nao encontrado) e retorna null
		if(v != null) {
			System.out.println(v.toString());
		} else if (v==null) {
			System.out.println("Veiculo nao encontrado!");
		}
		return v;
	}
	
	private void solicitarCorrida() {
	    Passageiro p = buscaPassageiro(sc);
	    if (p == null) {
	        return; // buscaPassageiro ja avisa que não encontrou
	    }

	    String origem = "";
	    while(origem.isEmpty()) {
	        System.out.println("Digite a origem:");
	        origem = sc.nextLine().trim();
	        if(origem.isEmpty()) System.out.println("Origem nao pode ficar vazia!");
	    }

	    String destino = "";
	    while(destino.isEmpty()) {
	        System.out.println("Digite o destino:");
	        destino = sc.nextLine().trim();
	        if(destino.isEmpty()) System.out.println("Destino nao pode ficar vazio!");
	    }

	    double distancia = 0;
	    while(distancia <= 0) {
	        System.out.println("Digite a distancia em km (use ponto: 12.5):");
	        try {
	            distancia = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
	            if(distancia <= 0 || Double.isNaN(distancia) || Double.isInfinite(distancia)) {
	                System.out.println("A distancia deve ser maior que zero!");
	                distancia = 0;
	            }
	        } catch(NumberFormatException e) {
	            System.out.println("Distancia invalida! Digite apenas numeros.");
	        }
	    }

	    System.out.println("Escolha a categoria da corrida:");
	    System.out.println("1 - Econômica");
	    System.out.println("2 - Conforto");
	    System.out.println("3 - Premium");

	    CategoriaCorrida cat;
	    switch (sc.nextLine()) {
	        case "1": cat = new CatEconomica(); break;
	        case "2": cat = new CatConforto();  break;
	        case "3": cat = new CatPremium();   break;
	        default:
	            System.out.println("Categoria inválida!");
	            return;
	    }

	    System.out.println("Escolha a forma de pagamento:");
	    System.out.println("1 - Dinheiro");
	    System.out.println("2 - Cartão");
	    System.out.println("3 - Pix");

	    FormaPagamento fp;
	    switch (sc.nextLine()) {
	        case "1": fp = new PagDinheiro(); break;
	        case "2": fp = new PagCartao();   break;
	        case "3": fp = new PagPix();      break;
	        default:
	            System.out.println("Forma de pagamento inválida!");
	            return;
	    }

	    Corrida nova = empresa.solicitarCorrida(p, origem, destino, distancia, cat, fp);
	    System.out.println("Corrida solicitada com sucesso! ID da corrida: " + nova.getId());
	}
	
	private Corrida buscaCorrida() {
	    System.out.println("Digite o ID da corrida:");
	    try {
	        int id = Integer.parseInt(sc.nextLine().trim());
	        return empresa.buscarCorridaPorId(id); // ja imprime "Corrida nao encontrada"
	    } catch(NumberFormatException e) {
	        System.out.println("ID invalido! Digite apenas numeros.");
	        return null;
	    }
	}
}
