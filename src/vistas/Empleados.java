package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Empleados extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Empleados frame = new Empleados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Empleados() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 418);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(28, 33, 64, 19);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setForeground(Color.WHITE);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(107, 32, 186, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(18, 85, 74, 19);
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblApellidos);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(107, 84, 186, 20);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setBounds(12, 240, 80, 19);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDireccion.setForeground(Color.WHITE);
		contentPane.add(lblDireccion);
		
		JTextArea txtDireccion = new JTextArea();
		txtDireccion.setBounds(107, 240, 186, 48);
		contentPane.add(txtDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(22, 137, 70, 19);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setForeground(Color.WHITE);
		contentPane.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(107, 136, 186, 20);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel labelImage = new JLabel("");
		labelImage.setBounds(349, 32, 280, 256);
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		labelImage.setIcon(new ImageIcon("C:\\Users\\FER\\Downloads\\media5 (4).png"));
		contentPane.add(labelImage);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(43, 188, 49, 19);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setForeground(Color.WHITE);
		contentPane.add(lblFecha);
		
		JDateChooser Calendario = new JDateChooser();
		Calendario.setBounds(107, 188, 186, 20);
		contentPane.add(Calendario);
		
		JButton buttonBuscar = new JButton("");
		buttonBuscar.setBounds(12, 311, 187, 57);
		buttonBuscar.setIcon(new ImageIcon(Empleados.class.getResource("/imagenes/search48.png")));
		contentPane.add(buttonBuscar);
		
		JButton buttonAlta = new JButton("");
		buttonAlta.setBounds(229, 311, 187, 57);
		buttonAlta.setIcon(new ImageIcon(Empleados.class.getResource("/imagenes/ok48.png")));
		contentPane.add(buttonAlta);
		
		JButton buttonBaja = new JButton("");
		buttonBaja.setBounds(442, 311, 187, 57);
		buttonBaja.setIcon(new ImageIcon(Empleados.class.getResource("/imagenes/error48.png")));
		contentPane.add(buttonBaja);
	}
}
