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
		
		System.out.println("Digite o CPF do passageiro: ");
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
	}

	public void cadastrarMotorista(Motorista m) {

		for(int i=0;i<motoristas.size();i++) {
			if(m.getCPF().equalsIgnoreCase(motoristas.get(i).getCPF())) {
				System.out.println("Já existe um motorista com esse CPF!");
			}
			if(m.getVeiculo() == motoristas.get(i).getVeiculo()) {
				System.out.println("Esse veiculo já pertence ao motorista " + motoristas.get(i).getNome() + "!");
			}
		}

		motoristas.add(m);
	}

	public void cadastrarVeiculo(Veiculo v) {

		for(int i=0;i<veiculos.size();i++) {
			if(v.getPlaca().equalsIgnoreCase(veiculos.get(i).getPlaca())) {
				System.out.println("Já existe um veiculo com essa placa!");
			}
		}

		veiculos.add(v);
	}
	
	public Corrida solicitarCorrida(Passageiro p, String origem, String destino, double distancia,
			CategoriaCorrida cat, FormaPagamento fp) {

		Corrida corrida = new Corrida(p, origem, destino, distancia, cat, fp);

		corridas.add(corrida);
		p.adicionarCorrida(corrida);

		return corrida;
	}
	
	public String buscarPassageiroPorCpf(Scanner in) {
		String cpf = null;
		
		System.out.println("Digite o CPF do passageiro a ser consultado: ");
		cpf = in.nextLine();
		
		for(int i=0;i<passageiros.size();i++) {
			if(cpf.equalsIgnoreCase(passageiros.get(i).getCPF())) {
				return passageiros.get(i).toString();
			}
		}
		
		System.out.println("Passageiro não encontrado!");
		
		return null;
	}
	
	public Motorista buscarMotoristaPorCpf(String cpf) {
		
		for(int i=0;i<motoristas.size();i++) {
			if(cpf.equalsIgnoreCase(motoristas.get(i).getCPF())) {
				return motoristas.get(i);
			}
		}
		
		System.out.println("Motorista não encontrado!");
		
		return null;
	}
	
	public Veiculo buscarVeiculoPorPlaca(String placa) {
		
		for(int i=0;i<veiculos.size();i++) {
			if(placa.equalsIgnoreCase(veiculos.get(i).getPlaca())) {
				System.out.println(veiculos.get(i));
			}
		}
		
		System.out.println("Veiculo não encontrado!");
		
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
		
		for(int i=0;i<motoristas.size();i++) {
			if(motoristas.get(i).getDisponibilidade()==true) {
				System.out.println(motoristas.get(i).getNome());
			}
		}
	}
}
