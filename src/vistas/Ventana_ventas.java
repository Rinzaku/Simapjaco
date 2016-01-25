package vistas;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPopupMenu;

import java.awt.Component;

import javax.swing.JMenuItem;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.ArrayList;
import java.util.Arrays;

import controllers.Ventas_C;

import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;



import ticket.Ticket;

public class Ventana_ventas extends JFrame {

	private static final long serialVersionUID = 1L;

	private class ImageV extends JDialog{
		private static final long serialVersionUID = 1L;
		//private JPanel panel;
		private JLabel labelImage;
		
		
		public ImageV() {
			super();
			final JDialog ventanaImage=this;
			ventanaImage.setUndecorated(true);
			ventanaImage.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					ventanaImage.dispose();
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}

		
		public void pintaVentana(String imagen){
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(0, 0, 300, 300);
			getContentPane().setLayout(null);
			labelImage = new JLabel("");
			labelImage.setHorizontalAlignment(SwingConstants.CENTER);
			ImageIcon imagen1=new ImageIcon(imagen);
			Image conversion =imagen1.getImage();
			Image tamanio=conversion.getScaledInstance(300,300,Image.SCALE_SMOOTH);
			ImageIcon imagen2=new ImageIcon(tamanio);
			labelImage.setIcon(imagen2);
			labelImage.setBounds(0, 0, 300, 300);
			getContentPane().add(labelImage);
			bndImage=true;
						
		}
		
		public void refreshV(){
			if (bndImage) {
				getContentPane().remove(labelImage);
			}
			
		}
		
	}

	private ImageV ventanaImage;
	private JPanel contentPane;
	private JTextField textFieldModelo;
	private JTextField textFieldColor;
	private JTextField textFieldTalla;
	private JTable tableVentas;
	private JTable tableBusqueda;
	JScrollPane scrollBusqueda ;
	JMenuItem mntmQuitar;
	JMenuItem mntmApartar;
	DefaultTableModel modelVentas;
	DefaultTableModel modelBusqueda;
	JScrollPane scrollVentas;
	Ventas_C controlador_ventas;
	
	boolean bndImage=false;
	boolean bandera = false;
	int renglon = 0;
	ArrayList<Integer> ids_modelos;
	ArrayList<Integer> ids_ropas;

	private String [][] datosVentas={};
	private String [] cabeceraVentas={"Modelo","Descripcion","Talla","Color","Cantidad","Precio"};
	

	private String [][] datosBusqueda;
	private String [] cabeceraBusqueda={"Modelo","Descripcion","Talla","Color","Cantidad","Estado"};
	private JTextField txtSubTotal;
	private JTextField txtFolio;
	private JLabel etiquetaFecha;
	private JTextField textFieldRecibido;
	private JTextField textFieldCambio;
	private JTextField textFieldEmpleado;
	private JComboBox<Object> comboDescuento;
	private TableModelListener tml;
	
	private int numero_prendas=0;
	private JTextField textTotal;
	private Ticket ticket;
	
	/**
	 * Create the frame.
	 */
	public Ventana_ventas() {
		
		ventanaImage=new ImageV();
		setTitle("BOUTIQUE SIMAPJACO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_ventas.class.getResource("/imagenes/Shopping48.png")));
		final Ventana_ventas ventasV=this;
		controlador_ventas = new Ventas_C();
		ids_modelos = new ArrayList<Integer>();
		ids_ropas = new ArrayList<Integer>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1265, 642);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[812px,grow]", "[][][76.00px][27.00px][][29.00][][][29.00][29.00,grow,top][21.00][grow][][grow]"));
		
		JLabel lblFolio = new JLabel("Folio:");
		lblFolio.setForeground(Color.WHITE);
		lblFolio.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblFolio, "flowx,cell 0 0,alignx right");
		
