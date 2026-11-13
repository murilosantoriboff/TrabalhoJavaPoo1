package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.EmpresaTransporte;

public class TelaPassageiro extends JDialog implements ActionListener{

	private JButton btnCadastro, btnCorrida, btnConsulta, btnAvaliar;
	private JLabel titulo, introducao, lblCadastro, lblCorrida, lblConsulta, lblAvaliar, o1, o2, o3, o4;
	private JPanel painelPrincipal, painelSup, painelCent, painelBtns;
	private EmpresaTransporte empresa = new EmpresaTransporte();
	
	public TelaPassageiro(TelaInicial telaInicial) {
		
		super(telaInicial, "Passageiro", true);
		
		setSize(900,600);
		setMinimumSize(new Dimension(800, 500));
		setLocationRelativeTo(telaInicial);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		criaConteudo();
		
		add(painelPrincipal);
	}

	
	private void criaConteudo() {
		
		painelPrincipal = new JPanel(new BorderLayout());
		painelSup = new JPanel();
		painelSup.setLayout(new BoxLayout(painelSup, BoxLayout.Y_AXIS));
		painelCent = new JPanel();
		painelCent.setLayout(new GridBagLayout());
		painelBtns = new JPanel();
		painelBtns.setLayout(new GridLayout(2,2,25,25));
		painelBtns.setPreferredSize(new Dimension(500,250));
		
		painelSup.add(Box.createVerticalStrut(60));
		titulo = new JLabel("Área do Passageiro");
		titulo.setFont(new Font("Arial", Font.BOLD, 28));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelSup.add(titulo);
		
		painelSup.add(Box.createVerticalStrut(30));
		introducao = new JLabel("Escolha a opção desejada:");
		introducao.setFont(new Font("Arial", Font.BOLD, 18));
		introducao.setHorizontalAlignment(SwingConstants.CENTER);
		introducao.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelSup.add(introducao);
		
		btnCadastro = new JButton();
		btnCadastro.setLayout(new BoxLayout(btnCadastro, BoxLayout.Y_AXIS));
		btnCadastro.setPreferredSize(new Dimension(200, 100));
		
		btnCadastro.add(Box.createVerticalGlue());
		lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Arial", Font.BOLD, 18));
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCadastro.add(lblCadastro);
		
		o1 = new JLabel("Cadastrar Passageiro");
		o1.setFont(new Font("Arial", Font.PLAIN, 14));
		o1.setHorizontalAlignment(SwingConstants.CENTER);
		o1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCadastro.add(o1);
		btnCadastro.add(Box.createVerticalGlue());
		
		btnCorrida = new JButton();
		btnCorrida.setLayout(new BoxLayout(btnCorrida, BoxLayout.Y_AXIS));
		btnCorrida.setPreferredSize(new Dimension(200, 100));
		
		btnCorrida.add(Box.createVerticalGlue());
		lblCorrida = new JLabel("Corridas");
		lblCorrida.setFont(new Font("Arial", Font.BOLD, 18));
		lblCorrida.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrida.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCorrida.add(lblCorrida);
		
		o2 = new JLabel("Visualizar/Solicitar Corridas");
		o2.setFont(new Font("Arial", Font.PLAIN, 14));
		o2.setHorizontalAlignment(SwingConstants.CENTER);
		o2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCorrida.add(o2);
		btnCorrida.add(Box.createVerticalGlue());
		
		btnConsulta = new JButton();
		btnConsulta.setLayout(new BoxLayout(btnConsulta, BoxLayout.Y_AXIS));
		btnConsulta.setPreferredSize(new Dimension(200, 100));
		
		btnConsulta.add(Box.createVerticalGlue());
		lblConsulta = new JLabel("Consultas");
		lblConsulta.setFont(new Font("Arial", Font.BOLD, 18));
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConsulta.add(lblConsulta);
		
		o3 = new JLabel("Realizar Consulta");
		o3.setFont(new Font("Arial", Font.PLAIN, 14));
		o3.setHorizontalAlignment(SwingConstants.CENTER);
		o3.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConsulta.add(o3);
		btnConsulta.add(Box.createVerticalGlue());
		
		btnAvaliar = new JButton();
		btnAvaliar.setLayout(new BoxLayout(btnAvaliar, BoxLayout.Y_AXIS));
		btnAvaliar.setPreferredSize(new Dimension(200, 100));
		
		btnAvaliar.add(Box.createVerticalGlue());
		lblAvaliar = new JLabel("Avaliar");
		lblAvaliar.setFont(new Font("Arial", Font.BOLD, 18));
		lblAvaliar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvaliar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAvaliar.add(lblAvaliar);
		
		o4 = new JLabel("Avaliar uma Corrida");
		o4.setFont(new Font("Arial", Font.PLAIN, 14));
		o4.setHorizontalAlignment(SwingConstants.CENTER);
		o4.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAvaliar.add(o4);
		btnAvaliar.add(Box.createVerticalGlue());
		
		painelBtns.add(btnCadastro);
		btnCadastro.addActionListener(this);
		painelBtns.add(btnCorrida);
		btnCorrida.addActionListener(this);
		painelBtns.add(btnConsulta);
		btnConsulta.addActionListener(this);
		painelBtns.add(btnAvaliar);
		btnAvaliar.addActionListener(this);
		
		painelCent.add(painelBtns);
		
		painelPrincipal.add(painelSup, BorderLayout.NORTH);
		painelPrincipal.add(painelCent, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnCorrida) {
			new TelaCorrida(empresa).setVisible(true);
		}
		
		if(e.getSource()==btnAvaliar) {
			new TelaAvaliacao(empresa).setVisible(true);
		}
	}

}
