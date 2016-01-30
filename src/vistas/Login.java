package vistas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPasswordField;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JPasswordField passwordFieldContrasena;
	private String[][] users= {{"KingSimapjaco","1s1m4pj4c0"},{"ADMIN1","mediasoft"},{"ADMIN2","illrmmbrLaura"}};
	private int intentos=0;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setBounds(100, 100, 578, 180);
		setTitle("LOGIN");
		contentPane = new JPanel();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/SIMAP.png")));
		contentPane.setBackground(new Color(176, 224, 226));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[79px][10px][255px,grow][136px]", "[50px][4px][20px][11px][37px]"));
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setForeground(new Color (0,51,153));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNombre, "cell 0 0,growx,aligny bottom");
		
		textFieldNombre = new JTextField();
		contentPane.add(textFieldNombre, "cell 2 0,growx,aligny bottom");
		textFieldNombre.setColumns(10);
		
		JLabel lblContasea = new JLabel("Contase\u00F1a:");
		lblContasea.setForeground(new Color(0,51,153));
		lblContasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblContasea, "cell 0 2,growx,aligny center");
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Login.class.getResource("/imagenes/Users96.png")));
		contentPane.add(label, "cell 3 0 1 5,grow");
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setBackground(new Color(0,51,153));
		btnNewButton.setForeground(Color.white);
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/imagenes/ok32.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accesar();
			}
		});
		
		passwordFieldContrasena = new JPasswordField();
		passwordFieldContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accesar();
			}
		});
		contentPane.add(passwordFieldContrasena, "cell 2 2,growx");
		contentPane.add(btnNewButton, "cell 0 4 3 1,grow");
	}

	private void accesar(){
		boolean acceso = false;
		String usuario = textFieldNombre.getText();
		char[] arraypass = passwordFieldContrasena.getPassword();
		String password = new String(arraypass);
		for (String[] user : users) {
			if(usuario.compareTo(user[0])==0 && password.compareTo(user[1])==0){
				acceso = true;
				break;
			}
		}
		if(acceso){
			Administrador admin =new Administrador();
			admin.setVisible(true);
			admin.setLocationRelativeTo(null);
			intentos=0;
			dispose();
		}else{
			JOptionPane.showMessageDialog(contentPane, "Nombre o contraseña inválidos\nFavor de verificar", "Error", JOptionPane.ERROR_MESSAGE);
			intentos++;
			
		}
		if(intentos == 3){
			JOptionPane.showMessageDialog(contentPane, "Demasiados intentos,\nfavor de intentar mas tarde", "Error", JOptionPane.ERROR_MESSAGE);
			intentos=0;
			dispose();
		}
	}
}
