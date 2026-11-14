package app;

import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PersistenciaJson {

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.enable(SerializationFeature.INDENT_OUTPUT)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	public record AvaliacaoDados(int nota, String comentario) {
	}

	public record VeiculoDados(String tipo, String placa, String modelo, String marca, int ano, int qtdPassageiros) {
	}

	public record PassageiroDados(String nome, String cpf, String telefone) {
	}

	public record MotoristaDados(String nome, String cpf, String telefone, String cnh, String placaVeiculo,
			boolean disponivel, List<AvaliacaoDados> avaliacoes) {
	}

	public record CorridaDados(int id, String cpfPassageiro, String cpfMotorista, String origem, String destino,
			double distancia, String categoria, String pagamento, StatusCorrida status, double valorBase,
			double valorFinal, AvaliacaoDados avaliacao) {
	}

	public record DadosEmpresa(List<VeiculoDados> veiculos, List<PassageiroDados> passageiros,
			List<MotoristaDados> motoristas, List<CorridaDados> corridas) {
	}


	public static void salvar(EmpresaTransporte empresa, Path arquivo) throws PersistenciaException {
		DadosEmpresa dados = paraDados(empresa);
		Path temporario = arquivo.resolveSibling(arquivo.getFileName() + ".tmp");

		try {
			Path pasta = arquivo.toAbsolutePath().getParent();
			if (pasta != null) {
				Files.createDirectories(pasta);
			}
			MAPPER.writeValue(temporario.toFile(), dados);
			try {
				Files.move(temporario, arquivo, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
			} catch (AtomicMoveNotSupportedException e) {
				Files.move(temporario, arquivo, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new PersistenciaException("Nao foi possivel gravar os dados em " + arquivo + ": " + e.getMessage(), e);
		}
	}

	private static DadosEmpresa paraDados(EmpresaTransporte empresa) {
		List<VeiculoDados> veiculos = new ArrayList<>();
		for (Veiculo v : empresa.getVeiculos()) {
			veiculos.add(new VeiculoDados(v.getClass().getSimpleName(), v.getPlaca(), v.getModelo(), v.getMarca(),
					v.getAno(), v.getQtdPassageiros()));
		}

		List<PassageiroDados> passageiros = new ArrayList<>();
		for (Passageiro p : empresa.getPassageiros()) {
			passageiros.add(new PassageiroDados(p.getNome(), p.getCPF(), p.getTelefone()));
		}

		List<MotoristaDados> motoristas = new ArrayList<>();
		for (Motorista m : empresa.getMotoristas()) {
			List<AvaliacaoDados> avaliacoes = new ArrayList<>();
			for (Avaliacao a : m.getAvaliacoes()) {
				avaliacoes.add(new AvaliacaoDados(a.getNota(), a.getComentario()));
			}
			motoristas.add(new MotoristaDados(m.getNome(), m.getCPF(), m.getTelefone(), m.getCnh(),
					m.getVeiculo().getPlaca(), m.getDisponibilidade(), avaliacoes));
		}

		List<CorridaDados> corridas = new ArrayList<>();
		for (Corrida c : empresa.getCorridas()) {
			String cpfMotorista = null;
			if (c.getMotorista() != null) {
				cpfMotorista = c.getMotorista().getCPF();
			}
			AvaliacaoDados avaliacao = null;
			if (c.getAvaliacao() != null) {
				avaliacao = new AvaliacaoDados(c.getAvaliacao().getNota(), c.getAvaliacao().getComentario());
			}
			corridas.add(new CorridaDados(c.getId(), c.getPassageiro().getCPF(), cpfMotorista, c.getOrigem(),
					c.getDestino(), c.getDistancia(), c.getCategoria().getClass().getSimpleName(),
					c.getFormaPagamento().getClass().getSimpleName(), c.getStatus(), c.getValorBase(),
					c.getValorFinal(), avaliacao));
		}

		return new DadosEmpresa(veiculos, passageiros, motoristas, corridas);
	}

	// ---- carregar ----

	// Se o arquivo ainda nao existe (primeira execucao) devolve uma empresa vazia.
	public static EmpresaTransporte carregar(Path arquivo) throws PersistenciaException {
		if (!Files.exists(arquivo)) {
			return new EmpresaTransporte();
		}

		try {
			DadosEmpresa dados = MAPPER.readValue(arquivo.toFile(), DadosEmpresa.class);
			return reconstruir(dados);
		} catch (JsonProcessingException e) {
			// JSON mal formado: guarda uma copia para o usuario nao perder o conteudo quando o sistema regravar
			throw new PersistenciaException("O arquivo " + arquivo + " esta corrompido." + guardarCopia(arquivo), e);
		} catch (IOException e) {
			throw new PersistenciaException("Nao foi possivel ler o arquivo " + arquivo + ": " + e.getMessage(), e);
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage() + guardarCopia(arquivo), e);
		}
	}

	private static EmpresaTransporte reconstruir(DadosEmpresa dados) throws PersistenciaException {
		if (dados == null) {
			throw new PersistenciaException("O arquivo de dados esta vazio.");
		}

		List<Veiculo> veiculos = new ArrayList<>();
		Map<String, Veiculo> veiculosPorPlaca = new HashMap<>();
		for (VeiculoDados d : lista(dados.veiculos())) {
			Veiculo v = criarVeiculo(d);
			veiculos.add(v);
			veiculosPorPlaca.put(v.getPlaca(), v);
		}

		List<Passageiro> passageiros = new ArrayList<>();
		Map<String, Passageiro> passageirosPorCpf = new HashMap<>();
		for (PassageiroDados d : lista(dados.passageiros())) {
			Passageiro p = new Passageiro(d.nome(), d.cpf(), d.telefone());
			passageiros.add(p);
			passageirosPorCpf.put(p.getCPF(), p);
		}

		List<Motorista> motoristas = new ArrayList<>();
		Map<String, Motorista> motoristasPorCpf = new HashMap<>();
		for (MotoristaDados d : lista(dados.motoristas())) {
			Veiculo veiculo = veiculosPorPlaca.get(d.placaVeiculo());
			if (veiculo == null) {
				throw new PersistenciaException("Motorista " + d.nome() + " usa um veiculo inexistente (placa " + d.placaVeiculo() + ").");
			}
			Motorista m = new Motorista(d.nome(), d.cpf(), d.telefone(), d.cnh(), veiculo);
			for (AvaliacaoDados a : lista(d.avaliacoes())) {
				m.adicionarAvaliacao(a.nota(), a.comentario());
			}
			if (!d.disponivel()) {
				m.ocupar();
			}
			motoristas.add(m);
			motoristasPorCpf.put(m.getCPF(), m);
		}

		List<Corrida> corridas = new ArrayList<>();
		for (CorridaDados d : lista(dados.corridas())) {
			Passageiro passageiro = passageirosPorCpf.get(d.cpfPassageiro());
			if (passageiro == null) {
				throw new PersistenciaException("Corrida " + d.id() + " aponta para um passageiro inexistente.");
			}
			Motorista motorista = null;
			if (d.cpfMotorista() != null) {
				motorista = motoristasPorCpf.get(d.cpfMotorista());
				if (motorista == null) {
					throw new PersistenciaException("Corrida " + d.id() + " aponta para um motorista inexistente.");
				}
			}
			if (d.status() == null) {
				throw new PersistenciaException("Corrida " + d.id() + " sem situacao.");
			}
			Avaliacao avaliacao = null;
			if (d.avaliacao() != null) {
				avaliacao = new Avaliacao(d.avaliacao().nota(), d.avaliacao().comentario());
			}

			Corrida c = new Corrida(d.id(), passageiro, motorista, d.origem(), d.destino(), d.distancia(),
					criarCategoria(d.categoria()), criarPagamento(d.pagamento()), d.status(), d.valorBase(),
					d.valorFinal(), avaliacao);
			passageiro.adicionarCorrida(c);
			corridas.add(c);
		}

		EmpresaTransporte empresa = new EmpresaTransporte();
		empresa.restaurar(passageiros, motoristas, veiculos, corridas);
		return empresa;
	}

	private static Veiculo criarVeiculo(VeiculoDados d) throws PersistenciaException {
		String tipo = String.valueOf(d.tipo());
		switch (tipo) {
		case "Carro":
			return new Carro(d.placa(), d.modelo(), d.marca(), d.ano(), d.qtdPassageiros());
		case "Moto":
			return new Moto(d.placa(), d.modelo(), d.marca(), d.ano(), d.qtdPassageiros());
		case "Van":
			return new Van(d.placa(), d.modelo(), d.marca(), d.ano(), d.qtdPassageiros());
		default:
			throw new PersistenciaException("Tipo de veiculo desconhecido: " + tipo);
		}
	}

	private static CategoriaCorrida criarCategoria(String nome) throws PersistenciaException {
		switch (String.valueOf(nome)) {
		case "CatEconomica":
			return new CatEconomica();
		case "CatConforto":
			return new CatConforto();
		case "CatPremium":
			return new CatPremium();
		default:
			throw new PersistenciaException("Categoria de corrida desconhecida: " + nome);
		}
	}

	private static FormaPagamento criarPagamento(String nome) throws PersistenciaException {
		switch (String.valueOf(nome)) {
		case "PagDinheiro":
			return new PagDinheiro();
		case "PagCartao":
			return new PagCartao();
		case "PagPix":
			return new PagPix();
		default:
			throw new PersistenciaException("Forma de pagamento desconhecida: " + nome);
		}
	}

	// uma lista que faltou no arquivo conta como vazia
	private static <T> List<T> lista(List<T> lista) {
		if (lista == null) {
			return new ArrayList<>();
		}
		return lista;
	}

	// devolve um texto para completar a mensagem de erro
	private static String guardarCopia(Path arquivo) {
		Path copia = arquivo.resolveSibling(arquivo.getFileName() + ".corrompido");
		try {
			Files.copy(arquivo, copia, StandardCopyOption.REPLACE_EXISTING);
			return " Uma copia foi guardada em " + copia + ".";
		} catch (IOException e) {
			return "";
		}
	}
}
