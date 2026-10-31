package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class TelaCorrida extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String TODAS = "Todas";

	private EmpresaTransporte empresa;

	private JTextField txtBuscaPassageiro = new JTextField(25);
	private JButton btnPesquisarPassageiro = new JButton("Pesquisar");
	private DefaultListModel<Passageiro> modeloPassageiros = new DefaultListModel<>();
	private JList<Passageiro> listaPassageiros = new JList<>(modeloPassageiros);
	private JTextField txtOrigem = new JTextField();
	private JTextField txtDestino = new JTextField();
	private JTextField txtDistancia = new JTextField();
	private JComboBox<CategoriaCorrida> cmbCategoria = new JComboBox<>();
	private JComboBox<FormaPagamento> cmbPagamento = new JComboBox<>();
	private JButton btnSolicitar = new JButton("Solicitar corrida");

	// Aba Acompanhar
	private JTextField txtBuscaCorrida = new JTextField(20);
	private JComboBox<String> cmbSituacao = new JComboBox<>();
	private JButton btnPesquisarCorrida = new JButton("Pesquisar");
	private CorridaTableModel modeloCorridas = new CorridaTableModel();
	private JTable tabelaCorridas = new JTable(modeloCorridas);
	private JTextField txtBuscaMotorista = new JTextField(20);
	private JButton btnPesquisarMotorista = new JButton("Pesquisar");
	private DefaultListModel<Motorista> modeloMotoristas = new DefaultListModel<>();
	private JList<Motorista> listaMotoristas = new JList<>(modeloMotoristas);
	private JButton btnAceitar = new JButton("Aceitar");
	private JButton btnIniciar = new JButton("Iniciar");
	private JButton btnFinalizar = new JButton("Finalizar");
	private JButton btnCancelar = new JButton("Cancelar corrida");

	private JTabbedPane abas = new JTabbedPane();

	public TelaCorrida(EmpresaTransporte empresa) {
		super((Frame) null, "Corridas", true); 
		this.empresa = empresa;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true); 

		abas.addTab("Solicitar", criarAbaSolicitar());
		abas.addTab("Acompanhar", criarAbaAcompanhar());
		add(abas);

		btnPesquisarPassageiro.addActionListener(e -> pesquisarPassageiros());
		txtBuscaPassageiro.addActionListener(e -> pesquisarPassageiros());
		btnSolicitar.addActionListener(e -> solicitar());

		btnPesquisarCorrida.addActionListener(e -> pesquisarCorridas());
		txtBuscaCorrida.addActionListener(e -> pesquisarCorridas());
		cmbSituacao.addActionListener(e -> pesquisarCorridas());
		btnPesquisarMotorista.addActionListener(e -> pesquisarMotoristas());
		txtBuscaMotorista.addActionListener(e -> pesquisarMotoristas());
		btnAceitar.addActionListener(e -> aceitar());
		btnIniciar.addActionListener(e -> iniciar());
		btnFinalizar.addActionListener(e -> finalizar());
		btnCancelar.addActionListener(e -> cancelar());

		pesquisarPassageiros();
		pesquisarCorridas();
		pesquisarMotoristas();

		setSize(1100, 620);
		setLocationRelativeTo(null);
	}


	private JPanel criarAbaSolicitar() {
		JPanel pesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pesquisa.add(new JLabel("Passageiro (parte do nome):"));
		pesquisa.add(txtBuscaPassageiro);
		pesquisa.add(btnPesquisarPassageiro);

		listaPassageiros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagem = new JScrollPane(listaPassageiros);
		rolagem.setBorder(BorderFactory.createTitledBorder("Selecione o passageiro"));

		DefaultComboBoxModel<CategoriaCorrida> categorias = new DefaultComboBoxModel<>();
		categorias.addAll(List.of(new CatEconomica(), new CatConforto(), new CatPremium()));
		cmbCategoria.setModel(categorias);
		cmbCategoria.setSelectedIndex(0);

		DefaultComboBoxModel<FormaPagamento> pagamentos = new DefaultComboBoxModel<>();
		pagamentos.addAll(List.of(new PagPix(), new PagCartao(), new PagDinheiro()));
		cmbPagamento.setModel(pagamentos);
		cmbPagamento.setSelectedIndex(0);

		JPanel formulario = new JPanel(new GridLayout(6, 2, 6, 6));
		formulario.setBorder(BorderFactory.createTitledBorder("Dados da corrida"));
		formulario.add(new JLabel("Endereço de origem:"));
		formulario.add(txtOrigem);
		formulario.add(new JLabel("Endereço de destino:"));
		formulario.add(txtDestino);
		formulario.add(new JLabel("Distância estimada (km):"));
		formulario.add(txtDistancia);
		formulario.add(new JLabel("Categoria:"));
		formulario.add(cmbCategoria);
		formulario.add(new JLabel("Forma de pagamento:"));
		formulario.add(cmbPagamento);
		formulario.add(new JLabel());
		formulario.add(btnSolicitar);

		JPanel aba = new JPanel(new BorderLayout(6, 6));
		aba.add(pesquisa, BorderLayout.NORTH);
		aba.add(rolagem, BorderLayout.CENTER);
		aba.add(formulario, BorderLayout.SOUTH);
		return aba;
	}

	private JPanel criarAbaAcompanhar() {
		List<String> situacoes = new ArrayList<>();
		situacoes.add(TODAS);
		for (StatusCorrida status : StatusCorrida.values()) {
			situacoes.add(status.name());
		}
		DefaultComboBoxModel<String> modeloSituacoes = new DefaultComboBoxModel<>();
		modeloSituacoes.addAll(situacoes);
		cmbSituacao.setModel(modeloSituacoes);
		cmbSituacao.setSelectedIndex(0);

		JPanel pesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pesquisa.add(new JLabel("Passageiro (parte do nome):"));
		pesquisa.add(txtBuscaCorrida);
		pesquisa.add(new JLabel("Situação:"));
		pesquisa.add(cmbSituacao);
		pesquisa.add(btnPesquisarCorrida);

		tabelaCorridas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CorridaTableModel.ajustarColunas(tabelaCorridas);
		JScrollPane rolagemTabela = new JScrollPane(tabelaCorridas);
		rolagemTabela.setBorder(BorderFactory.createTitledBorder("Corridas"));

		JPanel pesquisaMotorista = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pesquisaMotorista.add(new JLabel("Motorista (parte do nome):"));
		pesquisaMotorista.add(txtBuscaMotorista);
		pesquisaMotorista.add(btnPesquisarMotorista);

		listaMotoristas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagemMotoristas = new JScrollPane(listaMotoristas);
		rolagemMotoristas.setPreferredSize(new Dimension(600, 90));

		JPanel motoristas = new JPanel(new BorderLayout());
		motoristas.setBorder(BorderFactory.createTitledBorder("Motoristas disponíveis (para aceitar)"));
		motoristas.add(pesquisaMotorista, BorderLayout.NORTH);
		motoristas.add(rolagemMotoristas, BorderLayout.CENTER);

		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botoes.add(btnAceitar);
		botoes.add(btnIniciar);
		botoes.add(btnFinalizar);
		botoes.add(btnCancelar);

		JPanel baixo = new JPanel(new BorderLayout());
		baixo.add(motoristas, BorderLayout.CENTER);
		baixo.add(botoes, BorderLayout.SOUTH);

		JPanel aba = new JPanel(new BorderLayout(6, 6));
		aba.add(pesquisa, BorderLayout.NORTH);
		aba.add(rolagemTabela, BorderLayout.CENTER);
		aba.add(baixo, BorderLayout.SOUTH);
		return aba;
	}

	private void pesquisarPassageiros() {
		modeloPassageiros.clear();
		modeloPassageiros.addAll(empresa.buscarPassageiros(txtBuscaPassageiro.getText()));
	}

	private void pesquisarMotoristas() {
		modeloMotoristas.clear();
		modeloMotoristas.addAll(empresa.buscarMotoristasDisponiveis(txtBuscaMotorista.getText()));
	}

	private void pesquisarCorridas() {
		Corrida selecionada = getCorridaSelecionada();
		String situacao = (String) cmbSituacao.getSelectedItem();

		if (situacao == null || situacao.equals(TODAS)) {
			modeloCorridas.setCorridas(empresa.buscarCorridas(txtBuscaCorrida.getText()));
		} else {
			modeloCorridas.setCorridas(empresa.buscarCorridas(txtBuscaCorrida.getText(), List.of(StatusCorrida.valueOf(situacao))));
		}

		int linha = modeloCorridas.getLinha(selecionada);
		if (linha >= 0) {
			tabelaCorridas.setRowSelectionInterval(linha, linha);
		}
	}

	private Corrida getCorridaSelecionada() {
		int linha = tabelaCorridas.getSelectedRow();
		if (linha < 0) {
			return null;
		}
		return modeloCorridas.getCorrida(linha);
	}

	private Corrida exigirCorridaSelecionada() {
		Corrida corrida = getCorridaSelecionada();
		if (corrida == null) {
			JOptionPane.showMessageDialog(this, "Selecione uma corrida na tabela.", "Atenção", JOptionPane.WARNING_MESSAGE);
		}
		return corrida;
	}

	private void atualizarAposAcao() {
		pesquisarCorridas();
		pesquisarMotoristas();
	}

	private void mostrarErro(TransporteException e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}


	private void solicitar() {
		try {
			double distancia = Validacao.converterDistancia(txtDistancia.getText());
			Corrida nova = empresa.solicitarCorrida(listaPassageiros.getSelectedValue(), txtOrigem.getText(),
					txtDestino.getText(), distancia, (CategoriaCorrida) cmbCategoria.getSelectedItem(),
					(FormaPagamento) cmbPagamento.getSelectedItem());

			JOptionPane.showMessageDialog(this, "Corrida " + nova.getId() + " solicitada!");
			txtOrigem.setText("");
			txtDestino.setText("");
			txtDistancia.setText("");
			atualizarAposAcao();
			abas.setSelectedIndex(1);
		} catch (TransporteException e) {
			mostrarErro(e);
		}
	}

	private void aceitar() {
		Corrida corrida = exigirCorridaSelecionada();
		if (corrida == null) {
			return;
		}
		try {
			corrida.aceitar(listaMotoristas.getSelectedValue());
			JOptionPane.showMessageDialog(this, "Corrida " + corrida.getId() + " aceita por " + corrida.getMotorista().getNome() + "!");
			atualizarAposAcao();
		} catch (TransporteException e) {
			mostrarErro(e);
		}
	}

	private void iniciar() {
		Corrida corrida = exigirCorridaSelecionada();
		if (corrida == null) {
			return;
		}
		try {
			corrida.iniciar();
			JOptionPane.showMessageDialog(this, "Corrida " + corrida.getId() + " iniciada!");
			atualizarAposAcao();
		} catch (TransporteException e) {
			mostrarErro(e);
		}
	}

	private void finalizar() {
		Corrida corrida = exigirCorridaSelecionada();
		if (corrida == null) {
			return;
		}
		try {
			corrida.finalizar();
			JOptionPane.showMessageDialog(this, String.format("Corrida %d finalizada!%nValor da corrida: R$ %.2f%nValor final: %s",
					corrida.getId(), corrida.getValorBase(), corrida.getValorFormatado()));
			atualizarAposAcao();
		} catch (TransporteException e) {
			mostrarErro(e);
		}
	}

	private void cancelar() {
		Corrida corrida = exigirCorridaSelecionada();
		if (corrida == null) {
			return;
		}
		try {
			corrida.cancelar();
			JOptionPane.showMessageDialog(this, "Corrida " + corrida.getId() + " cancelada!");
			atualizarAposAcao();
		} catch (TransporteException e) {
			mostrarErro(e);
		}
	}
}
