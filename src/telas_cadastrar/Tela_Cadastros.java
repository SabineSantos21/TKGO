package telas_cadastrar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import telas_pesquisar.Pesquisar_Cliente;
import telas_pesquisar.Pesquisar_Encomenda;
import telas_pesquisar.Pesquisar_Motoboy;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Cadastros extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Cadastros frame = new Tela_Cadastros();
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
	public Tela_Cadastros() {
		setTitle("Cadastros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastros");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 158, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Cliente exibir = new Pesquisar_Cliente();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCliente.setBounds(27, 61, 158, 48);
		contentPane.add(btnCliente);
		
		JButton btnMotoboy = new JButton("Motoboy");
		btnMotoboy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Motoboy exibir = new Pesquisar_Motoboy();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnMotoboy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMotoboy.setBounds(210, 61, 158, 48);
		contentPane.add(btnMotoboy);
		
		JButton btnEncomenda = new JButton("Encomenda");
		btnEncomenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Encomenda exibir = new Pesquisar_Encomenda();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnEncomenda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEncomenda.setBounds(391, 61, 158, 48);
		contentPane.add(btnEncomenda);
	}

}
