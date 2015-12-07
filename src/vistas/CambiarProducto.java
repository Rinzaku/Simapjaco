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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	
	private class Vista extends JFrame {

		private JPanel contentPane;
		private JTextField textField;
		private JScrollPane scrollPane;
		
		private JTable tablaModelo;
		private DefaultTableModel model;
		
		private String[][] datos;
		private String[] cabecera = {"Modelo","Descripcion","Talla","Color","Cantidad","Precio"};
		private boolean bandera=false;
		Vista v;
		
		/**
		 * Create the frame.
		 */
		public Vista() {
			v = this;
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new MigLayout("", "[812px,grow]", "[][29.00px][27.00px][grow][29.00][29.00][29.00,grow,top][21.00][grow][][grow]"));
			setContentPane(contentPane);
			
			JLabel lblNewLabel = new JLabel("Modelo");
			
			contentPane.add(lblNewLabel, "flowx,cell 0 1,alignx center");
			
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent key) {
					if(key.getKeyChar()=='\n'){
//						JOptionPane.showConfirmDialog(null, "Hecho");
						if (!textField.getText().isEmpty()) {
							if(bandera) contentPane.remove(scrollPane);
							
							datos = cambios.busca_modelo(textField.getText());
							model = new DefaultTableModel(datos, cabecera){
								@Override
								public boolean isCellEditable(int row, int col){
									return false;
								}
							};
							tablaModelo = new JTable(model);
							tablaModelo.setBackground(new Color(176, 224, 230));
							
							scrollPane = new JScrollPane(tablaModelo);
							scrollPane.setPreferredSize(new Dimension(350, 100));
							
							contentPane.add(scrollPane, "cell 0 3,grow");
							
							addMouseClick();
							
							contentPane.updateUI();
							bandera=true;
							btnAceptar.setEnabled(true);
						}else{
							JOptionPane.showConfirmDialog(null, "POr favor ingresa un modelos");
						}
						
					}
				}
			});
			contentPane.add(textField, "cell 0 1");
			textField.setColumns(10);
			
		}
		
		private void addMouseClick(){
			tablaModelo.addMouseListener(new MouseAdapter() {
				@Override
				//evento para controlar  los clicks de la tabla ^.^/
				public void mousePressed(MouseEvent click) {
					if(click.getClickCount()==2){
//						JOptionPane.showMessageDialog(null, "Docle click");
						int renglon = tablaModelo.getSelectedRow();
						double precio = Double.parseDouble(model.getValueAt(renglon, 5).toString());
						double precioProducto = Double.parseDouble(modelCambios.getValueAt(renglonCambio, 3).toString());
						double diferencia = precioProducto-precio;
						
						textModeloCambio.setText(model.getValueAt(renglon, 0).toString());
						txtColor.setText(model.getValueAt(renglon, 3).toString());
						txtTalla.setText(model.getValueAt(renglon, 2).toString());
						textPrecioCambio.setText(""+precio);
						if (diferencia<=0) {
							textDiferencia.setText(""+Math.abs(diferencia));
							v.dispose();
						}else{
							JOptionPane.showMessageDialog(null, "El producto no puede costar menos que el producto a cambio");
						}
						
					}
				}
			});
		}

	}
	
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
	private JTextField txtColor;
	private JTextField txtTalla;
	
	private JButton btnAceptar;
	
	private int renglonCambio=-1;
	/**
	 * Create the frame.
	 */
	public CambiarProducto() {
		CambiarProducto cp = this;
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
						
						tableCambios.addMouseListener(new MouseAdapter() {
							@Override
							//evento para controlar  los clicks de la tabla ^.^/
							public void mousePressed(MouseEvent click) {
								if(click.getClickCount()==1){
									renglonCambio = tableCambios.getSelectedRow();
								}
								
							}
						});
						
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
		getContentPane().setLayout(new MigLayout("", "[207.00px,grow][18px][149px,grow][32px][46px][4px][101px][33px][163px]", "[21px][60.00px,grow][20px][21px][21px][0.00][0.00][0.00][0.00][0.00][][][0.00][][][][][][][baseline][41px,bottom]"));
		
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
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textFolio.getText().isEmpty()){
					if(tableCambios.getSelectedRow()!=-1)
						new Vista().setVisible(true);
					else
						JOptionPane.showMessageDialog(null, "Selecciona el producto que se cambiara");
				}else{
					JOptionPane.showMessageDialog(null, "Por favor ingrese una venta");
				}
				
			}
		});
		getContentPane().add(btnBuscar, "cell 0 14,alignx left");
		
		JLabel lblModeloCambio = new JLabel("Modelo de Cambio :");
		lblModeloCambio.setForeground(Color.WHITE);
		lblModeloCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblModeloCambio, "flowx,cell 0 15,alignx left,aligny center");
		
		textModeloCambio = new JTextField();
		textModeloCambio.setEditable(false);
		getContentPane().add(textModeloCambio, "cell 2 15,growx,aligny top");
		textModeloCambio.setColumns(10);
		
		JLabel lblPrecioCambio = new JLabel("Precio :");
		lblPrecioCambio.setForeground(Color.WHITE);
		lblPrecioCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblPrecioCambio, "cell 6 15,alignx left,aligny top");
		
		textPrecioCambio = new JTextField();
		textPrecioCambio.setEditable(false);
		getContentPane().add(textPrecioCambio, "cell 8 15,growx,aligny bottom");
		textPrecioCambio.setColumns(10);
		
		JLabel lblColor = new JLabel("Color :");
		lblColor.setForeground(Color.WHITE);
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblColor, "cell 0 16");
		
		txtColor = new JTextField();
		txtColor.setEditable(false);
		txtColor.setColumns(10);
		getContentPane().add(txtColor, "cell 2 16,growx");
		
		JLabel lblTalla = new JLabel("Talla :");
		lblTalla.setForeground(Color.WHITE);
		lblTalla.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblTalla, "cell 0 17");
		
		txtTalla = new JTextField();
		txtTalla.setEditable(false);
		txtTalla.setColumns(10);
		getContentPane().add(txtTalla, "cell 2 17,growx");
		
		JLabel lblDiferencia = new JLabel("Diferencia :");
		lblDiferencia.setForeground(Color.WHITE);
		lblDiferencia.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblDiferencia, "cell 6 17,alignx left,aligny top");
		
		textDiferencia = new JTextField();
		textDiferencia.setEditable(false);
		getContentPane().add(textDiferencia, "cell 8 17,growx,aligny bottom");
		textDiferencia.setColumns(10);
		
		btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Cambio efectuado exitosamente");
				
				getContentPane().remove(scrollCambios);
				textFolio.setText("");
				textAreaDatosVenta.setText("***************************************Descripcion de la venta*************************************");
				textModeloCambio.setText("");
				textPrecioCambio.setText("");
				textDiferencia.setText("");
				txtColor.setText("");
				txtTalla.setText("");
			}
		});
		btnAceptar.setBackground(new Color(51, 0, 204));
		btnAceptar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/ok32.png")));
		btnAceptar.setEnabled(false);
		getContentPane().add(btnAceptar, "cell 0 20 3 1,growx,aligny bottom");
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(51, 0, 204));
		btnCancelar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/error32.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cp.dispose();
			}
		});
		getContentPane().add(btnCancelar, "cell 4 20 5 1,growx,aligny bottom");
	}
}

