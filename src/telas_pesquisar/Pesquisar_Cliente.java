package telas_pesquisar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Cliente;
import telas_cadastrar.Tela_Cadastro_Cliente;
import telas_cadastrar.Tela_Cadastros;
import telas_editar.Editar_Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pesquisar_Cliente extends JFrame {

	private JPanel contentPane;
	private JTable TbClientes;
	private JButton btnBuscar;
	private JButton btnNovoCliente;
	private JButton btnEditar;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisar_Cliente frame = new Pesquisar_Cliente();
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
	public Pesquisar_Cliente() {
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Clientes Cadastrados");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 183, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 445, 107);
		contentPane.add(scrollPane);
		
		TbClientes = new JTable();
		TbClientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Email", "Telefone"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(TbClientes);
		
		btnBuscar = new JButton("Buscar Clientes");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Cliente cliente = new Cliente("", "", "");
					ResultSet rs = cliente.getClientes();
					
					DefaultTableModel modelo = (DefaultTableModel) TbClientes.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						modelo.addRow(new Object[] {rs.getString("idCliente"), rs.getString("Nome"), rs.getString("Email"), rs.getString("Telefone")});
					}
					
					rs.close();
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(10, 43, 144, 21);
		contentPane.add(btnBuscar);
		
		btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastro_Cliente exibir = new Tela_Cadastro_Cliente();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNovoCliente.setBounds(150, 220, 144, 21);
		contentPane.add(btnNovoCliente);
		
		btnEditar = new JButton("Editar Cliente");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar_Cliente exibir = new Editar_Cliente();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnEditar.setBounds(311, 220, 144, 21);
		contentPane.add(btnEditar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastros exibir = new Tela_Cadastros();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 220, 116, 21);
		contentPane.add(btnVoltar);
	}
}
