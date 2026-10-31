package app;

import java.util.ArrayList;
import java.util.List;


public class EmpresaTransporte {

	private List<Passageiro> passageiros;
	private List<Motorista> motoristas;
	private List<Veiculo> veiculos;
	private List<Corrida> corridas;

	public EmpresaTransporte() {
		this.passageiros = new ArrayList<>();
		this.motoristas = new ArrayList<>();
		this.veiculos = new ArrayList<>();
		this.corridas = new ArrayList<>();
	}

	public void cadastrarPassageiro(Passageiro p) throws DadoInvalidoException, RegistroDuplicadoException {
		Validacao.validarNome(p.getNome());
		Validacao.validarCpf(p.getCPF());
		Validacao.validarTelefone(p.getTelefone());
		verificarCpfPassageiroLivre(p.getCPF());
		passageiros.add(p);
	}

	public void cadastrarMotorista(Motorista m) throws DadoInvalidoException, RegistroDuplicadoException {
		Validacao.validarNome(m.getNome());
		Validacao.validarCpf(m.getCPF());
		Validacao.validarTelefone(m.getTelefone());
		Validacao.validarCnh(m.getCnh());
		verificarCpfMotoristaLivre(m.getCPF());
		if (m.getVeiculo() == null) {
			throw new DadoInvalidoException("Selecione um veiculo!");
		}
		for (Motorista existente : motoristas) {
			if (existente.getVeiculo() == m.getVeiculo()) {
				throw new RegistroDuplicadoException("Esse veiculo ja pertence ao motorista " + existente.getNome() + "!");
			}
		}
		motoristas.add(m);
	}

	public void cadastrarVeiculo(Veiculo v) throws DadoInvalidoException, RegistroDuplicadoException {
		Validacao.validarPlaca(v.getPlaca());
		Validacao.validarObrigatorio(v.getModelo(), "Modelo");
		Validacao.validarObrigatorio(v.getMarca(), "Marca");
		Validacao.validarAno(v.getAno());
		Validacao.validarLugares(v.getQtdPassageiros(), v.getMinimoLugares(), v.getMaximoLugares());
		verificarPlacaLivre(v.getPlaca());
		veiculos.add(v);
	}

	public void verificarCpfPassageiroLivre(String cpf) throws RegistroDuplicadoException {
		for (Passageiro p : passageiros) {
			if (p.getCPF().equals(cpf)) {
				throw new RegistroDuplicadoException("Ja existe um passageiro com esse CPF!");
			}
		}
	}

	public void verificarCpfMotoristaLivre(String cpf) throws RegistroDuplicadoException {
		for (Motorista m : motoristas) {
			if (m.getCPF().equals(cpf)) {
				throw new RegistroDuplicadoException("Ja existe um motorista com esse CPF!");
			}
		}
	}

	public void verificarPlacaLivre(String placa) throws RegistroDuplicadoException {
		for (Veiculo v : veiculos) {
			if (v.getPlaca().equalsIgnoreCase(placa)) {
				throw new RegistroDuplicadoException("Ja existe um veiculo com essa placa!");
			}
		}
	}

	public Corrida solicitarCorrida(Passageiro p, String origem, String destino, double distancia, CategoriaCorrida cat,
			FormaPagamento fp) throws DadoInvalidoException {

		if (p == null) {
			throw new DadoInvalidoException("Selecione um passageiro!");
		}
		Validacao.validarObrigatorio(origem, "Origem");
		Validacao.validarObrigatorio(destino, "Destino");
		Validacao.validarDistancia(distancia);
		if (cat == null) {
			throw new DadoInvalidoException("Selecione uma categoria!");
		}
		if (fp == null) {
			throw new DadoInvalidoException("Selecione uma forma de pagamento!");
		}

		Corrida corrida = new Corrida(p, origem.trim(), destino.trim(), distancia, cat, fp);
		corridas.add(corrida);
		p.adicionarCorrida(corrida);
		return corrida;
	}

	
	public List<Passageiro> buscarPassageiros(String texto) {
		List<Passageiro> encontrados = new ArrayList<>();
		for (Passageiro p : passageiros) {
			if (contem(p.getNome(), texto)) {
				encontrados.add(p);
			}
		}
		return encontrados;
	}

	public List<Motorista> buscarMotoristas(String texto) {
		List<Motorista> encontrados = new ArrayList<>();
		for (Motorista m : motoristas) {
			if (contem(m.getNome(), texto)) {
				encontrados.add(m);
			}
		}
		return encontrados;
	}

	public List<Motorista> buscarMotoristasDisponiveis(String texto) {
		List<Motorista> encontrados = new ArrayList<>();
		for (Motorista m : buscarMotoristas(texto)) {
			if (m.getDisponibilidade()) {
				encontrados.add(m);
			}
		}
		return encontrados;
	}

	public List<Veiculo> buscarVeiculos(String texto) {
		List<Veiculo> encontrados = new ArrayList<>();
		for (Veiculo v : veiculos) {
			if (contem(v.getPlaca(), texto) || contem(v.getMarca(), texto) || contem(v.getModelo(), texto)) {
				encontrados.add(v);
			}
		}
		return encontrados;
	}

	public List<Veiculo> buscarVeiculosLivres(String texto) {
		List<Veiculo> encontrados = new ArrayList<>();
		for (Veiculo v : buscarVeiculos(texto)) {
			boolean emUso = false;
			for (Motorista m : motoristas) {
				if (m.getVeiculo() == v) {
					emUso = true;
				}
			}
			if (!emUso) {
				encontrados.add(v);
			}
		}
		return encontrados;
	}

	public List<Corrida> buscarCorridas(String nomePassageiro) {
		List<Corrida> encontradas = new ArrayList<>();
		for (Corrida c : corridas) {
			if (contem(c.getPassageiro().getNome(), nomePassageiro)) {
				encontradas.add(c);
			}
		}
		return encontradas;
	}

	public List<Corrida> buscarCorridas(String nomePassageiro, List<StatusCorrida> situacoes) {
		List<Corrida> encontradas = new ArrayList<>();
		for (Corrida c : buscarCorridas(nomePassageiro)) {
			if (situacoes.contains(c.getStatus())) {
				encontradas.add(c);
			}
		}
		return encontradas;
	}

	private boolean contem(String valor, String texto) {
		if (texto == null) {
			return true;
		}
		return valor.toLowerCase().contains(texto.trim().toLowerCase());
	}
}