		txtFolio = new JTextField();
		txtFolio.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtFolio.setText(""+controlador_ventas.folio());
		txtFolio.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFolio.setEditable(false);
		contentPane.add(txtFolio, "cell 0 0,alignx center,aligny center");
		txtFolio.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblFecha, "cell 0 0,alignx right");
		
		etiquetaFecha = new JLabel(controlador_ventas.fecha());
		etiquetaFecha.setForeground(Color.WHITE);
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(etiquetaFecha, "cell 0 0,alignx right");
		
		JLabel lblNewLabel = new JLabel("N\u00B0 Empleado");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel, "flowx,cell 0 1,alignx right");
		
		textFieldEmpleado = new JTextField();
		textFieldEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(textFieldEmpleado, "cell 0 1,alignx right");
		textFieldEmpleado.setColumns(10);
//		textFieldFecha.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 153));
		contentPane.add(panel, "cell 0 2,alignx center,aligny bottom");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 3.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblVentasSimapjaco = new JLabel("Ventas  Simapjaco");
		lblVentasSimapjaco.setForeground(Color.WHITE);
		lblVentasSimapjaco.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblVentasSimapjaco.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblVentasSimapjaco = new GridBagConstraints();
		gbc_lblVentasSimapjaco.gridwidth = 22;
		gbc_lblVentasSimapjaco.fill = GridBagConstraints.BOTH;
		gbc_lblVentasSimapjaco.insets = new Insets(0, 0, 5, 0);
		gbc_lblVentasSimapjaco.gridx = 0;
		gbc_lblVentasSimapjaco.gridy = 0;
		panel.add(lblVentasSimapjaco, gbc_lblVentasSimapjaco);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModelo.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblModelo = new GridBagConstraints();
		gbc_lblModelo.anchor = GridBagConstraints.EAST;
		gbc_lblModelo.gridwidth = 2;
		gbc_lblModelo.gridheight = 2;
		gbc_lblModelo.insets = new Insets(0, 0, 0, 5);
		gbc_lblModelo.gridx = 0;
		gbc_lblModelo.gridy = 1;
		panel.add(lblModelo, gbc_lblModelo);
		//pinta_productos();
		textFieldModelo = new JTextField();
		textFieldModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == '\n'){
					ejecuta_busqueda();
				}
			}
		});
		GridBagConstraints gbc_textFieldModelo = new GridBagConstraints();
		gbc_textFieldModelo.gridheight = 2;
		gbc_textFieldModelo.gridwidth = 2;
		gbc_textFieldModelo.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldModelo.gridx = 2;
		gbc_textFieldModelo.gridy = 1;
		panel.add(textFieldModelo, gbc_textFieldModelo);
		textFieldModelo.setColumns(10);
		
		JLabel lblColor = new JLabel("Color :");
		lblColor.setBackground(Color.WHITE);
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblColor.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblColor = new GridBagConstraints();
		gbc_lblColor.anchor = GridBagConstraints.EAST;
		gbc_lblColor.gridheight = 2;
		gbc_lblColor.insets = new Insets(0, 0, 0, 5);
		gbc_lblColor.gridx = 5;
		gbc_lblColor.gridy = 1;
		panel.add(lblColor, gbc_lblColor);
		
		textFieldColor = new JTextField();
		textFieldColor.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldColor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == '\n'){
					ejecuta_busqueda();
				}
			}
		});
		textFieldColor.setColumns(10);
		GridBagConstraints gbc_textFieldColor = new GridBagConstraints();
		gbc_textFieldColor.anchor = GridBagConstraints.EAST;
		gbc_textFieldColor.gridheight = 2;
		gbc_textFieldColor.gridwidth = 3;
		gbc_textFieldColor.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldColor.gridx = 6;
		gbc_textFieldColor.gridy = 1;
		panel.add(textFieldColor, gbc_textFieldColor);
		
		JLabel lblTalla = new JLabel("Talla :");
		lblTalla.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTalla.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTalla = new GridBagConstraints();
		gbc_lblTalla.anchor = GridBagConstraints.EAST;
		gbc_lblTalla.fill = GridBagConstraints.VERTICAL;
		gbc_lblTalla.gridheight = 2;
		gbc_lblTalla.insets = new Insets(0, 0, 0, 5);
		gbc_lblTalla.gridx = 10;
		gbc_lblTalla.gridy = 1;
		panel.add(lblTalla, gbc_lblTalla);
		
		textFieldTalla = new JTextField();
		textFieldTalla.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldTalla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == '\n'){
					ejecuta_busqueda();
				}
			}
		});
		textFieldTalla.setColumns(10);
		GridBagConstraints gbc_textFieldTalla = new GridBagConstraints();
		gbc_textFieldTalla.anchor = GridBagConstraints.EAST;
		gbc_textFieldTalla.gridheight = 2;
		gbc_textFieldTalla.gridwidth = 4;
		gbc_textFieldTalla.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldTalla.gridx = 11;
		gbc_textFieldTalla.gridy = 1;
		panel.add(textFieldTalla, gbc_textFieldTalla);
		
		JLabel lblNewBuscar = new JLabel("");
		lblNewBuscar.setVerticalAlignment(SwingConstants.TOP);
		lblNewBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount()==1) {
					ejecuta_busqueda();
				}
			}
		});
		lblNewBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewBuscar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/search64.png")));
		GridBagConstraints gbc_lblNewBuscar = new GridBagConstraints();
		gbc_lblNewBuscar.anchor = GridBagConstraints.NORTH;
		gbc_lblNewBuscar.gridheight = 2;
		gbc_lblNewBuscar.gridwidth = 5;
		gbc_lblNewBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewBuscar.gridx = 16;
		gbc_lblNewBuscar.gridy = 1;
		panel.add(lblNewBuscar, gbc_lblNewBuscar);
		
		modelVentas = new DefaultTableModel(datosVentas,cabeceraVentas){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col){
				if(col==4) return true;
				else return false;
			}
		};
		
		tml = new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
				
				if (col == 5) return;
				
				double costo = Double.parseDouble(modelVentas.getValueAt(row, 5).toString())/numero_prendas;
				int cantidad = Integer.parseInt(modelVentas.getValueAt(row, 4).toString());
				double total = costo * cantidad;
				
				modelVentas.setValueAt(total, row, 5);
				double newTot =0;
				for (int i = 0; i < tableVentas.getRowCount(); i++) {
					newTot += Double.parseDouble(modelVentas.getValueAt(i, 5).toString());
				}
				txtSubTotal.setText(""+newTot);
				textTotal.setText(""+newTot);
			}
		};
		
		modelVentas.addTableModelListener(tml);
		
		tableVentas = new JTable(modelVentas);
		
		tableVentas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode()==KeyEvent.VK_ENTER || key.getKeyCode() == KeyEvent.VK_TAB) {
					modelVentas.addRow(new String[]{"","","","","",""});
					tableVentas.setModel(modelVentas);
				}
			}
		});
		tableVentas.addMouseListener(new MouseAdapter() {
			@Override
			//evento para controlar  los clicks de la tabla ^.^/
			public void mousePressed(MouseEvent click) {
			
				if (click.getClickCount()==2) {
					numero_prendas = Integer.parseInt(modelVentas.getValueAt(tableVentas.getSelectedRow(), 4).toString());
				}
			}
		});
		
		scrollVentas=new JScrollPane (tableVentas);
		
		tableVentas.setBackground(new Color(176, 224, 226));
		tableVentas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.BOTTOM, null, null));
		tableVentas.setSelectionBackground(Color.cyan);
		tableVentas.setRowHeight(17);		
		
		JPopupMenu menuTabla = new JPopupMenu();
		menuTabla.setBackground(SystemColor.inactiveCaptionBorder);
		menuTabla.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuTabla.setForeground(Color.BLACK);
		addPopup(tableVentas, menuTabla);
		
		mntmQuitar = new JMenuItem("Quitar");
		mntmQuitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if(tableVentas.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Selecciona una fila");
				}else{
					System.out.println("Fila -> "+tableVentas.getSelectedRow());
					double total = Double.parseDouble(txtSubTotal.getText());
					total -= Double.parseDouble(modelVentas.getValueAt(tableVentas.getSelectedRow(), 5).toString());
					txtSubTotal.setText(""+total);
					textTotal.setText(""+total);
					modelVentas.removeTableModelListener(tml);
					modelVentas.removeRow(tableVentas.getSelectedRow());
					modelVentas.addTableModelListener(tml);
					
					
				}
				
			}
		});
		
		menuTabla.add(mntmQuitar);
		
		mntmApartar= new JMenuItem("Apartar");
		mntmApartar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Apartar:"+" "+e.getClickCount());
				if(tableVentas.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Selecciona una fila");
				}else{

					int pos =tableVentas.getSelectedRow();
					String folio=txtFolio.getText();
					String modelo= (String) tableVentas.getValueAt(pos, 0);
					String  descripcion =(String) tableVentas.getValueAt(pos,1);
					String talla =(String) tableVentas.getValueAt(pos,2);
					String Color =(String) tableVentas.getValueAt(pos, 3);
					String cantidad = tableVentas.getValueAt(pos, 4).toString();
					String precio=tableVentas.getValueAt(pos,5).toString();
					if(textFieldEmpleado.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Ingresa tú identificador de empleado");
						
					}
					else{
						ApartarProducto apartarProd =new ApartarProducto(modelo.toUpperCase(),descripcion.toUpperCase(),talla.toUpperCase(),Color.toUpperCase(),cantidad,precio,folio,ids_modelos.get(0),ids_ropas.get(0),ventasV,Integer.parseInt(textFieldEmpleado.getText()));
						apartarProd.setVisible(true);
					}
					

				}
			}
		});
		menuTabla.add(mntmApartar);
		contentPane.add(scrollVentas, "cell 0 4,grow");
		scrollVentas.setPreferredSize(new Dimension(350, 150));
		
		JLabel lblSubTotal = new JLabel("Subtotal:  $");
		lblSubTotal.setForeground(Color.WHITE);
		lblSubTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblSubTotal, "flowx,cell 0 5,alignx right");
		
		txtSubTotal = new JTextField();
		txtSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(txtSubTotal, "cell 0 5,alignx right,aligny center");
		txtSubTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total: $");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setForeground(Color.WHITE);
		contentPane.add(lblTotal, "flowx,cell 0 7,alignx right");
		
		textTotal = new JTextField();
		textTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		textTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTotal.setEditable(false);
		contentPane.add(textTotal, "cell 0 7,alignx right,aligny top");
		textTotal.setColumns(10);
		
		JLabel lbelRecibido = new JLabel("Recibido:  $");
		lbelRecibido.setForeground(Color.WHITE);
		lbelRecibido.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbelRecibido, "flowx,cell 0 8,alignx right,aligny top");
		
		textFieldRecibido = new JTextField();
		textFieldRecibido.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldRecibido.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(textFieldRecibido, "cell 0 8,alignx right,aligny top");
		textFieldRecibido.setColumns(10);
		textFieldRecibido.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					if (!textFieldRecibido.getText().isEmpty()) {
						double recibido = Double.parseDouble(textFieldRecibido.getText());
						double cambio =controlador_ventas.cambio(recibido, Double.parseDouble(textTotal.getText()));
						cambio = Math.floor(cambio * 100) / 100;
						textFieldCambio.setText(""+cambio);
						textFieldRecibido.setText(""+recibido);
						contentPane.remove(scrollBusqueda);
						contentPane.updateUI();
					}

				}
			}
		});
		
		JLabel lblCambio = new JLabel("Cambio:  $");
		lblCambio.setForeground(Color.WHITE);
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblCambio, "flowx,cell 0 9,alignx right");
		
		textFieldCambio = new JTextField();
		textFieldCambio.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldCambio.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(textFieldCambio, "cell 0 9,alignx right,aligny top");
		textFieldCambio.setColumns(10);

		
		JPanel panelBoton = new JPanel();
		panelBoton.setBackground(SystemColor.black);
		contentPane.add(panelBoton, "cell 0 12 1 2,growx,aligny bottom");
		panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBotonAdmin = new JButton("");
		btnBotonAdmin.setToolTipText("Administrador");
		btnBotonAdmin.setBackground(new Color(0, 51, 153));
		btnBotonAdmin.setForeground(Color.WHITE);
		btnBotonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login=new Login();
				login.setVisible(true);
			}
		});
			
		btnBotonAdmin.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/user32.png")));
		panelBoton.add(btnBotonAdmin);
		
		JButton btnCambiarProd = new JButton("");
		btnCambiarProd.setToolTipText("Cambio de producto");
		btnCambiarProd.setBackground(new Color(0, 51, 153));
		btnCambiarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambiarProducto apartado =new  CambiarProducto();
				apartado.setVisible(true);
			}
		});
		
		JButton buttonApartar = new JButton("");
		buttonApartar.setToolTipText("Apartar producto");
		buttonApartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaApartados vtnApartados =new VentanaApartados();
				vtnApartados.setVisible(true);
			}
		});
		buttonApartar.setBackground(new Color(0, 51, 153));
		buttonApartar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/apartados.png")));
		panelBoton.add(buttonApartar);
		btnCambiarProd.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/cambio32.png")));
		panelBoton.add(btnCambiarProd);
		
		JButton btnFinalizar = new JButton("");
		btnFinalizar.setToolTipText("Finalizar venta");
		btnFinalizar.setBackground(new Color(0, 51, 153));
		btnFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Finalizar venta");
				int total_articulos=0;
				int id_ventas=0;
				boolean exito=false;
				int cantidad_modelo=0; 
				for (int k = 0; k < tableVentas.getRowCount(); k++) {
					cantidad_modelo = Integer.parseInt(modelVentas.getValueAt(k, 4).toString()); 
					if(controlador_ventas.hayExistencias(ids_modelos.get(k), cantidad_modelo)){
						total_articulos+=cantidad_modelo;
					}else{
						String modelo = controlador_ventas.dameModelo(ids_modelos.get(k));
						JOptionPane.showMessageDialog(contentPane, "La cantidad a vender del modelo:\n"+modelo+"\nexcede las existencias","Agotado",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
				}
				if(!textFieldEmpleado.getText().isEmpty()){
					if(controlador_ventas.exite_empleado(Integer.parseInt(textFieldEmpleado.getText()))){
						id_ventas = controlador_ventas.creaVenta(etiquetaFecha.getText(), total_articulos, Double.parseDouble(txtSubTotal.getText()),Integer.parseInt(textFieldEmpleado.getText()),Double.parseDouble(comboDescuento.getSelectedItem().toString()),Double.parseDouble(textTotal.getText()));
						if(id_ventas>0){
							for (int i = 0; i < tableVentas.getRowCount(); i++) {
								exito=controlador_ventas.creaDetalleVenta(id_ventas, ids_modelos.get(i), ids_ropas.get(i), Integer.parseInt(modelVentas.getValueAt(i, 4).toString()), Double.parseDouble((modelVentas.getValueAt(i, 5).toString())));
								if(!exito) break;
							}
							if(exito){
								ticket=new Ticket();
								ticket.cabecera(txtFolio.getText(),controlador_ventas.nombreEmpleado(Integer.parseInt(textFieldEmpleado.getText())),"VENTA");
								ticket.Items(venta());
								ticket.total(txtSubTotal.getText(), textTotal.getText(), comboDescuento.getSelectedItem().toString(), textFieldRecibido.getText(), textFieldCambio.getText());
								ticket.piePagina();
								ticket.ImprimirDocumento();
//								ticket.corta();
//								ticket.cortar();
								//System.out.println("VEntas :"+Arrays.deepToString(venta()));
								JOptionPane.showMessageDialog(contentPane, "Venta realizada exitosamente\nGracias por su compra","Venta existosa!",JOptionPane.INFORMATION_MESSAGE);
							}else
								JOptionPane.showMessageDialog(contentPane, "A ourrido un error. No se ha podido crear la venta");
						}else{
							JOptionPane.showMessageDialog(contentPane, "A ourrido un error. No se ha podido crear la venta");
						}
						limpiaVentana();
					}else{
						JOptionPane.showMessageDialog(contentPane, "El empleado introducido no existe!!", "Empleado inexistente",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(contentPane, "Por favor introduce tu numero de empleado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnFinalizar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/ok32.png")));
		panelBoton.add(btnFinalizar);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setToolTipText("Cancelar venta");
		btnCancelar.setBackground(new Color(0, 51, 153));
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textFieldEmpleado.getText().isEmpty() || modelBusqueda.getRowCount()!=0) {
					limpiaVentana();
				}
				else
					ventasV.dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/error32.png")));
		panelBoton.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("Descuento %:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel_1, "flowx,cell 0 6,alignx right");
		
		comboDescuento = new JComboBox<Object>();
		comboDescuento.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboDescuento.setBackground(Color.WHITE);
		comboDescuento.setForeground(Color.BLACK);
		comboDescuento.setModel(new DefaultComboBoxModel<Object>(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboDescuento.setSelectedIndex(0);
		contentPane.add(comboDescuento, "cell 0 6,alignx right");
		
		agregaAccionACombobox();
		
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
	
	private void addMouseClick(){
		
		tableBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			//evento para controlar  los clicks de la tabla ^.^/
			public void mousePressed(MouseEvent click) {
			
				if (click.getClickCount()==2) {
					if (modelBusqueda.getValueAt(tableBusqueda.getSelectedRow(), 5).toString().compareTo("ELIMINADO")==0) {
						JOptionPane.showMessageDialog(contentPane, "Producto fuera de sistema","Eliminado",JOptionPane.WARNING_MESSAGE);
						return;
					}
					int existencias = Integer.parseInt(modelBusqueda.getValueAt(tableBusqueda.getSelectedRow(), 4).toString());
					if(existencias == 0){
						JOptionPane.showMessageDialog(contentPane, "Ya no hay existencias\nde este modelo","Agotado",JOptionPane.WARNING_MESSAGE);
						return;
					}
					modelVentas.removeTableModelListener(tml);
					
					String[] fila = new String[tableBusqueda.getColumnCount()];
					TableModel tmodel = tableBusqueda.getModel();
					renglon=tableBusqueda.getSelectedRow();
					String[] precios = controlador_ventas.obten_precios();
					int[][] ids = controlador_ventas.obten_identificadores();
					
					for (int j = 0; j < fila.length-1; j++) {
						if(j==4) fila[j]="1";
						else fila[j]=tmodel.getValueAt(renglon, j).toString();
					}
					
					existencias -=1;
					tmodel.setValueAt(existencias, renglon, 4);
					
					fila[fila.length-1]= precios[renglon];
					ids_modelos.add(ids[renglon][0]);
					ids_ropas.add(ids[renglon][1]);
					
					modelVentas.addRow(fila);
					
					if (txtSubTotal.getText().isEmpty()) {
//						JOptionPane.showMessageDialog(null, "Campo total vacio");
						txtSubTotal.setText(precios[renglon]);
						textTotal.setText(precios[renglon]);
					} else {
//						JOptionPane.showMessageDialog(null, "Hay datos");
						double total = Double.parseDouble(txtSubTotal.getText());
						total +=Double.parseDouble(precios[renglon]);
						txtSubTotal.setText(""+total);
						textTotal.setText(""+total);
					}
					modelVentas.addTableModelListener(tml);
					contentPane.updateUI();
				}
			}
		});
	}
	
	private void agregaAccionACombobox(){
		comboDescuento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtSubTotal.getText().isEmpty()){
					double desc = Double.parseDouble(comboDescuento.getSelectedItem().toString())/100;
					double subtotal = Double.parseDouble(txtSubTotal.getText());
					double total = subtotal - (subtotal * desc);
					textTotal.setText(""+total);
					if(!textFieldRecibido.getText().isEmpty()){
						double recibido = Double.parseDouble(textFieldRecibido.getText());
						double cambio =controlador_ventas.cambio(recibido, Double.parseDouble(textTotal.getText()));
						cambio = Math.floor(cambio * 100) / 100;
						textFieldCambio.setText(""+cambio);
						textFieldRecibido.setText(""+recibido);
					}
				}
			}
		});
	}
	
	public void limpiaVentana(){
		modelVentas.removeTableModelListener(tml);
		for (int i = tableVentas.getRowCount()-1; i>=0; i--) {
			modelVentas.removeRow(i);	
		}

		txtFolio.setText(""+controlador_ventas.folio());
		textFieldRecibido.setText("");
		textFieldCambio.setText("");
		txtSubTotal.setText("");
		textFieldModelo.setText("");
		textFieldColor.setText("");
		textFieldTalla.setText("");
		textTotal.setText("");
		comboDescuento.setSelectedIndex(0);
		contentPane.remove(scrollBusqueda);
		contentPane.updateUI();



	}
	
	private void ejecuta_busqueda(){
		if(textFieldModelo.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Ingresa un modelo");
			if(bandera){
				contentPane.remove(scrollBusqueda);
				contentPane.updateUI();
				bandera = false;
			}
			return;
		}
		String modelo = textFieldModelo.getText().isEmpty() ? "" : textFieldModelo.getText();
		String talla = textFieldTalla.getText().isEmpty() ? "" : textFieldTalla.getText();
		String color = textFieldColor.getText().isEmpty() ? "" : textFieldColor.getText();
		if(bandera){
			contentPane.remove(scrollBusqueda);
		}
		textFieldModelo.setText("");
		textFieldColor.setText("");
		textFieldTalla.setText("");
		datosBusqueda = controlador_ventas.busca_modelo(modelo.toUpperCase(),talla.toUpperCase(),color.toUpperCase());
		if (datosBusqueda.length==0) {
			JOptionPane.showMessageDialog(null, "No se encontró el producto  \n Ingrese bien los datos");
		}else{
			ventanaImage.refreshV();
			ventanaImage.pintaVentana(datosBusqueda[0][6]);
			ventanaImage.setVisible(true);
			pinta_productos();
			addMouseClick();
		}
		System.out.println(Arrays.deepToString(datosBusqueda));
		
		
	}
	
	private void pinta_productos() {
		modelBusqueda = new DefaultTableModel(datosBusqueda,cabeceraBusqueda){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		tableBusqueda = new JTable(modelBusqueda);	
		tableBusqueda.setBackground(new Color(176, 224, 230));
		tableBusqueda.setSelectionBackground(Color.cyan);
		tableBusqueda.isCellEditable(0,	0);
		
		scrollBusqueda= new JScrollPane(tableBusqueda);
		scrollBusqueda.setPreferredSize(new Dimension(400, 150));
		
		contentPane.add(scrollBusqueda, "cell 0 11,grow");
		scrollBusqueda.setVisible(true);
		
		contentPane.updateUI();
		bandera = true;
		
	}
	
	public String[][] venta(){
		String [] [] datos=new String[tableVentas.getRowCount()][tableVentas.getColumnCount()]; 
		for (int i = 0; i < tableVentas.getRowCount(); i++) {
			for (int j = 0; j < tableVentas.getColumnCount(); j++) {
				datos[i][0]=modelVentas.getValueAt(i, 0).toString();
				datos[i][1]=modelVentas.getValueAt(i, 1).toString();
				datos[i][2]=modelVentas.getValueAt(i, 2).toString();
				datos[i][3]=modelVentas.getValueAt(i, 3).toString();
				datos[i][4]=modelVentas.getValueAt(i, 4).toString();
				datos[i][5]=modelVentas.getValueAt(i, 5).toString();
			}
			
		}
		
		return datos;
	}
	
}