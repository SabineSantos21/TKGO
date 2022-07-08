package telas_pesquisar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Cliente;
import classes.Encomenda;
import classes.Motoboy;
import enums.Status_Encomenda;
import telas_cadastrar.Tela_Cadastro_Encomenda;
import telas_cadastrar.Tela_Cadastro_Motoboy;
import telas_cadastrar.Tela_Cadastros;
import telas_editar.Editar_Encomenda;
import telas_editar.Editar_Motoboy;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Pesquisar_Encomenda extends JFrame {

	private JPanel contentPane;
	private JTable TbEncomendas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisar_Encomenda frame = new Pesquisar_Encomenda();
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
	public Pesquisar_Encomenda() {
		setTitle("Encomendas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Encomendas Cadastradas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 196, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnBuscar = new JButton("Buscar Encomendas");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Encomenda encomenda = new Encomenda("", "", "", "", "", "", "", "");
					ResultSet rs = encomenda.getEncomendas();
					
					DefaultTableModel modelo = (DefaultTableModel) TbEncomendas.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						Cliente cliente = new Cliente("", "", "");
						Cliente getCliente = cliente.getCliente(rs.getString("IdCliente"));
						
						Motoboy motoboy = new Motoboy("", "", "");
						Motoboy getMotoboy = motoboy.getMotoboy(rs.getString("IdMotoboy"));
								
						modelo.addRow(new Object[] {rs.getString("IdEncomenda"), getCliente.Pessoa.Nome, getMotoboy.Pessoa.Nome, Status_Encomenda.valueOf(rs.getInt("StatusEncomenda"))});
					}
					
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnBuscar.setBounds(10, 33, 196, 21);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastros exibir = new Tela_Cadastros();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 221, 107, 21);
		contentPane.add(btnVoltar);
		
		JButton btnNewButton = new JButton("Nova Encomenda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastro_Encomenda exibir = new Tela_Cadastro_Encomenda();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnNewButton.setBounds(137, 221, 138, 21);
		contentPane.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar Encomenda");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar_Encomenda exibir = new Editar_Encomenda();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnEditar.setBounds(285, 221, 141, 21);
		contentPane.add(btnEditar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 416, 116);
		contentPane.add(scrollPane);
		
		TbEncomendas = new JTable();
		TbEncomendas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IdEncomenda", "Cliente", "Motoboy", "Status"
			}
		));
		scrollPane.setViewportView(TbEncomendas);
	}
}
