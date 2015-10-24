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

public class ApartarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtClave;
	private JTextField txtArticulo;
	private JTextField txtColor;
	private JTextField txtTalla;
	private JTextField txtPrecio;
	private JButton btnBuscar;
	private JButton btnApartar;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public ApartarProducto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\icon.jpg"));
		
		setTitle("APARTADO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 202);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[98.00px][124.00px][][][][]", "[23px][20px][20px][20px][20px][23px][]"));
		
		txtClave = new JTextField();
		txtClave.setToolTipText("Ingresa La Clave del Producto");
		txtClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
		          if(!txtClave.getText().trim().isEmpty())
		        	  btnBuscar.setEnabled(true);  
		          else{
		        	  btnBuscar.setEnabled(false);
		        	  btnApartar.setEnabled(false);
		        	  txtArticulo.setText("");
						txtColor.setText("");
						txtTalla.setText("");
						txtPrecio.setText("");
		          }
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar(); 
		          if(!Character.isDigit(c)) { 
		              getToolkit().beep();   
		              e.consume();
		          }
			}
		});
		contentPane.add(txtClave, "cell 1 0,alignx center,aligny center");
		txtClave.setColumns(10);
		
		btnBuscar = new JButton("BUSCAR");
		contentPane.add(btnBuscar, "cell 3 0,alignx center");
		
		JLabel lblArticulo = new JLabel("Articulo:");
		lblArticulo.setForeground(Color.WHITE);
		lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblArticulo, "cell 0 1,alignx left,aligny top");
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setForeground(Color.WHITE);
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblClave, "cell 0 0,alignx left,aligny center");
		
		txtArticulo = new JTextField();
		txtArticulo.setToolTipText("Nombre del Articulo");
		txtArticulo.setEditable(false);
		contentPane.add(txtArticulo, "cell 1 1,alignx center,aligny center");
		txtArticulo.setColumns(10);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblColor.setForeground(Color.WHITE);
		contentPane.add(lblColor, "cell 0 2,alignx left,aligny top");
		
		txtColor = new JTextField();
		txtColor.setToolTipText("Color Seleccionado");
		txtColor.setEditable(false);
		contentPane.add(txtColor, "cell 1 2,alignx center,aligny center");
		txtColor.setColumns(10);
		
		btnApartar = new JButton("APARTAR");
		contentPane.add(btnApartar, "cell 3 2,alignx center");
		
		JLabel lblTalla = new JLabel("Talla:");
		lblTalla.setForeground(Color.WHITE);
		lblTalla.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblTalla, "cell 0 3,alignx left,aligny top");
		
		txtTalla = new JTextField();
		txtTalla.setToolTipText("Talla Seleccionada");
		txtTalla.setEditable(false);
		contentPane.add(txtTalla, "cell 1 3,alignx center,aligny center");
		txtTalla.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecio.setForeground(Color.WHITE);
		contentPane.add(lblPrecio, "cell 0 4,alignx left,aligny center");
		
		txtPrecio = new JTextField();
		txtPrecio.setToolTipText("Costo del Articulo");
		txtPrecio.setEditable(false);
		contentPane.add(txtPrecio, "cell 1 4,alignx center,aligny center");
		txtPrecio.setColumns(10);
		
		btnCancelar = new JButton("CANCELAR");
		contentPane.add(btnCancelar, "cell 3 4,alignx center");
	}

}

