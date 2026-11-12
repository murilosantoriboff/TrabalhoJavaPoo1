package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class TelaPassageiro extends JDialog implements ActionListener{

	private JButton btnCadastro, corrida, consulta;
	private JLabel titulo, introducao, lblCadastro, lblCorrida, lblConsulta, o1, o2, o3;
	private JPanel painelPrincipal, painelSup, painelCent, painelCad, painelCon, painelCorrida;
	
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
		painelCent.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		painelCad = new JPanel();
		painelCad.setLayout(new BoxLayout(painelCad, BoxLayout.Y_AXIS));
		painelCon = new JPanel();
		painelCon.setLayout((new BoxLayout(painelCon, BoxLayout.Y_AXIS)));
		painelCorrida = new JPanel();
		painelCorrida.setLayout((new BoxLayout(painelCorrida, BoxLayout.Y_AXIS)));
		
		o2 = new JLabel("Vizualisar/Solicitar Corridas");
		o3 = new JLabel("Realizar Consulta");
		
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
		
		
		painelCent.add(Box.createVerticalStrut(30));
		
		btnCadastro = new JButton();
		btnCadastro.setLayout(new BoxLayout(btnCadastro, BoxLayout.Y_AXIS));
		btnCadastro.setPreferredSize(new Dimension(200, 180));
		
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
		
		painelCent.add(btnCadastro);
		
		painelPrincipal.add(painelSup, BorderLayout.NORTH);
		painelPrincipal.add(btnCadastro, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
