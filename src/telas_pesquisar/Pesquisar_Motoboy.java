package telas_pesquisar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Cliente;
import classes.Motoboy;
import telas_cadastrar.Tela_Cadastro_Cliente;
import telas_cadastrar.Tela_Cadastro_Motoboy;
import telas_cadastrar.Tela_Cadastros;
import telas_editar.Editar_Cliente;
import telas_editar.Editar_Motoboy;

public class Pesquisar_Motoboy extends JFrame {

	private JPanel contentPane;
	private JTable TbMotoboys;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisar_Motoboy frame = new Pesquisar_Motoboy();
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
	public Pesquisar_Motoboy() {
		setTitle("Motoboys");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Motoboys  Cadastrados");
		lblNewLabel.setBounds(10, 10, 170, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		
		JButton btnBuscar = new JButton("Buscar Motoboy");
		btnBuscar.setBounds(10, 39, 170, 21);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Motoboy motoboy = new Motoboy("", "", "");
					ResultSet rs = motoboy.getMotoboys();
					
					DefaultTableModel modelo = (DefaultTableModel) TbMotoboys.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						modelo.addRow(new Object[] {rs.getString("idMotoboy"), rs.getString("Nome"), rs.getString("Email"), rs.getString("Telefone")});
					}
					
					rs.close();
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 416, 119);
		contentPane.add(scrollPane);
		
		TbMotoboys = new JTable();
		TbMotoboys.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Email", "Telefone"
			}
		));
		scrollPane.setViewportView(TbMotoboys);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastros exibir = new Tela_Cadastros();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 232, 115, 21);
		contentPane.add(btnVoltar);
		
		JButton btnNovo = new JButton("Novo Motoboy");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastro_Motoboy exibir = new Tela_Cadastro_Motoboy();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNovo.setBounds(135, 232, 144, 21);
		contentPane.add(btnNovo);
		
		JButton btnEditar = new JButton("Editar Motoboy");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar_Motoboy exibir = new Editar_Motoboy();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnEditar.setBounds(298, 232, 128, 21);
		contentPane.add(btnEditar);
	}
}
