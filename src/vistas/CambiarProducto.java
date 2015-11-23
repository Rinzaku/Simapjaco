package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.Font;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextArea;

import java.awt.SystemColor;

import javax.swing.JScrollPane;
import javax.swing.DropMode;

import controllers.Cambio;

import javax.swing.JTable;

public class CambiarProducto extends JFrame {
	private JTextField textModeloCambio;
	private JTextField textPrecioCambio;
	private JTextField textFolio;
	private JTextField textDiferencia;
	private JTextArea textAreaDatosVenta;
	
	private Cambio cambios;
	private JTable tableCambios;
	private DefaultTableModel modelCambios;
	private JScrollPane scrollCambios;

	private String [] cabecera = {"Modelo", "Descripcion","No. articulos", "Precio unitario", "Estado"};
	private String [][] datostable;
	private boolean bandera=false;
	
	/**
	 * Create the frame.
	 */
	public CambiarProducto() {
		cambios = new Cambio();
		
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CambiarProducto.class.getResource("/imagenes/Shopping48.png")));
		
		setTitle("Cambio de Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 380);
		
		textFolio = new JTextField();
		textFolio.setHorizontalAlignment(SwingConstants.RIGHT);
		textFolio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent key) {
				if (key.getKeyChar()=='\n') {
					if (!textFolio.getText().trim().isEmpty()) {
						if (bandera) {
							getContentPane().remove(scrollCambios);
						} 
						
						int folio = Integer.parseInt(textFolio.getText());
						String datos = cambios.obten_venta(folio);
						textAreaDatosVenta.setText("Folio \t Fecha \t\t Total artículos \t Total Venta \n"+datos);
						datostable = cambios.obten_detalles_venta(folio);
						modelCambios = new DefaultTableModel(datostable,cabecera){
							@Override
							public boolean isCellEditable(int row, int col){
								return false;
							}
						};
						tableCambios = new JTable(modelCambios);
						tableCambios.setBackground(new Color(176, 224, 230));
						scrollCambios = new JScrollPane(tableCambios);
						scrollCambios.setPreferredSize(new Dimension(400, 150));
						System.out.println(bandera);
						getContentPane().add(scrollCambios,"cell 0 2 9 12,grow");
						
						scrollCambios.setVisible(true);
						bandera = true;
						
					} else {
						JOptionPane.showMessageDialog(null, "Ingresa un folio de venta");
					}
					
				}
			}
		});
		getContentPane().setLayout(new MigLayout("", "[144px,grow][18px][149px][32px][46px][4px][101px][33px][163px]", "[21px][60.00px,grow][20px][21px][21px][0.00][0.00][0.00][0.00][0.00][][][0.00][][][][][][][baseline][41px,bottom]"));
		
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
		
		textAreaDatosVenta = new JTextArea();
		textAreaDatosVenta.setFont(new Font("Tahoma",Font.BOLD+Font.ITALIC, 13));
		textAreaDatosVenta.setText("***************************************Descripcion de la venta*************************************");
		getContentPane().add(textAreaDatosVenta, "cell 0 1 9 1,grow");
		
//		tableCambios = new JTable();
//		getContentPane().add(tableCambios, "cell 0 2 9 12,grow");
		
		JLabel lblModeloCambio = new JLabel("Modelo de Cambio :");
		lblModeloCambio.setForeground(Color.WHITE);
		lblModeloCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblModeloCambio, "cell 0 15,alignx left,aligny center");
		
		textModeloCambio = new JTextField();
		getContentPane().add(textModeloCambio, "cell 2 15,growx,aligny top");
		textModeloCambio.setColumns(10);
		
		JLabel lblPrecioCambio = new JLabel("Precio :");
		lblPrecioCambio.setForeground(Color.WHITE);
		lblPrecioCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblPrecioCambio, "cell 6 15,alignx left,aligny top");
		
		textPrecioCambio = new JTextField();
		getContentPane().add(textPrecioCambio, "cell 8 15,growx,aligny bottom");
		textPrecioCambio.setColumns(10);
		
		JLabel lblDiferencia = new JLabel("Diferencia :");
		lblDiferencia.setForeground(Color.WHITE);
		lblDiferencia.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblDiferencia, "cell 6 17,alignx left,aligny top");
		
		textDiferencia = new JTextField();
		textDiferencia.setEditable(false);
		getContentPane().add(textDiferencia, "cell 8 17,growx,aligny bottom");
		textDiferencia.setColumns(10);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.setBackground(new Color(51, 0, 204));
		btnAceptar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/ok32.png")));
		
		getContentPane().add(btnAceptar, "cell 0 20 3 1,growx,aligny bottom");
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(51, 0, 204));
		btnCancelar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/error32.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnCancelar, "cell 4 20 5 1,growx,aligny bottom");
	}
}

