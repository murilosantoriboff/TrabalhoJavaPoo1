package app;

import java.util.Scanner;

public class Menu {

	private Scanner sc = new Scanner(System.in);
	
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
			opcao = sc.nextInt();
			
			
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
				System.out.println("Opção inválida!");
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
			opcao = sc.nextInt();

			switch(opcao) {
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
				System.out.println("Opção inválida!");
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
			opcao = sc.nextInt();

			switch(opcao) {
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
				System.out.println("Opção inválida!");
			}
		} while(opcao != 0);
	}
	
	private void menuConsultas() {
		int opcao;

		do {
			System.out.println();
			System.out.println("=========================================");
			System.out.println(" SISTEMA DE TRANSPORTE - Consultas");
			System.out.println("=========================================");
			System.out.println("1 - Listar corridas");
			System.out.println("2 - Consultar passageiro");
			System.out.println("3 - Consultar motorista");
			System.out.println("0 - Voltar");
			System.out.println("=========================================");
			System.out.println("Opção: ");
			opcao = sc.nextInt();

			switch(opcao) {
			case 1:
				empresa.listarCorridas();
				break;
			case 2:
				consultarPassageiro();
				break;
			case 3:
				consultarMotorista();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while(opcao != 0);
	}
}
