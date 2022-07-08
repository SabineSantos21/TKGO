package interfaces;
import java.sql.ResultSet;

import classes.Encomenda;

public interface IEncomenda {
	public Encomenda createEncomenda(Encomenda novaEncomenda);
	public ResultSet getEncomendas();
	public Encomenda getEncomenda(String idEncomenda);
	public Encomenda updateEncomenda(String idEncomenda, Encomenda encomenda);
	public void deleteEncomenda(String idEncomenda);
}
