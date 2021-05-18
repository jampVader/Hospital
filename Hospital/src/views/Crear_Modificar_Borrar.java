package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAO.PacienteDAO;
import models.Paciente;
import models.TipoAcceso;

public class Crear_Modificar_Borrar extends JDialog {

	private JFrame frame;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JLabel lbDNI;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblEnfermedad;
	private JTextArea textAEnfermedad;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JFrame parent;
	
	private Paciente paciente;
	private PacienteDAO pacienteDAO;
	
	
	private TipoAcceso tipo; 


	/**
	 * Create the dialog.
	 */
	public Crear_Modificar_Borrar(TipoAcceso t, Paciente p, JFrame parent) {
		this.parent = parent;
		this.tipo = t;
		this.paciente = p;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		colocarComponentesUI();
		setListeners();

		switch (t) {
		case INSERTAR: {
			frame.setTitle("Insertar Paciente");
			break;
			}
		case MODIFICAR: {
			frame.setTitle("Editar Paciente");
			break;
			}
		case BORRAR: {
			frame.setTitle("Borrar Paciente");
			break;
			}
		}
		
		frame.setVisible(true);
		
		
		switch (t) {
		case INSERTAR: {
			limpiarCampos();
			break;			
			}
		case MODIFICAR: {
			cargarDatos(this.paciente.getDNI());
			break;
			}
		case BORRAR: {
			cargarDatos(this.paciente.getDNI());
			// Ponemos los campos de salida
			textDNI.setEnabled(false);
			textNombre.setEnabled(false);
			textApellidos.setEnabled(false);
			textAEnfermedad.setEnabled(false);
			break;
			}
		}

		
	}
	
	
	private void colocarComponentesUI() {
		
		lbDNI = new JLabel("DNI");
		lbDNI.setBounds(46, 42, 46, 14);
		frame.getContentPane().add(lbDNI);
		
		textDNI = new JTextField();
		textDNI.setBounds(107, 39, 86, 20);
		frame.getContentPane().add(textDNI);
		textDNI.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 77, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(107, 74, 204, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(46, 112, 46, 14);
		frame.getContentPane().add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setBounds(107, 109, 294, 20);
		frame.getContentPane().add(textApellidos);
		textApellidos.setColumns(10);
		
		lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setBounds(46, 150, 58, 14);
		frame.getContentPane().add(lblEnfermedad);
		
		textAEnfermedad = new JTextArea();
		textAEnfermedad.setBounds(107, 145, 294, 85);
		frame.getContentPane().add(textAEnfermedad);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(312, 259, 89, 23);
		frame.getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(213, 259, 89, 23);
		frame.getContentPane().add(btnCancelar);
	}
	
	private void setListeners() {
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accionAceptar();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.setVisible(true); // Abrimos la pantalla padre
				frame.dispose(); //Eliminamos la pantalla de inserción
			}
		});
	}
	
	
	private void limpiarCampos() {
		textDNI.setText("");
		textNombre.setText("");
		textApellidos.setText("");
		textAEnfermedad.setText("");
	}
	
	private void cargarDatos(String dni) {
		
		pacienteDAO = new PacienteDAO();
		try {
			if (!dni.equals("")) {
				JOptionPane.showMessageDialog(null, dni);
				Paciente paciente = pacienteDAO.getPaciente(dni);
				if (paciente != null) {
					textDNI.setText(paciente.getDNI());
					textNombre.setText(paciente.getNombre());
					textApellidos.setText(paciente.getApellidos());
					textAEnfermedad.setText(paciente.getEnfermedad());
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "No se ha podido cargar los datos del paciente", 
					"Error al acceder a los datos", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void accionAceptar() {
		
		switch (tipo) {
		case INSERTAR: {
			
			Paciente nuevoPaciente = new Paciente(textDNI.getText(), textNombre.getText(), textApellidos.getText(), textAEnfermedad.getText());
			// Insertamos los datos en la BBDD
			pacienteDAO.insertar(nuevoPaciente);
			break;
			}
		case MODIFICAR: {
			
			paciente.setDNI(textDNI.getText());
			paciente.setNombre(textNombre.getText());
			paciente.setApellidos(textApellidos.getText());
			paciente.setEnfermedad(textAEnfermedad.getText());
			
			pacienteDAO.editar(paciente);
			break;
			}
		case BORRAR: {
			
			int opcion = JOptionPane.showConfirmDialog(parent, "¿Desea borrar el paciente de la BBDD?");
			if (opcion == 0) pacienteDAO.borrar(paciente);
			break;
			}
		}
	}
}
