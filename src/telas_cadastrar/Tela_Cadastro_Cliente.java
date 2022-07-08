package telas_cadastrar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import telas_editar.Editar_Cliente;
import telas_pesquisar.Pesquisar_Cliente;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Cadastro_Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Cadastro_Cliente frame = new Tela_Cadastro_Cliente();
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
	public Tela_Cadastro_Cliente() {
		setTitle("Tela Cadastro Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Novo Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 111, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 51, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		inputNome = new JTextField();
		inputNome.setBounds(10, 74, 373, 28);
		contentPane.add(inputNome);
		inputNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 120, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 192, 71, 13);
		contentPane.add(lblNewLabel_3);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(10, 143, 373, 28);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		inputTelefone = new JTextField();
		inputTelefone.setBounds(10, 219, 373, 28);
		contentPane.add(inputTelefone);
		inputTelefone.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Cliente exibir = new Pesquisar_Cliente();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 275, 85, 21);
		contentPane.add(btnVoltar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputNome.setText("");
				inputEmail.setText("");
				inputTelefone.setText("");
			}
		});
		btnLimpar.setBounds(158, 275, 85, 21);
		contentPane.add(btnLimpar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					Cliente cliente = new Cliente(inputNome.getText(), inputEmail.getText(), inputTelefone.getText());
					cliente.createCliente(cliente);
					
					inputNome.setText("");
					inputEmail.setText("");
					inputTelefone.setText("");
					
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(298, 275, 85, 21);
		contentPane.add(btnSalvar);
	}

}
