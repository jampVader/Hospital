package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import DAO.LoginDAO;

public class LoginView extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JTextField tfUserName;
	private JPasswordField passwordField;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JLabel lblRegistro;
	private JLabel lblTitulo;
	private LoginDAO loginDAO;

	
	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
		loginDAO = new LoginDAO();
	}


		/**
		 * Initialize the contents of the panel.
		 */
		private void initialize() {
			
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			frame.setTitle("Login usuario");
			frame.getContentPane().setLayout(null);

			
			colocarComponentesUI();
			setListeners();
			
			frame.setVisible(true);
		}
		
		private void colocarComponentesUI() {
			lblTitulo = new JLabel("Bienvenido");
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setBounds(10, 26, 414, 34);
			frame.getContentPane().add(lblTitulo);
			
			lblUserName = new JLabel("User Name");
			lblUserName.setBounds(115, 74, 69, 14);
			frame.getContentPane().add(lblUserName);
			
			tfUserName = new JTextField();
			tfUserName.setBounds(194, 71, 86, 20);
			frame.getContentPane().add(tfUserName);
			tfUserName.setColumns(10);
			
			lblPassword = new JLabel("Password");
			lblPassword.setBounds(115, 113, 69, 14);
			frame.getContentPane().add(lblPassword);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(194, 110, 86, 20);
			frame.getContentPane().add(passwordField);
			
			btnLogin = new JButton("Login");

			btnLogin.setBounds(191, 161, 89, 23);
			frame.getContentPane().add(btnLogin);
			
			lblRegistro = new JLabel("Nuevo usuario. Pulse aqu\u00ED para registrarse.");
			lblRegistro.setForeground(Color.BLUE);
			lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblRegistro.setBounds(96, 205, 278, 14);
			frame.getContentPane().add(lblRegistro);

		}

		
		private void setListeners() {
			btnLogin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					loginPressed();
				}
			});
			
			KeyAdapter comportamientoLogin = new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					//Si ha pulsado el enter
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						loginPressed();
					}
				}
			};
			
			passwordField.addKeyListener(comportamientoLogin);		
			tfUserName.addKeyListener(comportamientoLogin);
			
			// Nuevo registro
			lblRegistro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new RegistroView(frame);
					frame.dispose();
				}
			});
			
		}
		
		private void loginPressed() {
			String username = tfUserName.getText();
			String password = new String(passwordField.getPassword());
			
//			System.out.println("Usuario: " + username);
//			System.out.println("Password: " + password);
			
			if(loginDAO.login(username, password)) {
				new WelcomeView();
				//frame.setVisible(false);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "Login INCORRECTO","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
}

		
	}

