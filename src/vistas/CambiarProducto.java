package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.DropMode;

public class CambiarProducto extends JFrame {
	private JTextField textModeloVenta;
	private JTextField textModeloCambio;
	private JTextField textPrecioVenta;
	private JTextField textPrecioCambio;
	private JTextField textFolio;
	private JTextField textDiferencia;

	/**
	 * Create the frame.
	 */
	public CambiarProducto() {
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\icon.jpg"));
		
		setTitle("Cambio de Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 368);
		getContentPane().setLayout(new MigLayout("", "[68px][10px][141px,grow][67px][68px][10px][141px,grow][1px]", "[][8.00][117.00px][-68.00px][][22px][20px][]"));
		
		JLabel lblFolio = new JLabel("Folio :");
		lblFolio.setForeground(Color.WHITE);
		lblFolio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblFolio, "cell 0 0");
		
		textFolio = new JTextField();
		getContentPane().add(textFolio, "cell 2 0,alignx left");
		textFolio.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblFecha, "cell 3 0");
		
		JTextArea txtrdescripcionDeLa = new JTextArea();
		txtrdescripcionDeLa.setEditable(false);
		txtrdescripcionDeLa.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
		txtrdescripcionDeLa.setText("*******************************Descripcion de la venta*******************************");
		txtrdescripcionDeLa.setTabSize(7);
		getContentPane().add(txtrdescripcionDeLa, "cell 0 2 7 1,grow");
		
		JLabel lblModelo = new JLabel("Modelo de  Venta:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblModelo, "cell 0 5,growx,aligny center");
		
		textModeloVenta = new JTextField();
		getContentPane().add(textModeloVenta, "cell 2 5,growx,aligny top");
		textModeloVenta.setColumns(10);
		
		textModeloCambio = new JTextField();
		getContentPane().add(textModeloCambio, "cell 2 6,growx,aligny top");
		textModeloCambio.setColumns(10);
		
		JLabel lblModeloCambio = new JLabel("Modelo de Cambio :");
		lblModeloCambio.setForeground(Color.WHITE);
		lblModeloCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblModeloCambio, "cell 0 6,growx,aligny center");
		
		JLabel lblPrecioVenta = new JLabel("Precio :");
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecioVenta.setForeground(Color.WHITE);
		getContentPane().add(lblPrecioVenta, "cell 4 5,growx,aligny center");
		
		textPrecioVenta = new JTextField();
		getContentPane().add(textPrecioVenta, "cell 6 5,growx,aligny bottom");
		textPrecioVenta.setColumns(10);
		
		JLabel lblPrecioCambio = new JLabel("Precio :");
		lblPrecioCambio.setForeground(Color.WHITE);
		lblPrecioCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblPrecioCambio, "cell 4 6");
		
		textPrecioCambio = new JTextField();
		getContentPane().add(textPrecioCambio, "cell 6 6,growx");
		textPrecioCambio.setColumns(10);
		
		JLabel lblDiferencia = new JLabel("Diferencia :");
		lblDiferencia.setForeground(Color.WHITE);
		lblDiferencia.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblDiferencia, "cell 4 7");
		
		textDiferencia = new JTextField();
		textDiferencia.setEditable(false);
		getContentPane().add(textDiferencia, "cell 6 7,growx");
		textDiferencia.setColumns(10);
	}
}

