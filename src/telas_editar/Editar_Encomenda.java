package telas_editar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import classes.Encomenda;
import classes.Motoboy;
import enums.Status_Encomenda;
import telas_pesquisar.Pesquisar_Encomenda;
import telas_pesquisar.Pesquisar_Motoboy;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Editar_Encomenda extends JFrame {

	private JPanel contentPane;
	private JTextField inputId;
	private JTextField inputCode;
	private JTextField inputEnderecoColeta;
	private JTextField inputEnderecoEntrega;
	private JTextField inputDtColeta;
	private JTextField inputDtEntrega;
	List<String> listaCliente = new ArrayList<String>();
	List<String> listaMotoboy = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editar_Encomenda frame = new Editar_Encomenda();
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
	public Editar_Encomenda() {
		setTitle("Buscar Encomenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar Encomenda");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 10, 116, 13);
		contentPane.add(lblNewLabel);
		
		JLabel Id = new JLabel("Id");
		Id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Id.setBounds(10, 38, 45, 13);
		contentPane.add(Id);
		
		inputId = new JTextField();
		inputId.setBounds(32, 33, 116, 25);
		contentPane.add(inputId);
		inputId.setColumns(10);
		inputCode = new JTextField();
		JTextArea inputDescricao = new JTextArea();
		JComboBox selectMotoboy = new JComboBox();
		JComboBox selectCliente = new JComboBox();
		JComboBox selectStatus = new JComboBox();
		inputEnderecoColeta = new JTextField();
		inputEnderecoEntrega = new JTextField();
		inputDtColeta = new JTextField();
		inputDtEntrega = new JTextField();
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Encomenda encomenda = new Encomenda("", "", "", "", "", "", "", "");
					var getEncomenda = encomenda.getEncomenda(inputId.getText());
					
					inputCode.setText(getEncomenda.Codigo);
					inputDescricao.setText(getEncomenda.Descricao);
					selectMotoboy.setSelectedItem(getEncomenda.NomeMotoboy);
					selectCliente.setSelectedItem(getEncomenda.NomeCliente);
					selectStatus.setSelectedItem(Status_Encomenda.valueOf(Integer.parseInt(getEncomenda.StatusEncomenda)).name());
					inputEnderecoColeta.setText(getEncomenda.EnderecoColeta);
					inputEnderecoEntrega.setText(getEncomenda.EnderecoEntrega);
					inputDtColeta.setText(getEncomenda.DtColeta);
					inputDtEntrega.setText(getEncomenda.DtEntrega);
					
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(157, 35, 116, 21);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 72, 416, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("Encomenda Encontrada");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 84, 150, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("C\u00F3digo");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 107, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		inputCode.setEditable(false);
		inputCode.setBounds(64, 101, 209, 25);
		contentPane.add(inputCode);
		inputCode.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 140, 66, 13);
		contentPane.add(lblNewLabel_4);
		
		inputDescricao.setBounds(10, 163, 416, 63);
		contentPane.add(inputDescricao);
		
		JLabel lblNewLabel_5 = new JLabel("Motoboy");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 236, 66, 13);
		contentPane.add(lblNewLabel_5);
		
		selectMotoboy.setEditable(true);
		selectMotoboy.setBounds(10, 259, 416, 25);
		contentPane.add(selectMotoboy);
		
		try {
			Motoboy motoboy = new Motoboy("", "", "");
			ResultSet rs = motoboy.getMotoboys();
			
			while(rs.next()) {
				listaMotoboy.add(rs.getString("Nome"));
			}			
			
			DefaultComboBoxModel defaultComboBoxMotoboy = new DefaultComboBoxModel(listaMotoboy.toArray());
			selectMotoboy.setModel(defaultComboBoxMotoboy);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JLabel lblNewLabel_6 = new JLabel("Cliente");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 294, 66, 13);
		contentPane.add(lblNewLabel_6);
		
		selectCliente.setEditable(true);
		selectCliente.setBounds(10, 317, 416, 25);
		contentPane.add(selectCliente);
		
		try {
			Cliente cliente = new Cliente("", "", "");
			ResultSet rs = cliente.getClientes();
			
			while(rs.next()) {
				listaCliente.add(rs.getString("Nome"));
			}			
			
			DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(listaCliente.toArray());
			selectCliente.setModel(defaultComboBox);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_7 = new JLabel("Status");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 352, 66, 13);
		contentPane.add(lblNewLabel_7);
		
		selectStatus.setEditable(true);
		selectStatus.setBounds(10, 374, 416, 25);
		contentPane.add(selectStatus);
		
		selectStatus.setModel(new DefaultComboBoxModel<>(Status_Encomenda.values()));
		
		JLabel lblNewLabel_8 = new JLabel("Endere\u00E7o de Coleta");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(10, 409, 127, 13);
		contentPane.add(lblNewLabel_8);
		
		inputEnderecoColeta.setBounds(10, 426, 416, 25);
		contentPane.add(inputEnderecoColeta);
		inputEnderecoColeta.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Endere\u00E7o de Entrega");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(10, 461, 127, 13);
		contentPane.add(lblNewLabel_9);
		
		inputEnderecoEntrega.setBounds(10, 484, 416, 25);
		contentPane.add(inputEnderecoEntrega);
		inputEnderecoEntrega.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Date de Coleta");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(10, 519, 100, 13);
		contentPane.add(lblNewLabel_10);
		
		inputDtColeta.setBounds(10, 535, 203, 25);
		contentPane.add(inputDtColeta);
		inputDtColeta.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Data de Entrega");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(223, 519, 110, 13);
		contentPane.add(lblNewLabel_11);
		
		inputDtEntrega.setBounds(223, 535, 203, 25);
		contentPane.add(inputDtEntrega);
		inputDtEntrega.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Encomenda exibir = new Pesquisar_Encomenda();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 589, 85, 21);
		contentPane.add(btnVoltar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Encomenda encomenda = new Encomenda(inputDescricao.getText(), selectMotoboy.getSelectedItem().toString(), selectCliente.getSelectedItem().toString(), inputEnderecoColeta.getText(), inputEnderecoEntrega.getText(), inputDtColeta.getText(), inputDtEntrega.getText(), selectStatus.getSelectedItem().toString());
					encomenda.updateEncomenda(inputId.getText(), encomenda);
					
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(188, 589, 85, 21);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Encomenda encomenda = new Encomenda(inputDescricao.getText(), selectMotoboy.getSelectedItem().toString(), selectStatus.getSelectedItem().toString(), inputEnderecoColeta.getText(), inputEnderecoEntrega.getText(), inputDtColeta.getText(), inputDtEntrega.getText(), selectStatus.getSelectedItem().toString());
					encomenda.deleteEncomenda(inputId.getText());
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnDeletar.setForeground(Color.WHITE);
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setBounds(341, 589, 85, 21);
		contentPane.add(btnDeletar);
	}
}
