package interfaces;

import java.sql.ResultSet;

import classes.Cliente;

public interface ICliente {
	public Cliente createCliente(Cliente novoCliente);
	public ResultSet getClientes();
	public Cliente getCliente(String idClientes);
	public Cliente updateCliente(String idCliente, Cliente cliente);
	public void deleteCliente(String idCliente);
	public Cliente getClienteByName(String nome);
}
