package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.JOptionPane;

import classes_de_conexao.Conexao;
import enums.Status_Encomenda;
import interfaces.IEncomenda;

public class Encomenda implements IEncomenda {
	public String IdEncomenda;
	public String Codigo;
	public String Descricao;
	public int IdMotoboy;
	public String NomeMotoboy;
	public int IdCliente;
	public String NomeCliente;
	public String EnderecoColeta;
	public String EnderecoEntrega;
	public String StatusEncomenda;
	public String DtColeta;
	public String DtEntrega;
	
	public Encomenda(String descricao, String nomeMotoboy, String nomeCliente, String enderecoColeta, String enderecoEntrega, String dtColeta, String dtEntrega, String status) {
		Descricao = descricao;
		NomeMotoboy = nomeMotoboy;
		NomeCliente = nomeCliente;
		EnderecoColeta = enderecoColeta;
		EnderecoEntrega = enderecoEntrega;
		DtColeta = dtColeta;
		DtEntrega = dtEntrega;
		StatusEncomenda = status;
		
	}
	
	@Override
	public Encomenda createEncomenda(Encomenda novaEncomenda) {
		
		if(novaEncomenda.Descricao.equals("") || novaEncomenda.EnderecoColeta.equals("") || novaEncomenda.EnderecoEntrega.equals("")) {
			JOptionPane.showMessageDialog(null, "Descrição, Endereço da Coleta e Endereço da Entrega são campos obrigatórios.");
		} else {			
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "INSERT INTO tb_encomenda (Codigo, Descricao, IdMotoboy, IdCliente, StatusEncomenda, EnderecoColeta, EnderecoEntrega, DtColeta, DtEntrega) VALUE (? , ? , ?, ?, ?, ?, ?, ?, ?)";
				
				Random gerador = new Random();
				int numCod = gerador.nextInt(1000);
				novaEncomenda.Codigo = "TKGO-" + numCod;
				
				Motoboy motoboy = getMotoboy(novaEncomenda.NomeMotoboy);
				novaEncomenda.IdMotoboy = Integer.parseInt(motoboy.IdMotoboy);
				
				Cliente cliente = getCliente(novaEncomenda.NomeCliente);
				novaEncomenda.IdCliente = Integer.parseInt(cliente.IdCliente);
				
				Status_Encomenda statusEncomenda = Status_Encomenda.valueOf(novaEncomenda.StatusEncomenda);
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, novaEncomenda.Codigo);
				stmt.setString(2, novaEncomenda.Descricao);
				stmt.setInt(3, novaEncomenda.IdMotoboy);
				stmt.setInt(4, novaEncomenda.IdCliente);
				stmt.setInt(5, statusEncomenda.getValue()) ;
				stmt.setString(6, novaEncomenda.EnderecoColeta);
				stmt.setString(7, novaEncomenda.EnderecoEntrega);
				stmt.setString(8, novaEncomenda.DtColeta);
				stmt.setString(9, novaEncomenda.DtEntrega);
				
				stmt.execute();
				
				stmt.close();
				con.close();
				JOptionPane.showMessageDialog(null, "Encomenda criada com Sucesso!");
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Erro ao criar Encomenda " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public Motoboy getMotoboy(String nome) {
		Motoboy motoboy = new Motoboy("", "", "");
		return motoboy.getMotoboyByNome(nome);
	}
	
	public Cliente getCliente(String nome) {
		Cliente cliente = new Cliente("", "", "");
		return cliente.getClienteByName(nome);
	}
	
	@Override
	public ResultSet getEncomendas() {
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_encomenda";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro ao Buscar Encomendas " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Encomenda getEncomenda(String idEncomenda) {
		try {
			Connection con = Conexao.db_connect();
			
			String sql = "SELECT * FROM tb_encomenda WHERE idEncomenda like ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, idEncomenda);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Encomenda encomenda = new Encomenda(rs.getString("Descricao"), "", "", rs.getString("EnderecoColeta"), rs.getString("EnderecoEntrega"), rs.getString("DtColeta"), rs.getString("DtEntrega"), rs.getString("StatusEncomenda"));
				encomenda.IdEncomenda = rs.getString("IdEncomenda");
				encomenda.IdMotoboy = rs.getInt("IdMotoboy");
				encomenda.IdCliente = rs.getInt("IdCliente");
				encomenda.Codigo = rs.getString("Codigo");
				
				Cliente cliente = new Cliente("", "", "");
				Cliente getCliente = cliente.getCliente(rs.getString("IdCliente"));
				encomenda.NomeCliente = getCliente.Pessoa.Nome;
				
				Motoboy motoboy = new Motoboy("", "", "");
				Motoboy getMotoboy = motoboy.getMotoboy(rs.getString("IdMotoboy"));
				encomenda.NomeMotoboy = getMotoboy.Pessoa.Nome;

				return encomenda;
			}
			
			JOptionPane.showMessageDialog(null, "Encomenda não encontrado");
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Encomenda não encontrado " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Encomenda updateEncomenda(String idEncomenda, Encomenda encomenda) {
		
		if(idEncomenda.equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o Id da Encomenda");
		} else {
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "UPDATE tb_encomenda SET Descricao= ? , IdMotoboy= ?, IdCliente = ?, StatusEncomenda= ?, EnderecoColeta= ?, EnderecoEntrega= ?, DtColeta= ?, DtEntrega= ? WHERE IdEncomenda = ?";
				PreparedStatement stmt = con.prepareStatement(sql);
				
				Motoboy motoboy = getMotoboy(encomenda.NomeMotoboy);
				encomenda.IdMotoboy = Integer.parseInt(motoboy.IdMotoboy);
				
				Cliente cliente = getCliente(encomenda.NomeCliente);
				encomenda.IdCliente = Integer.parseInt(cliente.IdCliente);
				
				Status_Encomenda statusEncomenda = Status_Encomenda.valueOf(encomenda.StatusEncomenda);
				stmt.setString(1, encomenda.Descricao);
				stmt.setInt(2, encomenda.IdMotoboy);
				stmt.setInt(3, encomenda.IdCliente);
				stmt.setInt(4, statusEncomenda.getValue());
				stmt.setString(5, encomenda.EnderecoColeta);
				stmt.setString(6, encomenda.EnderecoEntrega);
				stmt.setString(7, encomenda.DtColeta);
				stmt.setString(8, encomenda.DtEntrega);
				stmt.setString(9, idEncomenda);

				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Encomenda Atualizado com sucesso");
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Error ao Atualizar Encomenda " + e.getMessage());

			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEncomenda(String idEncomenda) {
		// TODO Auto-generated method stub
		
		if(idEncomenda.equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o Id da Encomenda");
		} else {
			try {
				Connection con = Conexao.db_connect();
				
				String sql = "DELETE FROM tb_encomenda WHERE IdEncomenda = ?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, idEncomenda);
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Encomenda Deletada com sucesso!");
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Erro ao Deletar Encomenda " + e.getMessage());
				e.printStackTrace();
			}
		}		
	}
}
