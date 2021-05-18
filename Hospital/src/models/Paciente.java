/**
 * 
 */
package models;

/**
 * @author Jose Antonio Martos Pozo
 *
 */
public class Paciente {

	private String DNI;
	private String Nombre;
	private String apellidos;
	private String enfermedad;
	
	public Paciente(String dNI, String nombre, String apellidos, String enfermedad) {
		DNI = dNI;
		Nombre = nombre;
		this.apellidos = apellidos;
		this.enfermedad = enfermedad;
	}

	/**
	 * @return the dNI
	 */
	public String getDNI() {
		return DNI;
	}

	/**
	 * @param dNI the dNI to set
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the enfermedad
	 */
	public String getEnfermedad() {
		return enfermedad;
	}

	/**
	 * @param enfermedad the enfermedad to set
	 */
	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	@Override
	public String toString() {
		return "Paciente [DNI=" + DNI + ", Nombre=" + Nombre + ", apellidos=" + apellidos + ", enfermedad=" + enfermedad
				+ "]";
	}
	
	
	
}
