package telas_cadastrar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Cliente;
import classes.Encomenda;
import classes.Motoboy;
import enums.Status_Encomenda;
import telas_pesquisar.Pesquisar_Cliente;
import telas_pesquisar.Pesquisar_Encomenda;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Tela_Cadastro_Encomenda extends JFrame {

	private JPanel contentPane;
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
					Tela_Cadastro_Encomenda frame = new Tela_Cadastro_Encomenda();
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
	public Tela_Cadastro_Encomenda() {
		setTitle("Cadastro de Encomenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nova Encomenda");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 212, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 36, 80, 13);
		contentPane.add(lblNewLabel_1);
		
		JTextArea inputDescricao = new JTextArea();
		inputDescricao.setBounds(10, 59, 416, 38);
		contentPane.add(inputDescricao);
		
		JLabel lblNewLabel_2 = new JLabel("Motoboy");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 107, 80, 13);
		contentPane.add(lblNewLabel_2);
		
		JComboBox selectMotoboy = new JComboBox();
		selectMotoboy.setBounds(10, 130, 416, 27);
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
		
		JLabel lblNewLabel_3 = new JLabel("Cliente");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 167, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JComboBox selectCliente = new JComboBox();
		selectCliente.setBounds(10, 190, 416, 27);
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
		
		JLabel lblNewLabel_4 = new JLabel("Status");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 227, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		JComboBox selectStatus = new JComboBox();
		selectStatus.setBounds(10, 250, 416, 27);
		contentPane.add(selectStatus);
		
		selectStatus.setModel(new DefaultComboBoxModel<>(Status_Encomenda.values()));
		
		JLabel lblNewLabel_5 = new JLabel("Endere\u00E7o de Coleta");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 287, 130, 13);
		contentPane.add(lblNewLabel_5);
		
		inputEnderecoColeta = new JTextField();
		inputEnderecoColeta.setBounds(10, 310, 416, 27);
		contentPane.add(inputEnderecoColeta);
		inputEnderecoColeta.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Endere\u00E7o de Entrega");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 347, 130, 13);
		contentPane.add(lblNewLabel_6);
		
		inputEnderecoEntrega = new JTextField();
		inputEnderecoEntrega.setBounds(10, 365, 416, 27);
		contentPane.add(inputEnderecoEntrega);
		inputEnderecoEntrega.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar_Encomenda exibir = new Pesquisar_Encomenda();
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnVoltar.setBounds(10, 460, 85, 21);
		contentPane.add(btnVoltar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputDescricao.setText("");
				inputDtEntrega.setText("");
				inputDtColeta.setText("");
				inputEnderecoColeta.setText("");
				inputEnderecoEntrega.setText("");
			}
		});
		btnLimpar.setBounds(166, 460, 85, 21);
		contentPane.add(btnLimpar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					Encomenda encomenda = new Encomenda(inputDescricao.getText(), selectMotoboy.getSelectedItem().toString(), selectCliente.getSelectedItem().toString(), inputEnderecoColeta.getText(), inputEnderecoEntrega.getText(), inputDtColeta.getText(), inputDtEntrega.getText(), selectStatus.getSelectedItem().toString());
					encomenda.createEncomenda(encomenda);
					
					inputDescricao.setText("");
					inputDtEntrega.setText("");
					inputDtColeta.setText("");
					inputEnderecoColeta.setText("");
					inputEnderecoEntrega.setText("");
					
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(341, 460, 85, 21);
		contentPane.add(btnSalvar);
		
		JLabel lblNewLabel_7 = new JLabel("Data da Coleta");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 402, 95, 13);
		contentPane.add(lblNewLabel_7);
		
		inputDtColeta = new JTextField();
		inputDtColeta.setToolTipText("dd/MM/yyyy");
		inputDtColeta.setBounds(10, 417, 198, 27);
		contentPane.add(inputDtColeta);
		inputDtColeta.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Data da Entrega");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(227, 402, 105, 13);
		contentPane.add(lblNewLabel_8);
		
		inputDtEntrega = new JTextField();
		inputDtEntrega.setToolTipText("dd/MM/yyyy");
		inputDtEntrega.setBounds(228, 417, 198, 27);
		contentPane.add(inputDtEntrega);
		inputDtEntrega.setColumns(10);
	}
}
