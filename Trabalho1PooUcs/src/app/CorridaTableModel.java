package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class CorridaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static final List<String> COLUNAS = List.of("Nº", "Passageiro", "Motorista", "Veículo", "Origem", "Destino",
			"Km", "Categoria", "Pagamento", "Valor", "Situação", "Nota");

	private List<Corrida> corridas = new ArrayList<>();

	public static void ajustarColunas(JTable tabela) {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(170);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(11).setPreferredWidth(40);
	}

	public void setCorridas(List<Corrida> corridas) {
		this.corridas = corridas;
		fireTableDataChanged();
	}

	public Corrida getCorrida(int linha) {
		return corridas.get(linha);
	}

	public int getLinha(Corrida corrida) {
		return corridas.indexOf(corrida);
	}

	@Override
	public int getRowCount() {
		return corridas.size();
	}

	@Override
	public int getColumnCount() {
		return COLUNAS.size();
	}

	@Override
	public String getColumnName(int coluna) {
		return COLUNAS.get(coluna);
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		Corrida c = corridas.get(linha);

		switch (coluna) {
		case 0:
			return c.getId();
		case 1:
			return c.getPassageiro().getNome();
		case 2:
			return c.getMotorista() == null ? "-" : c.getMotorista().getNome();
		case 3:
			return c.getMotorista() == null ? "-"
					: c.getMotorista().getVeiculo().getModelo() + " (" + c.getMotorista().getVeiculo().getPlaca() + ")";
		case 4:
			return c.getOrigem();
		case 5:
			return c.getDestino();
		case 6:
			return c.getDistancia();
		case 7:
			return c.getCategoria().getNome();
		case 8:
			return c.getFormaPagamento().getNome();
		case 9:
			return c.getValorFormatado();
		case 10:
			return c.getStatus();
		default:
			return c.getAvaliacao() == null ? "-" : c.getAvaliacao().getNota();
		}
	}
}
