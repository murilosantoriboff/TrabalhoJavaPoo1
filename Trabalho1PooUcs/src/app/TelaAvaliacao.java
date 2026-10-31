package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class TelaAvaliacao extends JDialog {

	private static final long serialVersionUID = 1L;

	private EmpresaTransporte empresa;

	private JTextField txtBusca = new JTextField(25);
	private JButton btnPesquisar = new JButton("Pesquisar");
	private CorridaTableModel modeloCorridas = new CorridaTableModel();
	private JTable tabelaCorridas = new JTable(modeloCorridas);
	private JComboBox<Integer> cmbNota = new JComboBox<>();
	private JTextArea txtComentario = new JTextArea(3, 40);
	private JButton btnAvaliar = new JButton("Avaliar");

	public TelaAvaliacao(EmpresaTransporte empresa) {
		super((Frame) null, "Avaliar motorista", true);
		this.empresa = empresa;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);

		add(criarPainelPesquisa(), BorderLayout.NORTH);
		add(criarPainelTabela(), BorderLayout.CENTER);
		add(criarPainelAvaliacao(), BorderLayout.SOUTH);

		btnPesquisar.addActionListener(e -> pesquisar());
		txtBusca.addActionListener(e -> pesquisar());
		btnAvaliar.addActionListener(e -> avaliar());

		pesquisar();
		setSize(1100, 520);
		setLocationRelativeTo(null);
	}

	private JPanel criarPainelPesquisa() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painel.add(new JLabel("Passageiro (parte do nome):"));
		painel.add(txtBusca);
		painel.add(btnPesquisar);
		return painel;
	}

	private JScrollPane criarPainelTabela() {
		tabelaCorridas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CorridaTableModel.ajustarColunas(tabelaCorridas);
		JScrollPane painel = new JScrollPane(tabelaCorridas);
		painel.setBorder(BorderFactory.createTitledBorder("Corridas finalizadas"));
		return painel;
	}

	private JPanel criarPainelAvaliacao() {
		DefaultComboBoxModel<Integer> notas = new DefaultComboBoxModel<>();
		notas.addAll(List.of(5, 4, 3, 2, 1));
		cmbNota.setModel(notas);
		cmbNota.setSelectedIndex(0);
		cmbNota.setPreferredSize(new Dimension(70, cmbNota.getPreferredSize().height));

		JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		painel.setBorder(BorderFactory.createTitledBorder("Avaliação"));
		painel.add(new JLabel("Nota:"));
		painel.add(cmbNota);
		painel.add(new JLabel("Comentário (opcional):"));
		painel.add(new JScrollPane(txtComentario));
		painel.add(btnAvaliar);
		return painel;
	}

	private void pesquisar() {
		modeloCorridas.setCorridas(empresa.buscarCorridas(txtBusca.getText(), List.of(StatusCorrida.FINALIZADA)));
	}

	private void avaliar() {
		int linha = tabelaCorridas.getSelectedRow();
		if (linha < 0) {
			JOptionPane.showMessageDialog(this, "Selecione uma corrida na lista.", "Atenção", JOptionPane.WARNING_MESSAGE);
			return;
		}

		Corrida corrida = modeloCorridas.getCorrida(linha);
		try {
			corrida.avaliar((Integer) cmbNota.getSelectedItem(), txtComentario.getText());

			Motorista motorista = corrida.getMotorista();
			JOptionPane.showMessageDialog(this, String.format("Avaliação registrada!%nMédia de %s: %.1f",
					motorista.getNome(), motorista.calcularMediaAvaliacoes()));
			txtComentario.setText("");
			pesquisar();
		} catch (TransporteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
