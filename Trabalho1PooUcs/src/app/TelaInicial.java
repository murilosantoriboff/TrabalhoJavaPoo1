package app;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaInicial extends JFrame implements ActionListener{

	private JLabel titulo, subtitulo, introducao;
	private JButton passageiro, motorista;
	private JPanel fundo, painelCont, botoes;
	
	public TelaInicial() {
		
		setTitle("Sistema de Transporte");
		setSize(1200, 750);
		setMinimumSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		
		criaComponentes();
		
		add(fundo);
	}

	private void criaComponentes() {
		
		
		fundo = new JPanel(new GridBagLayout());
		painelCont = new JPanel();
		botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		
		painelCont.setLayout(new BoxLayout(painelCont, BoxLayout.Y_AXIS));
		
		ImageIcon imagemOriginal = new ImageIcon("Images/imagemTelaInicial.png");
		Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		JLabel imagem = new JLabel(new ImageIcon(imagemRedimensionada));
		imagem.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCont.add(imagem);
		painelCont.add(Box.createVerticalStrut(20));
		
		titulo = new JLabel ("Bem Vindo ao Sistema de Transporte!");
		titulo.setFont(new Font("Arial", Font.BOLD, 28));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		painelCont.add(titulo);
		painelCont.add(Box.createVerticalStrut(20));
		
		subtitulo = new JLabel ("Sua mobilidade começa aqui.");
		subtitulo.setFont(new Font("Arial", Font.BOLD, 18));
		subtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCont.add(subtitulo);
		painelCont.add(Box.createVerticalStrut(45));
		
		introducao = new JLabel ("Para continuar, escolha como deseja acessar o sistema:");
		introducao.setFont(new Font("Arial", Font.BOLD, 16));
		introducao.setHorizontalAlignment(SwingConstants.CENTER);
		introducao.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelCont.add(introducao);
		painelCont.add(Box.createVerticalStrut(25));
		
		passageiro = new JButton ("Passageiro");
		passageiro.setFont(new Font("Arial", Font.BOLD, 18));
		passageiro.setPreferredSize(new Dimension(200, 60));
		passageiro.addActionListener(this);
		
		motorista = new JButton ("Motorista");
		motorista.setFont(new Font("Arial", Font.BOLD, 18));
		motorista.setPreferredSize(new Dimension(200, 60));
		motorista.addActionListener(this);
		
		botoes.add(passageiro);
		botoes.add(motorista);
		painelCont.add(botoes);
		
		fundo.add(painelCont);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == passageiro) {
			TelaPassageiro telaPassageiro = new TelaPassageiro(this);
			telaPassageiro.setVisible(true);
		}
		
	}

}
