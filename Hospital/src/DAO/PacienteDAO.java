package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Paciente;

/**
 * 
 * @author Jose Antonio Martos Pozo
 *
 */
public class PacienteDAO extends AbstractDAO {
	
	public void insertar(Paciente paciente) {
		PreparedStatement pstmt;
		String sql = "Insert into paciente values('?','?','?','?')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paciente.getDNI());
			pstmt.setString(2, paciente.getNombre());
			pstmt.setString(3, paciente.getApellidos());
			pstmt.setString(4, paciente.getEnfermedad());
			pstmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println("Error al insertar: " + e.getMessage());
		}
	}

	public void editar(Paciente paciente) {
		PreparedStatement pstmt;
		String sql = "update paciente set nombre = '?', apellidos = '?', enfermedad = '?'"
				+ " Where dni = '?'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paciente.getNombre());
			pstmt.setString(2, paciente.getApellidos());
			pstmt.setString(3, paciente.getEnfermedad());
			pstmt.setString(4, paciente.getDNI());
			pstmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println("Error al actualizar: " + e.getMessage());
		}
	}
	
	public void borrar(Paciente paciente) {
		PreparedStatement pstmt;
		String sql = "delete from paciente Where dni = '?'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paciente.getDNI());
			pstmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println("Error al borrar: " + e.getMessage());
		}
	}
	
	public ArrayList<Paciente> seleccionar(){
		ArrayList<Paciente> listaPacientes = new ArrayList<Paciente>();
		Paciente paciente;
		
		PreparedStatement pstmt;
		String sql = "select * from paciente";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			
			while (rst.next()) {
				paciente = new Paciente(rst.getString("DNI"), rst.getString("Nombre"), rst.getString("Apellidos"), rst.getString("Enfermedad"));
				listaPacientes.add(paciente);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al recuperar pacientes: " + e.getMessage());
		}
		
		
		return listaPacientes;
		
	}
	
	public Paciente getPaciente(String dni) {
		Paciente paciente=null;
		
		PreparedStatement pstmt;
		String sql = "select * from paciente where dni = '" + dni + "'";
		//System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery(sql);
			
			if(rst.next()) {
				paciente = new Paciente(rst.getString("DNI"), rst.getString("Nombre"), rst.getString("Apellidos"), rst.getString("Enfermedad"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al recuperar paciente: " + e.getMessage());
		}

		
		return paciente;
		
	}

	
}
