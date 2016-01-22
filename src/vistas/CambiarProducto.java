package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextArea;

import java.awt.SystemColor;

import javax.swing.JScrollPane;

import controllers.Cambio;

import javax.swing.JTable;

import ticket.Ticket;

import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class CambiarProducto extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private class Vista extends JFrame {

		private static final long serialVersionUID = 1L;
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
			setBounds(100, 100, 650, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new MigLayout("", "[812px,grow]", "[][29.00px][27.00px][grow][29.00][29.00][29.00,grow,top][21.00][grow][][grow]"));
			contentPane.setBackground(Color.BLACK);
			setIconImage(Toolkit.getDefaultToolkit().getImage(Vista.class.getResource("/imagenes/Shopping48.png")));
			setTitle("Modelo a cambio");
			setContentPane(contentPane);
			
			JLabel lblModelo = new JLabel("Modelo");
			lblModelo.setForeground(Color.WHITE);
			lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPane.add(lblModelo, "flowx,cell 0 1,alignx center");
			
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent key) {
					if(key.getKeyChar()=='\n'){
						if (!textField.getText().isEmpty()) {
							
							if(bandera) contentPane.remove(scrollPane);
							
							datos = cambios.busca_modelo(textField.getText());
							model = new DefaultTableModel(datos, cabecera){
								private static final long serialVersionUID = 1L;

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
				public void mousePressed(MouseEvent click) {
					if(click.getClickCount()==2){
						int renglon = tablaModelo.getSelectedRow();
						if(Integer.parseInt(model.getValueAt(renglon, 4).toString())==0){
							JOptionPane.showMessageDialog(contentPane, "Ya no hay existencias\nde este modelo","Agotado",JOptionPane.WARNING_MESSAGE);
							return;
						}
						double precioProducto = Double.parseDouble(modelCambios.getValueAt(renglonCambio, 3).toString());
						double diferencia=0, precio;
						String[][] datos = {{model.getValueAt(renglon, 0).toString(),model.getValueAt(renglon, 1).toString(),""+1,model.getValueAt(renglon, 2).toString(),model.getValueAt(renglon, 3).toString(),model.getValueAt(renglon, 5).toString()}};
						
						if(bandera2==2){
							modeloCambio.removeTableModelListener(tml);
							datosTablaCambio = datos;
							modeloCambio.addRow(datos[0]);
							scrollCambio.updateUI();
							modeloCambio.addTableModelListener(tml);
							
						}else if(bandera2==0){
							datosTablaCambio = datos;
							modeloCambio = new DefaultTableModel(datosTablaCambio,cabeceraCambio){
								private static final long serialVersionUID = 1L;

								@Override
								public boolean isCellEditable(int row, int col){
									return col==2 ? true:false;
								}
							};
							tml = new TableModelListener() {
								
								@Override
								public void tableChanged(TableModelEvent e) {
									int row = e.getFirstRow();
									int column = e.getColumn();
									
									if(column == 5) return;
									
									double costo = Double.parseDouble(modeloCambio.getValueAt(row, 5).toString())/numero_prendas;
									int cantidad = Integer.parseInt(modeloCambio.getValueAt(row, column).toString());
									double total = costo * cantidad;
									
									modeloCambio.setValueAt(total, row, 5);
									double precio=0;
									for (int i = 0; i < modeloCambio.getRowCount(); i++) {
										precio += Double.parseDouble(modeloCambio.getValueAt(i, 5).toString());
									}
									
									double diferencia = precio - precioProducto;
									dif = diferencia;
									textDiferencia.setText(""+diferencia);
								}
							};
							modeloCambio.addTableModelListener(tml);
							
							tablaCambio = new JTable(modeloCambio);
							tablaCambio.setBackground(new Color(176, 224, 230));
							
							bandera2=1;
							
						}
						
						precio=0;
						for (int i = 0; i < modeloCambio.getRowCount(); i++) {
							precio += Double.parseDouble(modeloCambio.getValueAt(i, 5).toString());
						}
						
						diferencia = precio - precioProducto;
						dif = diferencia;
						textDiferencia.setText(""+diferencia);
						v.dispose();
						
					}
				}
			});
		}
		

	}
	
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
	private TableModelListener tml;
	private String[] cabeceraCambio={"Modelo", "Descripcion","No. articulos","Talla","Color","Precio"};
	private String[][] datosTablaCambio;
	private JTable tablaCambio;
	private JScrollPane scrollCambio;
	private boolean bandera1=false;
	private int bandera2=0;
	
	private JButton btnAceptar;
	private JPopupMenu menuTabla;
	
	private int renglonCambio=-1;
	private int numero_prendas=0;
	
	private double dif=0;
	private JMenuItem mntmQuitar;
	private Ticket ticket;
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
						if(datos==null){
							JOptionPane.showMessageDialog(getContentPane(), "Lo sentimos!!\nLa fecha para cambio ha expirado");
							return;
						}
						textAreaDatosVenta.setText("Folio \t Fecha \t\t Total artículos\tSubtotal \t Descuento \t Total Ventas \n"+datos);
						datostable = cambios.obten_detalles_venta(folio);
						modelCambios = new DefaultTableModel(datostable,cabecera){
							private static final long serialVersionUID = 1L;

							@Override
							public boolean isCellEditable(int row, int col){
								return false;
							}
						};
						tableCambios = new JTable(modelCambios);
						tableCambios.setBackground(new Color(176, 224, 230));
						
						tableCambios.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent click) {
								if(click.getClickCount()==1){
									renglonCambio = tableCambios.getSelectedRow();
								}
								
							}
						});
						
						scrollCambios = new JScrollPane(tableCambios);
						scrollCambios.setPreferredSize(new Dimension(300, 100));
						getContentPane().add(scrollCambios,"cell 0 3 9 2,grow");
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
				
				int cantidad_art = Integer.parseInt(modelCambios.getValueAt(renglonCambio, 2).toString());
				int numero_arts = tablaCambio.getRowCount();
				boolean hecho = false;
				if(cantidad_art==1 && numero_arts==1){
					//"Eliminamos" el articulo y lo sustituimos por el articulo de cambio
					hecho=cambios.elimina_modelo_en_venta(renglonCambio,modeloCambio.getValueAt(0, 0).toString(), modeloCambio.getValueAt(0, 3).toString(), modeloCambio.getValueAt(0, 4).toString(), Integer.parseInt(modeloCambio.getValueAt(0, 2).toString()),Double.parseDouble(textDiferencia.getText()));
					
				}else if(cantidad_art==1 && numero_arts>1){
					hecho=cambios.elimina_modelo_en_venta(renglonCambio,modeloCambio.getValueAt(0, 0).toString(), modeloCambio.getValueAt(0, 3).toString(), modeloCambio.getValueAt(0, 4).toString(), Integer.parseInt(modeloCambio.getValueAt(0, 2).toString()),Double.parseDouble(textDiferencia.getText()));
					for (int i = 1; i < numero_arts; i++) {
						hecho = hecho && cambios.agrega_a_venta(modeloCambio.getValueAt(i, 0).toString(), modeloCambio.getValueAt(i, 3).toString(), modeloCambio.getValueAt(i, 4).toString(), Integer.parseInt(modeloCambio.getValueAt(i, 2).toString()));
					}
					
				}else if(cantidad_art>1 && numero_arts==1){
					
					hecho = cambios.actualiza_venta(renglonCambio, cantidad_art-1, Double.parseDouble(textDiferencia.getText())) 
							&& cambios.agrega_a_venta(modeloCambio.getValueAt(0, 0).toString(), modeloCambio.getValueAt(0, 3).toString(), modeloCambio.getValueAt(0, 4).toString(), Integer.parseInt(modeloCambio.getValueAt(0, 2).toString()));
					
				}else if(cantidad_art>1 && numero_arts>1){
					hecho = cambios.actualiza_venta(renglonCambio, cantidad_art-1, Double.parseDouble(textDiferencia.getText()));
					for (int i = 0; i < numero_arts; i++) {
						hecho = hecho && cambios.agrega_a_venta(modeloCambio.getValueAt(i, 0).toString(), modeloCambio.getValueAt(i, 3).toString(), modeloCambio.getValueAt(i, 4).toString(), Integer.parseInt(modeloCambio.getValueAt(i, 2).toString()));
					}
				}
				
				String msj = hecho ? "Cambio efectuado exitosamente\nGracias por su compra" : "Ha ocurrido un error\nPor favor vuelva a intentarlo";
				JOptionPane.showMessageDialog(getContentPane(), msj);
				if (hecho) {
					ticket=new Ticket();
					ticket.cabecera(textFolio.getText(),cambios.nombreEmpleado(),"CAMBIO");
					ticket.Item_a_cambiar(modelCambios.getValueAt(renglonCambio, 0).toString(), modelCambios.getValueAt(renglonCambio, 1).toString(),"1", modelCambios.getValueAt(renglonCambio, 3).toString());
					ticket.items_a_cambio(obten_cambios());
					ticket.cambio(""+cambios.get_total_venta(),""+ Double.parseDouble(textDiferencia.getText()),"" + (cambios.get_total_venta()+Double.parseDouble(textDiferencia.getText())));
					ticket.piePagina();
					ticket.ImprimirDocumento();
				}
				getContentPane().remove(scrollCambios);
				getContentPane().remove(scrollCambio);
				textFolio.setText("");
				textAreaDatosVenta.setText("***************************************Descripcion de la venta*************************************");
				textDiferencia.setText("");
				btnAceptar.setEnabled(false);
				dispose();
			}
		});

		btnAceptar.setBackground(new Color(51, 0, 204));
		btnAceptar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/ok32.png")));
		btnAceptar.setEnabled(false);
		getContentPane().add(btnAceptar, "cell 0 11 3 1,growx,aligny bottom");
		
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
		
		JLabel lblModeloCambio = new JLabel("Cambia por :");
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
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(51, 0, 204));
		btnCancelar.setIcon(new ImageIcon(CambiarProducto.class.getResource("/imagenes/error32.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cp.dispose();
			}
		});
		getContentPane().add(btnCancelar, "cell 4 11 5 1,growx,aligny bottom");
		
		menuTabla = new JPopupMenu();
		menuTabla.setBackground(SystemColor.inactiveCaptionBorder);
		menuTabla.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuTabla.setForeground(Color.BLACK);
		
		mntmQuitar = new JMenuItem("Quitar");
		mntmQuitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if(tablaCambio.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(getContentPane(), "Selecciona una fila");
				}else{
					double dif = Double.parseDouble(textDiferencia.getText());
					double resta = Double.parseDouble(modeloCambio.getValueAt(tablaCambio.getSelectedRow(), 5).toString());
					textDiferencia.setText(""+(dif - resta));
					modeloCambio.removeTableModelListener(tml);
					modeloCambio.removeRow(tablaCambio.getSelectedRow());
					modeloCambio.addTableModelListener(tml);
				}
				
			}
		});
		
		menuTabla.add(mntmQuitar);
	}
	
	private void agregaEscuchaTablaCambio(){
		
		tablaCambio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click) {
				if (click.getClickCount()==2) {
					numero_prendas = Integer.parseInt(modeloCambio.getValueAt(tablaCambio.getSelectedRow(), tablaCambio.getSelectedColumn()).toString());
				}
			}
		});
		
		addPopup(tablaCambio, menuTabla);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private String[][] obten_cambios(){
		int rows = tablaCambio.getRowCount();
		String[][] productos = new String[rows][];
		int j=0;
		for (int i = 0; i < rows; i++) {
			String[] p = new String[4];
			p[0] = modelCambios.getValueAt(i, 0).toString();
			p[1] = modelCambios.getValueAt(i, 1).toString();
			p[2] = modelCambios.getValueAt(i, 2).toString();
			p[3] = modelCambios.getValueAt(i, 5).toString();
			productos[j] = p;
			j++;
		}
		return productos;
	}
}

