package telas_editar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import telas_cadastrar.Tela_Cadastro_Cliente;
import telas_cadastrar.Tela_Cadastros;
import telas_pesquisar.Pesquisar_Cliente;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class Editar_Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField inputIdCliente;
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
					Editar_Cliente frame = new Editar_Cliente();
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
	public Editar_Cliente() {
		setTitle("Editar Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar Cliente");
		lblNewLabel.setBounds(10, 10, 96, 13);
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(10, 36, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		inputIdCliente = new JTextField();
		inputIdCliente.setBounds(31, 33, 119, 19);
		contentPane.add(inputIdCliente);
		inputIdCliente.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 97, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 62, 416, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_3 = new JLabel("Cliente Encontrado");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 74, 156, 13);
		contentPane.add(lblNewLabel_3);
		
		inputNome = new JTextField();
		inputNome.setBounds(10, 120, 416, 26);
		contentPane.add(inputNome);
		inputNome.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 156, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(10, 179, 416, 26);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Telefone");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 215, 61, 13);
		contentPane.add(lblNewLabel_5);
		
		inputTelefone = new JTextField();
		inputTelefone.setBounds(10, 238, 416, 26);
		contentPane.add(inputTelefone);
		inputTelefone.setColumns(10);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente cliente = new Cliente(inputNome.getText(), inputEmail.getText(), inputTelefone.getText());
					cliente.deleteCliente(inputIdCliente.getText());
					
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBounds(341, 274, 85, 21);
		contentPane.add(btnDeletar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente cliente = new Cliente(inputNome.getText(), inputEmail.getText(), inputTelefone.getText());
					cliente.updateCliente(inputIdCliente.getText(), cliente);
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
		btnAtualizar.setBounds(160, 274, 85, 21);
		contentPane.add(btnAtualizar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Pesquisar_Cliente exibir = new Pesquisar_Cliente();
					exibir.setVisible(true);
					
					setVisible(false);					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnVoltar.setBounds(10, 274, 85, 21);
		contentPane.add(btnVoltar);
		
		JButton btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Cliente cliente = new Cliente("", "", "");
					var getCliente = cliente.getCliente(inputIdCliente.getText());
					
					inputNome.setText(getCliente.Pessoa.Nome);
					inputEmail.setText(getCliente.Pessoa.Email);
					inputTelefone.setText(getCliente.Pessoa.Telefone);					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnPesquisa.setBounds(160, 33, 112, 21);
		contentPane.add(btnPesquisa);
	}
}
