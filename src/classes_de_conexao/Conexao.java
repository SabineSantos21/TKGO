package classes_de_conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection db_connect() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_tkgo", "tkgo", "tkgo22Dev");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
	}
	
	
}
