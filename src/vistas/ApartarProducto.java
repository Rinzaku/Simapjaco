package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import controllers.Apartar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;

import ticket.Ticket;

import java.awt.Toolkit;

public class ApartarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textModelo;
	private JTextField textTalla;
	private JTextField textColor;
	private JTextField textPrecio;
	private JTextField textCuenta;
	private JTextField textResta;
	private JTextField textFolio;
	private JButton buttonListo;
	private Apartar apartar;
	private JFrame ventanApartar;
	private JTextField textDesc;
	private Ticket ticket;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ApartarProducto frame = new ApartarProducto();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param precio 
	 * @param cantidad 
	 * @param color 
	 * @param talla 
	 * @param descripcion 
	 * @param modelo 
	 */
	public ApartarProducto(String modelo, String descripcion, String talla, String color, String precio,String folio,int idModelo, int ropa,Ventana_ventas ventas,int empleado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ApartarProducto.class.getResource("/imagenes/Shopping48.png")));
		ventanApartar=this;
		ticket=new Ticket();
		apartar = new Apartar();
		setTitle("Apartar producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 354);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[97px][4px][114.00px,grow][190px][12px][][][86px]", "[20px][20px][20px,grow][20px][20px][20px][8px][20px][20px][8px][8px][11.00px][41px]"));
		
		JLabel lblFolio = new JLabel("Folio :");
		lblFolio.setForeground(Color.WHITE);
		lblFolio.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblFolio, "flowx,cell 3 0,alignx right,aligny top");
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setForeground(Color.WHITE);
		contentPane.add(lblFecha, "cell 5 0");
		
		JLabel etiquetaFecha = new JLabel();
		etiquetaFecha.setText(apartar.fecha());
		etiquetaFecha.setForeground(Color.WHITE);
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(etiquetaFecha, "cell 7 0");
		
		JLabel lblModelo = new JLabel("Modelo :");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblModelo, "cell 0 1,alignx right,aligny top");
		
		textModelo = new JTextField(modelo);
		textModelo.setEditable(false);
		contentPane.add(textModelo, "cell 2 1,alignx left,aligny bottom");
		textModelo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion :");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblDescripcion, "cell 0 2,alignx left,aligny top");
		
		textDesc = new JTextField(descripcion);
		contentPane.add(textDesc, "cell 2 2,growx");
		textDesc.setColumns(10);
		
		JLabel lblTalla = new JLabel("Talla :");
		lblTalla.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTalla.setForeground(Color.WHITE);
		contentPane.add(lblTalla, "cell 0 3,alignx right,aligny top");
		
		textTalla = new JTextField(talla);
		textTalla.setEditable(false);
		contentPane.add(textTalla, "cell 2 3,alignx left,aligny bottom");
		textTalla.setColumns(10);
		
		JLabel lblColor = new JLabel("Color :");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblColor.setForeground(Color.WHITE);
		contentPane.add(lblColor, "cell 0 4,alignx right,aligny top");
		
		textColor = new JTextField(color);
		textColor.setEditable(false);
		contentPane.add(textColor, "cell 2 4,alignx left,aligny bottom");
		textColor.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio : ");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecio.setForeground(Color.WHITE);
		contentPane.add(lblPrecio, "cell 0 5,alignx right,aligny top");
		
		textPrecio = new JTextField(precio);
		textPrecio.setEditable(false);
		contentPane.add(textPrecio, "cell 2 5,alignx left,aligny bottom");
		textPrecio.setColumns(10);
		
		JLabel lblACuenta = new JLabel("A cuenta :\r\n");
		lblACuenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblACuenta.setForeground(Color.WHITE);
		contentPane.add(lblACuenta, "cell 0 7,alignx right,aligny top");
		
		textCuenta = new JTextField();
		textCuenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					String cuenta;
					cuenta=apartar.diferencia(Double.parseDouble(textPrecio.getText()),Double.parseDouble(textCuenta.getText()));
					textResta.setText(cuenta);
					
				}
			}
		});
		contentPane.add(textCuenta, "cell 2 7,alignx left,aligny bottom");
		textCuenta.setColumns(10);
		
		JLabel lblResta = new JLabel("Resta :\r\n");
		lblResta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResta.setForeground(Color.WHITE);
		contentPane.add(lblResta, "cell 0 8,alignx right,aligny top");
		
		textResta = new JTextField();
		textResta.setEditable(false);
		contentPane.add(textResta, "cell 2 8,alignx left,aligny bottom");
		textResta.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ApartarProducto.class.getResource("/imagenes/apartar128.png")));
		contentPane.add(label, "cell 3 1 5 8,alignx center,aligny center");
		
		
		buttonListo = new JButton("");
		
		buttonListo.setBackground(new Color(51, 0, 204));
		
		buttonListo.setIcon(new ImageIcon(ApartarProducto.class.getResource("/imagenes/ok32.png")));
		contentPane.add(buttonListo, "cell 0 12 3 1,grow");
		
		JButton buttonCancelar = new JButton("");
		buttonCancelar.setBackground(new Color(51, 0, 204));
		buttonCancelar.setIcon(new ImageIcon(ApartarProducto.class.getResource("/imagenes/error32.png")));
		contentPane.add(buttonCancelar, "cell 3 12 4 1,grow");
		
		textFolio = new JTextField(folio);
		contentPane.add(textFolio, "cell 3 0,alignx left,aligny bottom");
		textFolio.setColumns(10);
		
		buttonListo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean bdn=false;
				if (textCuenta.getText().isEmpty() && textResta.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Ingrese la cantidad para apartar el producto");
				}
				bdn=apartar.apartarProducto(modelo, descripcion, talla, color, precio,etiquetaFecha.getText(),Double.parseDouble(textResta.getText()),idModelo,ropa,empleado);
				if (bdn) {
					ticket.cabecera(folio,apartar.nombreEmpleado(empleado));
					ticket.itemApartar(textModelo.getText(), "1", textPrecio.getText());
					ticket.Apartado(precio, textCuenta.getText(), textResta.getText());
					ticket.piePaginaApartado();
					ticket.ImprimirDocumento();
					JOptionPane.showMessageDialog(null, "Apartado exitoso");
					ventanApartar.dispose();
					ventas.limpiaVentana(); 
					
				}else{
					JOptionPane.showMessageDialog(null, "No se pudo apartar el producto");
					ventanApartar.dispose();
					ventas.limpiaVentana(); 
				}
				
				
				
			}
		});
	}
}
