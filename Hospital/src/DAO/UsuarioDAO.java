package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Usuario;

/**
 * Clase de acceso a datos de la clase Usuario<br>
 * Hereda de la clase abstracta AbstractDAO que implementa el<br>
 * método de conexión a la BBDD "pokemondexbbdd'
 * @author Jose Antonio Martos Pozo
 *
 */
public class UsuarioDAO extends AbstractDAO{

	/**
	 * Método que comprueba que el usuario pasado existe en la base de datos
	 * @param username Cadena de texto con el nombre del usuario a comprobar
	 */
	
	public boolean comprobarUsuario(String username) {
		
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM usuario " + "WHERE idUsuario = '" + username + "'";
			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			// Esto solo ocurre hay error o no existe
			return false;
		}

	}
	
	public void registrarnuevoUsuario(Usuario usuario) {
		
		PreparedStatement preparedStatement;
		String insert="INSERT INTO usuario VALUES (?,?);";
		
		try {
			
			preparedStatement = conn.prepareStatement(insert);
			preparedStatement.setString(1, usuario.getIdUsuario());
			preparedStatement.setString(2, usuario.getPassword());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
