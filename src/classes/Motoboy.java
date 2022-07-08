package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import classes_de_conexao.Conexao;
import interfaces.IMotoboy;

public class Motoboy implements IMotoboy {
	public Pessoa Pessoa = new Pessoa();
	public String IdMotoboy;
	
	public Motoboy(String Nome, String Email, String Telefone) {
		Pessoa.Nome = Nome;
		Pessoa.Email = Email;
		Pessoa.Telefone = Telefone;
	}

	@Override
	public Motoboy createMotoboy(Motoboy novoMotoboy) {
		if(novoMotoboy.Pessoa.Nome.equals("") || novoMotoboy.Pessoa.Email.equals("") || novoMotoboy.Pessoa.Telefone.equals("")) {
			JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
		} else {
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "INSERT INTO tb_motoboy (Nome, Email, Telefone) VALUE (? , ? , ?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, novoMotoboy.Pessoa.Nome);
				stmt.setString(2, novoMotoboy.Pessoa.Email);
				stmt.setString(3, novoMotoboy.Pessoa.Telefone);
				
				stmt.execute();
				
				stmt.close();
				con.close();
				JOptionPane.showMessageDialog(null, "Motoboy criado com Sucesso!");
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Erro ao criar Motoboy " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public ResultSet getMotoboys() {
		
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_motoboy";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro ao Buscar Motoboys " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Motoboy getMotoboy(String idMotoboy) {
		
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_motoboy WHERE idMotoboy like ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, idMotoboy);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Motoboy motoboy = new Motoboy(rs.getString("Nome"), rs.getString("Email"), rs.getString("Telefone"));
				return motoboy;
			}
			
			JOptionPane.showMessageDialog(null, "Motoboy não encontrado");
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Motoboy não encontrado " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Motoboy updateMotoboy(String idMotoboy, Motoboy motoboy) {
		
		if(idMotoboy.equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o Id do Motoboy");
		} else {
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "UPDATE tb_motoboy SET Nome= ? , Email= ?, Telefone = ? WHERE IdMotoboy = ?";
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, motoboy.Pessoa.Nome);
				stmt.setString(2, motoboy.Pessoa.Email);
				stmt.setString(3, motoboy.Pessoa.Telefone);
				stmt.setString(4, idMotoboy);
				
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Motoboy Atualizado com sucesso");
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Error ao Atualizar Motoboy " + e.getMessage());
			}
		}
		
		return null;
	}

	@Override
	public void deleteMotoboy(String idMotoboy) {
		// TODO Auto-generated method stub
		
		if(idMotoboy.equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o Id do Motoboy");
		} else {
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "DELETE FROM tb_motoboy WHERE IdMotoboy = ?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, idMotoboy);
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Motoboy Deletado com sucesso!");
				
			} catch (Exception e) {				
				if(e instanceof SQLIntegrityConstraintViolationException) {
					JOptionPane.showMessageDialog(null, "Erro ao Deletar Motoboy. Existe uma encomenda ligada a esse motoboy.");
					
			    } else {
					JOptionPane.showMessageDialog(null, "Erro ao Deletar Motoboy " + e.getMessage());
					e.printStackTrace();
			    }
			}
		}		
	}

	@Override
	public Motoboy getMotoboyByNome(String nome) {
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_motoboy WHERE Nome like ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, nome);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Motoboy motoboy = new Motoboy(rs.getString("Nome"), rs.getString("Email"), rs.getString("Telefone"));
				motoboy.IdMotoboy = rs.getString("IdMotoboy");
				return motoboy;
			}
			
			JOptionPane.showMessageDialog(null, "Motoboy não encontrado");
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Motoboy não encontrado " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
}
