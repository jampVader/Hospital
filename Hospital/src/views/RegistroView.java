package views;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.UsuarioDAO;
import models.Usuario;

public class RegistroView extends JDialog {


	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JTextField tfUserName;
	private JPasswordField passwordField;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JLabel lblTitulo;
	private JPasswordField tFRepitePassword;
	private JButton btnRegistro;
	private JButton btnVolver;
	private JLabel lblRepitePassword;
	private UsuarioDAO nuevoUsuarioDAO;
	private JFrame framePadre;



	/**
	 * Create the application. Pasamos el frame del padre para volver a esa pantalla
	 */
	public RegistroView(JFrame loginView) {
		initialize();
		this.nuevoUsuarioDAO = new UsuarioDAO();
		this.framePadre = loginView;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		colocarComponentesUI();
		setListener();
		
		//Esto siempre lo último.
		frame.setVisible(true);
	}
	
	private void colocarComponentesUI() {
		
		lblTitulo = new JLabel("Registro nuevo usuario");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 26, 414, 34);
		frame.getContentPane().add(lblTitulo);
		
		lblUserName = new JLabel("Usuario");
		lblUserName.setBounds(115, 74, 69, 14);
		frame.getContentPane().add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(212, 71, 86, 20);
		frame.getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(115, 113, 69, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(212, 110, 86, 20);
		frame.getContentPane().add(passwordField);
		
		btnRegistro = new JButton("Registrar");
		btnRegistro.setBounds(127, 191, 89, 23);
		frame.getContentPane().add(btnRegistro);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(226, 191, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		lblRepitePassword = new JLabel("Repetir password");
		lblRepitePassword.setBounds(115, 138, 89, 14);
		frame.getContentPane().add(lblRepitePassword);
		
		tFRepitePassword = new JPasswordField();
		tFRepitePassword.setBounds(212, 135, 86, 20);
		frame.getContentPane().add(tFRepitePassword);

	}
	
	private void setListener() {
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Recogemos los valores del usuario y contraseñas introducidas
				String usuario = tfUserName.getText();
				String password = new String(passwordField.getPassword());
				String passwordRepite = new String(tFRepitePassword.getPassword());
				
				// Si las dos contraseñas coinciden, comprobamos si el usuario ya existe, si
				// no es así se crea y abrimos la pantalla de login de nuevo para que se loguee
				
				if (password.equals(passwordRepite)) {
					// Comprobamos si el usuario ya existe, si la llamada al método retorna true
					if(nuevoUsuarioDAO.comprobarUsuario(usuario)) {
						// Error ya existe el usuario
						JOptionPane.showMessageDialog(frame, "El usuario ya está registrado", "Error, datos incorrectos", JOptionPane.ERROR_MESSAGE);
					}else {
						// el usuario no existe, lo creamos
						nuevoUsuarioDAO.registrarnuevoUsuario(new Usuario(usuario, password));
						JOptionPane.showMessageDialog(frame, "Usuario registrado", "Datos correctos", JOptionPane.INFORMATION_MESSAGE);
						// Llamamos a la ventana del login y cerramos la actual
						framePadre.setVisible(true);
						frame.dispose();
					}
				}else {
					// No coinciden las contraseñas
					JOptionPane.showMessageDialog(frame, "Las contraseñas introducidas no coinciden.", "Error, datos incorrectos", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Llamamos a la ventana del login y cerramos la actual
				framePadre.setVisible(true);
				frame.dispose();
			}
		});
		
	}
}
