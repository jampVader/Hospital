package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO extends AbstractDAO {

	public boolean login(String username, String password) {

		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM usuario " + "WHERE idUsuario = '" + username + "'" + " AND password = '"
					+ password + "'";
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		// Esto solo ocurre si peta la BD.
		return false;

	}

	
}
