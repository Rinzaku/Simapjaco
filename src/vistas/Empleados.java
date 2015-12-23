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

public class Empleados extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(10, 31, 76, 14);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(96, 30, 186, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidos.setBounds(10, 75, 76, 14);
		contentPane.add(lblApellidos);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(96, 74, 186, 20);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setBounds(10, 198, 89, 14);
		contentPane.add(lblDireccion);
		
		JTextArea textAreaDireccion = new JTextArea();
		textAreaDireccion.setBounds(96, 195, 186, 31);
		contentPane.add(textAreaDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setBounds(10, 125, 76, 14);
		contentPane.add(lblTelefono);
		
		textField = new JTextField();
		textField.setBounds(96, 124, 186, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel labelImage = new JLabel("");
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		labelImage.setIcon(new ImageIcon("C:\\Users\\FER\\Downloads\\media5 (4).png"));
		labelImage.setBounds(347, 11, 314, 201);
		contentPane.add(labelImage);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setBounds(10, 162, 76, 14);
		contentPane.add(lblFecha);
	}
}
