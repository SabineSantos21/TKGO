package telas_cadastrar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import classes.Motoboy;
import telas_pesquisar.Pesquisar_Cliente;
import telas_pesquisar.Pesquisar_Motoboy;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Cadastro_Motoboy extends JFrame {

	private JPanel contentPane;
	private JTextField inputNome;
	private JTextField inputTelefone;
	private JTextField inputEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Cadastro_Motoboy frame = new Tela_Cadastro_Motoboy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_Cadastro_Motoboy() {
		setTitle("Tela de Cadastro de Motoboy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Novo Motoboy");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 203, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 61, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		inputNome = new JTextField();
		inputNome.setBounds(10, 78, 404, 31);
		contentPane.add(inputNome);
		inputNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 184, 84, 13);
		contentPane.add(lblNewLabel_2);
		
		inputTelefone = new JTextField();
		inputTelefone.setBounds(10, 207, 404, 31);
		contentPane.add(inputTelefone);
		inputTelefone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 119, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(10, 143, 404, 31);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		JButton btnSair = new JButton("Voltar");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Motoboy exibir = new Pesquisar_Motoboy();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnSair.setBounds(9, 296, 85, 23);
		contentPane.add(btnSair);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					Motoboy motoboy = new Motoboy(inputNome.getText(), inputEmail.getText(), inputTelefone.getText());
					motoboy.createMotoboy(motoboy);
					
					inputNome.setText("");
					inputEmail.setText("");
					inputTelefone.setText("");
					
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(329, 296, 85, 23);
		contentPane.add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputNome.setText("");
				inputEmail.setText("");
				inputTelefone.setText("");
			}
		});
		btnLimpar.setBounds(172, 297, 85, 23);
		contentPane.add(btnLimpar);
		
	}	
}
