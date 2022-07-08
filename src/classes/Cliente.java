package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import classes_de_conexao.Conexao;
import interfaces.ICliente;

public class Cliente implements ICliente {
	public Pessoa Pessoa = new Pessoa();
	public String IdCliente;
	
	public Cliente(String Nome, String Email , String Telefone) {
		Pessoa.Nome = Nome;
		Pessoa.Email = Email;
		Pessoa.Telefone = Telefone;
	}
	
	@Override
	public Cliente createCliente(Cliente novoCliente) {
		
		if(novoCliente.Pessoa.Nome.equals("") || novoCliente.Pessoa.Email.equals("") || novoCliente.Pessoa.Telefone.equals("")) {
			JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
		} else {
			try {
				Connection con = Conexao.db_connect();	
				
				String sql = "INSERT INTO tb_cliente (Nome, Email, Telefone) VALUE (? , ? , ?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, novoCliente.Pessoa.Nome);
				stmt.setString(2, novoCliente.Pessoa.Email);
				stmt.setString(3, novoCliente.Pessoa.Telefone);
				
				stmt.execute();
				
				stmt.close();
				con.close();
				JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!!");
				
				
			} catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao Criar Cliente " + ex.getMessage());
				ex.printStackTrace();
			}	
			
			return novoCliente;			
		}
		
		return null;
		
	}

	@Override
	public Cliente getCliente(String idCliente) {
		
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_cliente WHERE idCliente like ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, idCliente);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente(rs.getString("Nome"), rs.getString("Email"), rs.getString("Telefone")); 
				return cliente;				
			} 
			
			JOptionPane.showMessageDialog(null, "Cliente não encontrado");
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Cliente updateCliente(String idCliente, Cliente cliente) {
		
		if(idCliente.equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o Id do Cliente");
		} else {
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "UPDATE tb_cliente SET Nome= ? , Email= ?, Telefone = ? WHERE IdCliente = ?";
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, cliente.Pessoa.Nome);
				stmt.setString(2, cliente.Pessoa.Email);
				stmt.setString(3, cliente.Pessoa.Telefone);
				stmt.setString(4, idCliente);
				
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Cliente Atualizado com sucesso!");
								
			} catch(SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao Atualizar Cliente " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public void deleteCliente(String idCliente) {
		if(idCliente.equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o Id do Cliente");
		} else {
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "DELETE FROM tb_cliente WHERE IdCliente = ?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, idCliente);
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Cliente Deletado com sucesso!");
				
			} catch (SQLException e) {
				// TODO: handle exception
				if(e instanceof SQLIntegrityConstraintViolationException) {
			          // Handle Here
					JOptionPane.showMessageDialog(null, "Erro ao Deletar Cliente. Existe uma encomenda ligada a esse cliente.");
					
			    } else {
			    	JOptionPane.showMessageDialog(null, "Erro ao Deletar Cliente " + e.getMessage());			    	
			    	e.printStackTrace();
			    }
			}
		}		
	}

	@Override
	public ResultSet getClientes() {
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_cliente";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			return rs;
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro ao Buscar Clientes " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Cliente getClienteByName(String nome) {
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_cliente WHERE Nome like ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, nome);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente(rs.getString("Nome"), rs.getString("Email"), rs.getString("Telefone")); 
				cliente.IdCliente = rs.getString("IdCliente");
				return cliente;				
			} 
			
			JOptionPane.showMessageDialog(null, "Cliente não encontrado");
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
}
