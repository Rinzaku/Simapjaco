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
import java.awt.event.KeyListener;

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

import controllers.Cambio;

public class CambiarProducto extends JFrame {
	private JTextField textModeloVenta;
	private JTextField textModeloCambio;
	private JTextField textPrecioVenta;
	private JTextField textPrecioCambio;
	private JTextField textFolio;
	private JTextField textDiferencia;
	private JTextArea txtrdescripcionDeLa;
	
	private Cambio cambios;

	/**
	 * Create the frame.
	 */
	public CambiarProducto() {
		cambios = new Cambio();
		
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\icon.jpg"));
		
		setTitle("Cambio de Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 314);
		
		textFolio = new JTextField();
		textFolio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent key) {
				if (key.getKeyChar()=='\n') {
					if (!textFolio.getText().trim().isEmpty()) {
						int folio = Integer.parseInt(textFolio.getText());
						String datos = cambios.obten_venta(folio);
						txtrdescripcionDeLa.setText("Folio \t\t\t Fecha \t\t Total_articulos \t Total Venta \n"+datos);
					} else {
						JOptionPane.showMessageDialog(null, "Ingresa un folio de venta");
					}
					
				}
			}
		});
		getContentPane().setLayout(new MigLayout("", "[144px][18px][149px][32px][46px][4px][101px][33px][163px]", "[21px][115px][20px][21px][21px][][][][][][baseline][41px,bottom]"));
		
		JLabel lblFolio = new JLabel("Folio :");
		lblFolio.setForeground(Color.WHITE);
		lblFolio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblFolio, "cell 4 0,alignx left,aligny top");
		getContentPane().add(textFolio, "cell 6 0,alignx right,aligny bottom");
		textFolio.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha : " + cambios.fecha());
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblFecha, "cell 8 0,alignx right,aligny top");
		
		txtrdescripcionDeLa = new JTextArea();
		txtrdescripcionDeLa.setEditable(false);
		txtrdescripcionDeLa.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
		txtrdescripcionDeLa.setText("*******************************Descripcion de la venta*******************************");
		txtrdescripcionDeLa.setTabSize(7);
		getContentPane().add(txtrdescripcionDeLa, "cell 0 1 9 1,grow");
		
		JLabel lblModelo = new JLabel("Modelo de  Venta:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblModelo, "cell 0 2,growx,aligny bottom");
		
		textModeloVenta = new JTextField();
		getContentPane().add(textModeloVenta, "cell 2 2,growx,aligny top");
		textModeloVenta.setColumns(10);
		
		textModeloCambio = new JTextField();
		getContentPane().add(textModeloCambio, "cell 2 3,growx,aligny top");
		textModeloCambio.setColumns(10);
		
		JLabel lblModeloCambio = new JLabel("Modelo de Cambio :");
		lblModeloCambio.setForeground(Color.WHITE);
		lblModeloCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblModeloCambio, "cell 0 3,alignx left,aligny center");
		
		JLabel lblPrecioVenta = new JLabel("Precio :");
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecioVenta.setForeground(Color.WHITE);
		getContentPane().add(lblPrecioVenta, "cell 6 2,growx,aligny bottom");
		
		textPrecioVenta = new JTextField();
		getContentPane().add(textPrecioVenta, "cell 8 2,growx,aligny top");
		textPrecioVenta.setColumns(10);
		
		JLabel lblPrecioCambio = new JLabel("Precio :");
		lblPrecioCambio.setForeground(Color.WHITE);
		lblPrecioCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblPrecioCambio, "cell 6 3,alignx left,aligny top");
		
		textPrecioCambio = new JTextField();
		getContentPane().add(textPrecioCambio, "cell 8 3,growx,aligny bottom");
		textPrecioCambio.setColumns(10);
		
		JLabel lblDiferencia = new JLabel("Diferencia :");
		lblDiferencia.setForeground(Color.WHITE);
		lblDiferencia.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblDiferencia, "cell 6 4,alignx left,aligny top");
		
		textDiferencia = new JTextField();
		textDiferencia.setEditable(false);
		getContentPane().add(textDiferencia, "cell 8 4,growx,aligny bottom");
		textDiferencia.setColumns(10);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.setBackground(new Color(51, 0, 204));
		btnAceptar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/ok32.png")));
		
		getContentPane().add(btnAceptar, "cell 0 11 3 1,growx,aligny bottom");
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(51, 0, 204));
		btnCancelar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/error32.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnCancelar, "cell 4 11 5 1,growx,aligny bottom");
	}
}

