package app;

import java.time.Year;
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

		String nome = "";
		String cpf = "";
		String telefone = "";

		while(nome.isEmpty()) {
			System.out.println("Digite o nome do passageiro: ");
			String digitado = in.nextLine().trim();

			if(digitado.isEmpty() || digitado.matches(".*\\d.*")) {
				// Nao sabia como verificar se o numero tinha algum numero e achei isso no StackOverflow
				System.out.println("Nome invalido! Nao pode ficar vazio nem conter numeros.");
			} else {
				nome = digitado;
			}
		}

		while(cpf.isEmpty()) {
			System.out.println("Digite o CPF (apenas numeros) do passageiro: ");
			String digitado = in.nextLine().trim();

			boolean existe = false;
			for(Passageiro p : passageiros) {
				if(p.getCPF().equals(digitado)) {
					existe = true;
				}
			}

			if(!digitado.matches("\\d{11}")) {
				System.out.println("CPF invalido! Digite exatamente 11 numeros.");
			} else if(existe) {
				System.out.println("Ja existe um passageiro com esse CPF!");
			} else {
				cpf = digitado;
			}
		}

		while(telefone.isEmpty()) {
			System.out.println("Digite o telefone do passageiro (DDD + numero): ");
			String digitado = in.nextLine().trim();

			if(!digitado.matches("\\d{10,11}")) {
				System.out.println("Telefone invalido! Digite 10 ou 11 numeros.");
			} else {
				telefone = digitado;
			}
		}

		passageiros.add(new Passageiro(nome, cpf, telefone));
		System.out.println("Passageiro Cadastrado!");
	}

	//REALIZAR A VERIFICAÇÃO DE VEICULO, VERIFICAR SE UM MOTORISTA JA POSSUI O VEICULO ESCOLHIDO NA HORA DE ASSOCIAR
	public void cadastrarMotorista(Scanner in) {

		if(veiculos.isEmpty()) {
			System.out.println("Nenhum veiculo cadastrado! Cadastre um veiculo antes de cadastrar o motorista.");
			return;
		}

		// guarda so os veiculos que ainda nao tem motorista
		ArrayList<Veiculo> livres = new ArrayList<>();
		for(Veiculo veiculo : veiculos) {
			boolean emUso = false;
			for(Motorista motorista : motoristas) {
				if(motorista.getVeiculo() == veiculo) {
					emUso = true;
				}
			}
			if(!emUso) {
				livres.add(veiculo);
			}
		}

		if(livres.isEmpty()) {
			System.out.println("Todos os veiculos ja estao associados a um motorista! Cadastre um novo veiculo.");
			return;
		}

		String nome = "";
		String cpf = "";
		String telefone = "";
		String cnh = "";
		Veiculo veiculoEscolhido = null;

		while(nome.isEmpty()) {
			System.out.println("Digite o nome do motorista: ");
			String digitado = in.nextLine().trim();

			if(digitado.isEmpty() || digitado.matches(".*\\d.*")) {
				System.out.println("Nome invalido! Nao pode ficar vazio nem conter numeros.");
			} else {
				nome = digitado;
			}
		}

		while(cpf.isEmpty()) {
			System.out.println("Digite o CPF (apenas numeros) do motorista: ");
			String digitado = in.nextLine().trim();

			boolean existe = false;
			for(Motorista motorista : motoristas) {
				if(motorista.getCPF().equals(digitado)) {
					existe = true;
				}
			}

			if(!digitado.matches("\\d{11}")) {
				System.out.println("CPF invalido! Digite exatamente 11 numeros.");
			} else if(existe) {
				System.out.println("Ja existe um motorista com esse CPF!");
			} else {
				cpf = digitado;
			}
		}

		while(telefone.isEmpty()) {
			System.out.println("Digite o telefone do motorista (DDD + numero): ");
			String digitado = in.nextLine().trim();

			if(!digitado.matches("\\d{10,11}")) {
				System.out.println("Telefone invalido! Digite 10 ou 11 numeros.");
			} else {
				telefone = digitado;
			}
		}

		while(cnh.isEmpty()) {
			System.out.println("Digite a CNH do motorista: ");
			String digitado = in.nextLine().trim();

			if(!digitado.matches("\\d{11}")) {
				System.out.println("CNH invalida! Digite exatamente 11 numeros.");
			} else {
				cnh = digitado;
			}
		}

		while(veiculoEscolhido == null) {
			System.out.println("Escolha o seu veiculo pela placa:");
			for(int i = 0; i < livres.size(); i++) {
				System.out.println(i + " - " + livres.get(i).getPlaca());
			}

			try {
				int opcao = Integer.parseInt(in.nextLine().trim());
				veiculoEscolhido = livres.get(opcao);
			} catch(NumberFormatException | IndexOutOfBoundsException e) {
				System.out.println("Opção invalida! Digite o número de um dos veiculos listados.");
			}
		}

		motoristas.add(new Motorista(nome, cpf, telefone, cnh, veiculoEscolhido));
		System.out.println("Motorista cadastrado e veiculo associado!");
	}

	public void cadastrarVeiculo(Scanner in) {

		String tipo = "";
		String placa = "";
		String modelo = "";
		String marca = "";
		int ano = 0;
		int qtdPassageiros = 0;
		int anoMaximo = Year.now().getValue() + 1;

		while(tipo.isEmpty()) {
			System.out.println("Digite o tipo do veiculo (Carro, Van ou Moto): ");
			String digitado = in.nextLine().trim().toLowerCase();

			if(digitado.equals("carro") || digitado.equals("moto") || digitado.equals("van")) {
				tipo = digitado;
			} else {
				System.out.println("Tipo invalido! Digite Carro, Van ou Moto.");
			}
		}

		while(placa.isEmpty()) {
			System.out.println("Digite a placa do veiculo: ");
			String digitado = in.nextLine().trim().toUpperCase().replace("-", "").replace(" ", "");

			boolean existe = false;
			for(Veiculo existente : veiculos) {
				if(existente.getPlaca().equals(digitado)) {
					existe = true;
				}
			}

			if(!digitado.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
				System.out.println("Placa invalida! Use o formato ABC1234 ou ABC1D23.");
			} else if(existe) {
				System.out.println("Ja existe um veiculo com essa placa!");
			} else {
				placa = digitado;
			}
		}

		while(modelo.isEmpty()) {
			System.out.println("Digite o modelo do veiculo (Civic, Astra, etc..): ");
			modelo = in.nextLine().trim();
		}

		while(marca.isEmpty()) {
			System.out.println("Digite a marca do veiculo (Honda, Chevrolet, etc..): ");
			marca = in.nextLine().trim();
		}

		while(ano == 0) {
			System.out.println("Digite o ano do veiculo: ");

			try {
				int digitado = Integer.parseInt(in.nextLine().trim());

				if(digitado < 1950 || digitado > anoMaximo) {
					System.out.println("Ano invalido! Digite um ano entre 1950 e " + anoMaximo + ".");
				} else {
					ano = digitado;
				}
			} catch(NumberFormatException e) {
				System.out.println("Ano invalido! Digite apenas numeros.");
			}
		}

		// limites de passageiros de cada tipo
		int minimo = 1;
		int maximo = 4;
		if(tipo.equals("moto")) {
			maximo = 1;
		} else if(tipo.equals("van")) {
			minimo = 6;
			maximo = 19;
		}

		while(qtdPassageiros == 0) {
			System.out.println("Digite a quantidade de passageiros que o veiculo aceita (sem contar o motorista): ");

			try {
				int digitado = Integer.parseInt(in.nextLine().trim());

				if(digitado < minimo || digitado > maximo) {
					System.out.println("Quantidade invalida! Para " + tipo + ", digite de " + minimo + " a " + maximo + ".");
				} else {
					qtdPassageiros = digitado;
				}
			} catch(NumberFormatException e) {
				System.out.println("Quantidade invalida! Digite apenas numeros inteiros.");
			}
		}

		Veiculo v;
		if(tipo.equals("carro")) {
			v = new Carro(placa, modelo, marca, ano, qtdPassageiros);
		} else if(tipo.equals("moto")) {
			v = new Moto(placa, modelo, marca, ano, qtdPassageiros);
		} else {
			v = new Van(placa, modelo, marca, ano, qtdPassageiros);
		}

		veiculos.add(v);
		System.out.println("Veiculo Cadastrado!");
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
