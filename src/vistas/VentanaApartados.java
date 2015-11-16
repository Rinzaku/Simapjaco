package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaApartados extends JFrame {

	private JPanel contentPane;
	private JTextField textModel;
	private JTextField textResta;
	private JTextField textAbono;
	private JTextField textTotal;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaApartados frame = new VentanaApartados();
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
	public VentanaApartados() {
		setTitle("Apartados");
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 309);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setBounds(300, 12, 53, 19);
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBackground(Color.WHITE);
		contentPane.add(lblFecha);
		
		JLabel etiquetaFecha = new JLabel("12/15/2015");
		etiquetaFecha.setBounds(352, 12, 98, 19);
		etiquetaFecha.setForeground(Color.WHITE);
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(etiquetaFecha);
		
		JLabel lblModelo = new JLabel("Modelo :");
		lblModelo.setBounds(12, 50, 63, 19);
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblModelo);
		
		textModel = new JTextField();
		textModel.setBounds(79, 49, 133, 20);
		contentPane.add(textModel);
		textModel.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(222, 31, 63, 48);
		label.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/search48.png")));
		contentPane.add(label);
		
		JLabel lblResta = new JLabel("Resta :");
		lblResta.setBounds(23, 87, 52, 19);
		lblResta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResta.setForeground(Color.WHITE);
		contentPane.add(lblResta);
		
		textResta = new JTextField();
		textResta.setBounds(79, 88, 133, 20);
		contentPane.add(textResta);
		textResta.setColumns(10);
		
		JLabel lblAbono = new JLabel("Abono :");
		lblAbono.setBounds(20, 112, 55, 19);
		lblAbono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAbono.setForeground(Color.WHITE);
		contentPane.add(lblAbono);
		
		textAbono = new JTextField();
		textAbono.setBounds(79, 113, 133, 20);
		contentPane.add(textAbono);
		textAbono.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(28, 137, 47, 19);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setForeground(Color.WHITE);
		contentPane.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setBounds(79, 138, 133, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);
		
		JLabel labelImagen = new JLabel("");
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagen.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/money215.png")));
		labelImagen.setBounds(310, 50, 164, 129);
		contentPane.add(labelImagen);
		
		JButton button = new JButton("");
		button.setBackground(new Color(51, 0, 204));
		button.setForeground(new Color(51, 0, 204));
		button.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/error32.png")));
		button.setBounds(34, 216, 189, 34);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setBackground(new Color(51, 0, 204));
		button_1.setForeground(new Color(51, 0, 204));
		button_1.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/ok32.png")));
		button_1.setBounds(239, 216, 189, 34);
		contentPane.add(button_1);
	}
}
