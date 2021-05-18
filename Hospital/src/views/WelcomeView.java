/**
 * 
 */
package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.PacienteDAO;
import models.Paciente;
import models.TipoAcceso;

/**
 * @author Jose Antonio Martos Pozo
 *
 */
public class WelcomeView {

	private JFrame frame;
	private JTable tablePacientes;
	private DefaultTableModel modeloTabla;
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private JScrollPane scrollPane;
	

	/**
	 * Create the application.
	 */
	public WelcomeView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 629, 348);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		colocarComponentesUI();
		setListeners();
		
		//Esto siempre lo último.
		frame.setVisible(true);

	}
	
	private void colocarComponentesUI() {
		
		tablePacientes = new JTable();
		// Añade al modelo de la tabla una nueva fila con los distintos campos
		modeloTabla = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"DNI", "Nombre", "Apellidos", "Enfermedad"
				})
			{
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
    		}; 
		
   		cargarModelo();
		
		// Añado un JScrollPane para que se muestren las columnas
		scrollPane = new JScrollPane(tablePacientes);
		scrollPane.setBounds(10, 49, 593, 249);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);		
	}
	
	private void setListeners() {
	    // Muestra la ficha del show seleccionado de la tabla, si ha seleccionado alguno
	    tablePacientes.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getClickCount()> 1){
					// Creamos el paciente y llamamos a Crear_Modificar_Borrar
	    			Paciente pac = new Paciente((String) modeloTabla.getValueAt(tablePacientes.getSelectedRow(),0), 
	    										(String) modeloTabla.getValueAt(tablePacientes.getSelectedRow(),1), 
	    										(String) modeloTabla.getValueAt(tablePacientes.getSelectedRow(),2), 
	    										(String) modeloTabla.getValueAt(tablePacientes.getSelectedRow(),3));
	    			new Crear_Modificar_Borrar(TipoAcceso.MODIFICAR,pac, frame);
	    			cargarModelo();
	    			
	    		}
	    	}
	    });
	}
	
	private void cargarModelo() {
		// Creamos la accion a realizar con los datos en el modelo de la tabla
		Consumer<? super Paciente> accion;
		// Limpiamos el modelo de la tabla y lo cargamos de nuevo
		modeloTabla.setRowCount(0);
		accion = p-> modeloTabla.addRow(new Object[] {p.getDNI(), p.getNombre(), p.getApellidos(), p.getEnfermedad()});
		
		// Cargamos en el modelo los pacientes que haya en la tabla de la BBDD
		ArrayList<Paciente> listaPacientes = pacienteDAO.seleccionar();
		listaPacientes.stream().forEach(accion);
		
		// Aplicamos el modelo a la JTable
		tablePacientes.setModel(modeloTabla);	

	}
}
