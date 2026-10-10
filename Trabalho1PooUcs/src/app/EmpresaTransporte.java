package app;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpresaTransporte {


	private ArrayList<Passageiro> passageiros = new ArrayList<>();
	private ArrayList<Motorista> motoristas = new ArrayList<>();
	private ArrayList<Veiculo> veiculos = new ArrayList<>();
	private ArrayList<Corrida> corridas = new ArrayList<>();

	public EmpresaTransporte() {
		
	}
	
	public void cadastrarPassageiro(Scanner in) {

		String nome = null;
		String cpf = null;
		String telefone = null;
		
		System.out.println("Digite o nome do passageiro: ");
		nome = in.nextLine();
		
		System.out.println("Digite o CPF (apenas numeros) do passageiro: ");
		cpf = in.nextLine();
		
		for(int i=0;i<passageiros.size();i++) {
			if(cpf.equalsIgnoreCase(passageiros.get(i).getCPF())) {
				System.out.println("Já existe um passageiro com esse CPF!");
			}
		}
		
		System.out.println("Digite o telefone do passageiro: ");
		telefone = in.nextLine();
		
		Passageiro p = new Passageiro(nome, cpf, telefone);
		
		passageiros.add(p);
		System.out.println("Passageiro Cadastrado!");
	}

	//REALIZAR A VERIFICAÇÃO DE VEICULO, VERIFICAR SE UM MOTORISTA JA POSSUI O VEICULO ESCOLHIDO NA HORA DE ASSOCIAR
	public void cadastrarMotorista(Scanner in) {
		
		Veiculo v = null ;
		String nome = null;
		String cpf = null;
		String telefone = null;
		String cnh = null;
		int opcao=0;
		int verificaVeiculo = 0, verificaCpf = 0;
		
		System.out.println("Digite o nome do motorista: ");
		nome = in.nextLine();
		
		while(verificaCpf != 1) {
			System.out.println("Digite o CPF (apenas numeros) do motorista: ");
			cpf = in.nextLine();
			
			for(int i=0;i<motoristas.size();i++) {
				if(cpf.equalsIgnoreCase(motoristas.get(i).getCPF())) {
					System.out.println("Já existe um motorista com esse CPF!");
					verificaCpf = 0;
				}
			}
			verificaCpf = 1;
		}
		
		System.out.println("Digite o telefone do motorista: ");
		telefone = in.nextLine();
		
		System.out.println("Digite a cnh do motorista: ");
		cnh = in.nextLine();
		
		while(verificaVeiculo != 1) {
			
			System.out.println("Escolha o seu veículo cadastrado pela placa:");
			for(int i=0; i<veiculos.size(); i++) {
				System.out.println(i + " - " + veiculos.get(i).getPlaca());
			}
			opcao = Integer.parseInt(in.nextLine());
			
			if(opcao < 0 || opcao > veiculos.size()) {
				System.out.println("Opção inválida!");
				verificaVeiculo=0;
			}
			else {
				verificaVeiculo=1;
			}
			
		}
		
		v = veiculos.get(opcao);
		
		Motorista m = new Motorista(nome, cpf, telefone, cnh, v);

		motoristas.add(m);
		System.out.println("Motorista cadastrado e veiculo associado!");
	}

	public void cadastrarVeiculo(Scanner in) {

		int verificaPlaca = 0, verificaPassageiro;
		Veiculo v = null;
		String tipo = null;
		String modelo = null;
		String marca = null;
		String placa = null;
		int ano = 0;
		int qtdPassageiros = 0;
		
		System.out.println("Digite o tipo do veículo (Carro, Van ou Moto): ");
		tipo = in.nextLine();
		
		while(verificaPlaca != 1) {
			System.out.println("Digite a placa do veículo: ");
			placa = in.nextLine();
			
			for(int i=0;i<veiculos.size();i++) {
				if(placa.equalsIgnoreCase(veiculos.get(i).getPlaca())) {
					System.out.println("Já existe um veiculo com essa placa!");
				}
			}
			verificaPlaca = 1;
		}
		
		System.out.println("Digite o modelo do veiculo(Civic, Astra, etc..): ");
		modelo = in.nextLine();
		
		System.out.println("Digite a marca do veículo(Honda, Chevrolet, etc..): ");
		marca = in.nextLine();
		
		System.out.println("Digite o ano do veículo: ");
		ano = Integer.parseInt(in.nextLine());
		
		if(tipo.equalsIgnoreCase("carro")) {
			
			verificaPassageiro = 0;
			
			while(verificaPassageiro != 1) {
				
				System.out.println("Digite a quantidade de passageiros que o veículo aceita (sem contar o motorista): ");
				qtdPassageiros = Integer.parseInt(in.nextLine());
				
				if(qtdPassageiros < 1 || qtdPassageiros > 4) {
					System.out.println("Quantidade de passageiros inválida!");
				}
				else {
					verificaPassageiro = 1;
				}
			}
			v = new Carro(placa, modelo, marca, ano, qtdPassageiros);
		}
		
		else if(tipo.equalsIgnoreCase("moto")) {
			
			verificaPassageiro = 0;
				
			while(verificaPassageiro != 1) {
					
				System.out.println("Digite a quantidade de passageiros que o veículo aceita (sem contar o motorista): ");
				qtdPassageiros = Integer.parseInt(in.nextLine());
				
				if(qtdPassageiros < 1 || qtdPassageiros > 1) {
					System.out.println("Quantidade de passageiros inválida!");
				}
				else {
					verificaPassageiro = 1;
				}
			}
			v = new Moto(placa, modelo, marca, ano, qtdPassageiros);
		}
		else if(tipo.equalsIgnoreCase("van")) {
			
			verificaPassageiro = 0;
			
			while(verificaPassageiro != 1) {
					
				System.out.println("Digite a quantidade de passageiros que o veículo aceita (sem contar o motorista): ");
				qtdPassageiros = Integer.parseInt(in.nextLine());
				
				if(qtdPassageiros < 6 || qtdPassageiros > 19) {
					System.out.println("Quantidade de passageiros inválida!");
					System.out.println("Para ser considerado VAN, a quantidade deve ser no máximo 19 e no mínimo 6!");
				}
				else {
					verificaPassageiro = 1;
				}
			}
			v = new Van(placa, modelo, marca, ano, qtdPassageiros);
		}
		
		if(v != null) {
			veiculos.add(v);
			System.out.println("Veículo Cadastrado!");
		}
	}
	
	public Corrida solicitarCorrida(Passageiro p, String origem, String destino, double distancia,
			CategoriaCorrida cat, FormaPagamento fp) {

		Corrida corrida = new Corrida(p, origem, destino, distancia, cat, fp);

		corridas.add(corrida);
		p.adicionarCorrida(corrida);

		return corrida;
	}
	
	public Passageiro buscarPassageiroPorCpf(Scanner in) {
		String cpf = null;
		
		System.out.println("Digite o CPF (apenas numeros) do passageiro a ser consultado: ");
		cpf = in.nextLine();
		
		for(int i=0;i<passageiros.size();i++) {
			if(cpf.equalsIgnoreCase(passageiros.get(i).getCPF())) {
				return passageiros.get(i);
			}
		}
		return null;
	}
	
	public Motorista buscarMotoristaPorCpf(Scanner in) {
		
		String cpf = null;
		
		System.out.println("Digite o CPF (apenas numeros) do motorista a ser consultado: ");
		cpf = in.nextLine();
		
		for(int i=0;i<motoristas.size();i++) {
			if(cpf.equalsIgnoreCase(motoristas.get(i).getCPF())) {
				return motoristas.get(i);
			}
		}
		
		return null;
	}
	
	public Veiculo buscarVeiculoPorPlaca(Scanner in) {
		
		String placa = null;
		
		System.out.println("Digite a placa do veículo que deseja consultar: ");
		placa = in.nextLine();
		
		for(int i=0;i<veiculos.size();i++) {
			if(placa.equalsIgnoreCase(veiculos.get(i).getPlaca())) {
				System.out.println(veiculos.get(i));
			}
		}
		
		return null;
	}
	
	public Corrida buscarCorridaPorId(int id) {
		
		for(int i=0;i<corridas.size();i++) {
			if(id == corridas.get(i).getId()) {
				return corridas.get(i);
			}
		}
		
		System.out.println("Corrida não encontrada!");
		
		return null;
	}
	
	public void listarCorridasSolicitadas() {
		
		System.out.println("---Corridas Solicitadas(ID)---");
		
		for(int i=0; i<corridas.size();i++) {
			System.out.println("Corrida "+ i+1 + ": " + corridas.get(i).getId());
		}
				
	}
	
	public void listarMotoristasDisponiveis() {
		
		System.out.println("---Motoristas Disponíveis---");
		
		if(motoristas.isEmpty()) {
			System.out.println("Nenhum motorista cadastrado!");
			return;
		}
		
		for(int i=0;i<motoristas.size();i++) {
			if(motoristas.get(i).getDisponibilidade()==true) {
				System.out.println(motoristas.get(i).getNome());
			}
		}
	}
	
	public void listarPassageiros() {
		
		System.out.println("---Passageiros Cadastrados---");
		
		if(passageiros.isEmpty()) {
			System.out.println("Nenhum passageiro cadastrado!");
			return;
		}
		
		for(int i=0; i<passageiros.size();i++) {
			System.out.println(passageiros.get(i).getNome());
		}
	}
}
