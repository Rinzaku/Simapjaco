package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JPasswordField passwordFieldContrasena;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setBounds(100, 100, 578, 180);
		contentPane =  new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[79px][10px][255px,grow][136px]", "[50px][4px][20px][11px][37px]"));
		contentPane.setLayout(new MigLayout("", "[68px][6px][83px][1px][6px][73px]", "[23px][][]"));
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNombre, "cell 0 0,grow");
		
		textFieldNombre = new JTextField();
		contentPane.add(textFieldNombre, "cell 2 0,grow");
		textFieldNombre.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Login.class.getResource("/imagenes/Users96.png")));
		contentPane.add(label, "cell 3 0,alignx left,aligny top");
		
		JLabel lblContasea = new JLabel("Contase\u00F1a:");
		lblContasea.setForeground(Color.WHITE);
		lblContasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblContasea, "cell 0 1,grow");
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/imagenes/ok32.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		passwordFieldContrasena = new JPasswordField();
		contentPane.add(passwordFieldContrasena, "cell 2 1,grow");
		contentPane.add(btnNewButton, "cell 0 2,grow");
	}

}
