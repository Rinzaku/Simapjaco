package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;













import javax.swing.SwingUtilities;

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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;

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
//						double precio = Double.parseDouble(model.getValueAt(renglon, 5).toString());
						double precioProducto = Double.parseDouble(modelCambios.getValueAt(renglonCambio, 3).toString());
//						double diferencia = precioProducto-precio;
						double diferencia=0, precio;
//						textModeloCambio.setText(model.getValueAt(renglon, 0).toString());
						String[][] datos = {{model.getValueAt(renglon, 0).toString(),model.getValueAt(renglon, 1).toString(),""+1,model.getValueAt(renglon, 5).toString()}};
						
						if(bandera2==2){
							System.out.println("Ya esta la tabla");
							datosTablaCambio = datos;
							modeloCambio.addRow(datos[0]);
							scrollCambio.updateUI();
							
						}else if(bandera2==0){
//							System.out.println("Entre al else");
							datosTablaCambio = datos;
							modeloCambio = new DefaultTableModel(datosTablaCambio,cabeceraCambio){
								@Override
								public boolean isCellEditable(int row, int col){
									return col==2 ? true:false;
								}
							};
							
							tablaCambio = new JTable(modeloCambio);
							tablaCambio.setBackground(new Color(176, 224, 230));
							
							bandera2=1;
							
						}
						
						precio=0;
						for (int i = 0; i < modeloCambio.getRowCount(); i++) {
							precio += Double.parseDouble(modeloCambio.getValueAt(i, 3).toString());
						}
						
						diferencia = precio - precioProducto;
						dif = diferencia;
						textDiferencia.setText(""+diferencia);
						v.dispose();
						
//						if (diferencia<=0) {
////							v.setVisible(false);
//						}else{
//							JOptionPane.showMessageDialog(null, "El producto no puede costar menos que el producto a cambio");
//						}
						
					}
				}
			});
		}
		

	}
	
	private JTextField textModeloCambio;
//	private JTextField textPrecioCambio;
	private JTextField textFolio;
	private JTextField textDiferencia;
	private JTextArea textAreaDatosVenta;
	
	private Cambio cambios;
	private JTable tableCambios;
	private DefaultTableModel modelCambios;
	private JScrollPane scrollCambios;

	private String [] cabecera = {"Modelo", "Descripcion","No. articulos", "Precio unitario", "Estado"};
	private String [][] datostable;
	
	private DefaultTableModel modeloCambio;
	private String[] cabeceraCambio={"Modelo", "Descripcion","No. articulos", "Precio unitario"};
	private String[][] datosTablaCambio;
	private JTable tablaCambio;
	private JScrollPane scrollCambio;
	private boolean bandera1=false;
	private int bandera2=0;
//	private JTextField txtColor;
//	private JTextField txtTalla;
	
	private JButton btnAceptar;
	
	
	private int renglonCambio=-1;
	
	private double dif=0;
	private double total=0;
	/**
	 * Create the frame.
	 */
	public CambiarProducto() {
		cambios = new Cambio();
		CambiarProducto cp = this;
		cp.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				System.out.println("Ventana gano el enfoque");
				if(bandera2==1){
					
					scrollCambio = new JScrollPane(tablaCambio);
					scrollCambio.setPreferredSize(new Dimension(300, 100));
					
					agregaEscuchaTablaCambio();
					
					cp.getContentPane().add(scrollCambio, "cell 0 8 9 2,grow");
					scrollCambio.setVisible(true);
					
					SwingUtilities.updateComponentTreeUI(cp);
					bandera2=2;
//					System.out.println("Diferencia: "+dif);
					if (dif<0) {
						JOptionPane.showMessageDialog(cp, "Recuerda que debes llevar una o mas prendas que cubran el costo\nde la prenda a devolver");
					}
				}
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CambiarProducto.class.getResource("/imagenes/Shopping48.png")));
		
		setTitle("Cambio de Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 449);
		
		textFolio = new JTextField();
		textFolio.setHorizontalAlignment(SwingConstants.RIGHT);
		textFolio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent key) {
				if (key.getKeyChar()=='\n') {
					if (!textFolio.getText().trim().isEmpty()) {
						if (bandera1) {
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
						scrollCambios.setPreferredSize(new Dimension(300, 100));
//						System.out.println(bandera1);
						getContentPane().add(scrollCambios,"cell 0 3 9 2,grow");
						
//						scrollCambios.setVisible(true);
						bandera1 = true;
						
					} else {
						JOptionPane.showMessageDialog(null, "Ingresa un folio de venta");
					}
					
				}
			}
		});
		getContentPane().setLayout(new MigLayout("", "[207.00px,grow][18px][149px,grow][32px][46px][4px][101px][33px][163px]", "[23.00px][87.00px][21px][][22.00px][][][][9.00][26.00,baseline][32.00px,bottom][]"));
		
		
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
		
		btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Cambio efectuado exitosamente");
				
				getContentPane().remove(scrollCambios);
				textFolio.setText("");
				textAreaDatosVenta.setText("***************************************Descripcion de la venta*************************************");
//				textModeloCambio.setText("");
//				textPrecioCambio.setText("");
				textDiferencia.setText("");
//				txtColor.setText("");
//				txtTalla.setText("");
			}
		});
		
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
		getContentPane().add(btnBuscar, "cell 0 6,alignx left");
		
		JLabel lblModeloCambio = new JLabel("Modelo de Cambio :");
		lblModeloCambio.setForeground(Color.WHITE);
		lblModeloCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblModeloCambio, "flowx,cell 0 7,alignx left,aligny center");
		
		JLabel lblDiferencia = new JLabel("Diferencia :");
		lblDiferencia.setForeground(Color.WHITE);
		lblDiferencia.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblDiferencia, "cell 6 10,alignx left,aligny top");
		
		textDiferencia = new JTextField();
		textDiferencia.setEditable(false);
		getContentPane().add(textDiferencia, "cell 8 10,growx,aligny bottom");
		textDiferencia.setColumns(10);
		btnAceptar.setBackground(new Color(51, 0, 204));
		btnAceptar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/ok32.png")));
		btnAceptar.setEnabled(false);
		getContentPane().add(btnAceptar, "cell 0 11 3 1,growx,aligny bottom");
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(51, 0, 204));
		btnCancelar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/error32.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cp.dispose();
			}
		});
		getContentPane().add(btnCancelar, "cell 4 11 5 1,growx,aligny bottom");
	}
	
	private void agregaEscuchaTablaCambio(){
		
		TableModelListener tml = new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
//				JOptionPane.showMessageDialog(null, "Bien hecho!! x.x");
				int row = tablaCambio.getSelectedRow();
				double costo = Double.parseDouble(modeloCambio.getValueAt(row, 3).toString());
				int cantidad = Integer.parseInt(modeloCambio.getValueAt(row, 2).toString());
				total = costo * cantidad;
				
				
				
			}
		};
		modeloCambio.addTableModelListener(tml);
//		modeloCambio.setValueAt(total, tablaCambio.getSelectedRow(), 3);
		modeloCambio.removeTableModelListener(tml);
	}
}

