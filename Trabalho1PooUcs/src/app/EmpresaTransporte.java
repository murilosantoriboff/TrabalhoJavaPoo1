package app;

import java.util.ArrayList;

public class EmpresaTransporte {

	private ArrayList<Passageiro> passageiros = new ArrayList<>();
	private ArrayList<Motorista> motoristas = new ArrayList<>();
	private ArrayList<Veiculo> veiculos = new ArrayList<>();
	private ArrayList<Corrida> corridas = new ArrayList<>();
	
	//Contrutor
	public EmpresaTransporte() {
		
	}
	
	public void cadastrarPassageiro(Passageiro p) {
		
	}
	
	public void cadastrarMotorista(Motorista m) {
		
	}
	
	public void cadastrarVeiculo() {
		
	}
	
	public Corrida solicitarCorrida(Passageiro p, String origem, String destino, double distancia, 
			CategoriaCorrida cat, FormaPagamento fp) {
		
		return new Corrida();
	}
	
	public Passageiro buscarPassageiroPorCpf(String cpf) {
		
		for(int i=0;i<passageiros.size();i++) {
			if(cpf == passageiros.get(i).getCPF()) {
				return passageiros.get(i);
			}
		}
		return null;
	}
	
	public Motorista buscarMotoristaPorCpf(String cpf) {
		
		for(int i=0;i<motoristas.size();i++) {
			if(cpf == motoristas.get(i).getCPF()) {
				return motoristas.get(i);
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
		return null;
	}
}
