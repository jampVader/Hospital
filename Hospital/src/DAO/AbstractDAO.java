package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {
	
	protected Connection conn;
	
	public AbstractDAO() {
		try {
			// Le añadimos ?serverTimezone=UTC para indicar la zona horaria y no cambiarlo en el Servidor de la BBDD
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/hospital?serverTimezone=UTC", "root",
					"123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
