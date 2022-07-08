package telas_editar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Motoboy;
import telas_pesquisar.Pesquisar_Cliente;
import telas_pesquisar.Pesquisar_Motoboy;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Editar_Motoboy extends JFrame {

	private JPanel contentPane;
	private JTextField inputIdMotoboy;
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
					Editar_Motoboy frame = new Editar_Motoboy();
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
	public Editar_Motoboy() {
		setTitle("Editar Motoboy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar Motoboy");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 10, 89, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(10, 33, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		inputIdMotoboy = new JTextField();
		inputIdMotoboy.setBounds(33, 30, 123, 19);
		contentPane.add(inputIdMotoboy);
		inputIdMotoboy.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Motoboy motoboy = new Motoboy("", "", "");
					var getMotoboy = motoboy.getMotoboy(inputIdMotoboy.getText());
					
					inputNome.setText(getMotoboy.Pessoa.Nome);
					inputEmail.setText(getMotoboy.Pessoa.Email);
					inputTelefone.setText(getMotoboy.Pessoa.Telefone);	
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnPesquisar.setBounds(166, 29, 123, 21);
		contentPane.add(btnPesquisar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 59, 395, -1);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 56, 416, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("Motoboy Encontrado");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 68, 134, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 91, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		inputNome = new JTextField();
		inputNome.setBounds(10, 114, 395, 27);
		contentPane.add(inputNome);
		inputNome.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 151, 70, 13);
		contentPane.add(lblNewLabel_4);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(10, 174, 395, 27);
		contentPane.add(inputEmail);
		inputEmail.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Telefone");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 211, 70, 13);
		contentPane.add(lblNewLabel_5);
		
		inputTelefone = new JTextField();
		inputTelefone.setBounds(10, 234, 395, 29);
		contentPane.add(inputTelefone);
		inputTelefone.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Motoboy exibir = new Pesquisar_Motoboy();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 281, 85, 21);
		contentPane.add(btnVoltar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Motoboy motoboy = new Motoboy(inputNome.getText(), inputEmail.getText(), inputTelefone.getText());
					motoboy.updateMotoboy(inputIdMotoboy.getText(), motoboy);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(166, 281, 85, 21);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Motoboy motoboy = new Motoboy(inputNome.getText(), inputEmail.getText(), inputTelefone.getText());
					motoboy.deleteMotoboy(inputIdMotoboy.getText());
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setBounds(320, 281, 85, 21);
		contentPane.add(btnDeletar);
	}
}
