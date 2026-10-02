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
		
		System.out.println("-----------------");
		System.out.println("Escolha uma opção:");
		System.out.println("1 - Menu Cadastros");
		System.out.println("2 - Menu Corridas");
		System.out.println("3 - Menu Consultas");
		System.out.println("0 - Fechar menu");
		int opcao = sc.nextInt();
		
		//terminar switch case
		switch(opcao) {
		case 1:
			menuCadastros();
			break;
			
		
		}
	}
	
	private void menuCadastros() {
		
	}
	
	private void menuCorridas() {
		
	}
	
	private void menuConsultas() {
		
	}
}
