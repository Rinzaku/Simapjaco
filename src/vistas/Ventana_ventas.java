package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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









import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPopupMenu;

import java.awt.Component;

import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import controllers.Ventas_C;

public class Ventana_ventas extends JFrame {


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
	
	boolean bandera = false;
	int renglon = 0;
	ArrayList<Integer> ids_modelos;
	ArrayList<Integer> ids_ropas;
	
//	private String [][] datosVentas={{"holq","ceci","jhdgdhdj","ieyrhb","xcjvbj","hxcbv"},
//			{"","","","","",""},
//			{"","","","","",""},
//			{"","","","","",""},};
	private String [][] datosVentas={};
	private String [] cabeceraVentas={"Modelo","Descripcion","Talla","Color","Cantidad","Precio"};
	

	private String [][] datosBusqueda;
	private String [] cabeceraBusqueda={"Modelo","Descripcion","Talla","Color","Cantidad","Estado"};
	private JTextField txtTotal;
	private JTextField txtFolio;
	private JLabel etiquetaFecha;
	private JTextField textFieldRecibido;
	private JTextField textFieldCambio;

	
	  
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Ventana_ventas frame = new Ventana_ventas();
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
	public Ventana_ventas() {
		
		controlador_ventas = new Ventas_C();
		ids_modelos = new ArrayList<Integer>();
		ids_ropas = new ArrayList<Integer>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1265, 642);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[812px,grow]", "[][76.00px][27.00px][][29.00][29.00][29.00,grow,top][21.00][grow][][grow]"));
		
		JLabel lblFolio = new JLabel("Folio:");
		lblFolio.setForeground(Color.WHITE);
		lblFolio.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblFolio, "flowx,cell 0 0,alignx right");
		
		txtFolio = new JTextField();
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
//		textFieldFecha.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 153));
		contentPane.add(panel, "cell 0 1,alignx center,aligny bottom");
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
		
		textFieldModelo = new JTextField();
		textFieldModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == '\n'){
					if(!textFieldModelo.getText().isEmpty() && textFieldTalla.getText().isEmpty() && textFieldColor.getText().isEmpty()){
						if(bandera){
							contentPane.remove(scrollBusqueda);
						}
						String modelo = textFieldModelo.getText();
						
						datosBusqueda = controlador_ventas.busca_modelo(modelo);
						
						System.out.println(Arrays.deepToString(datosBusqueda));
						
						modelBusqueda = new DefaultTableModel(datosBusqueda,cabeceraBusqueda){
							@Override
							public boolean isCellEditable(int row, int col){
								return false;
							}
						};
						tableBusqueda = new JTable(modelBusqueda);	
						tableBusqueda.setBackground(new Color(176, 224, 230));
						tableBusqueda.isCellEditable(0,	0);
						
						scrollBusqueda= new JScrollPane(tableBusqueda);
						scrollBusqueda.setPreferredSize(new Dimension(400, 150));
						
						contentPane.add(scrollBusqueda, "cell 0 8,grow ");
						scrollBusqueda.setVisible(true);
						
						contentPane.updateUI();
						bandera = true;
						addMouseClick();
					}else{
						JOptionPane.showMessageDialog(null, "Ingresa un modelo");
					}
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
					if(!textFieldModelo.getText().isEmpty() && textFieldTalla.getText().isEmpty() && textFieldColor.getText().isEmpty()){
						if(bandera){
							contentPane.remove(scrollBusqueda);
						}
						String modelo = textFieldModelo.getText();
						
						datosBusqueda = controlador_ventas.busca_modelo(modelo);
						System.out.println(Arrays.deepToString(datosBusqueda));
						
						modelBusqueda = new DefaultTableModel(datosBusqueda,cabeceraBusqueda){
							@Override
							public boolean isCellEditable(int row, int col){
								return false;
							}
						};
						tableBusqueda = new JTable(modelBusqueda);
						tableBusqueda.setBackground(new Color(176, 224, 230));
						
						scrollBusqueda= new JScrollPane(tableBusqueda);
						scrollBusqueda.setPreferredSize(new Dimension(400, 150));
						
						contentPane.add(scrollBusqueda, "cell 0 8,grow ");
						scrollBusqueda.setVisible(true);
						
						contentPane.updateUI();
						bandera = true;
						addMouseClick();
					}else{
						JOptionPane.showMessageDialog(null, "Ingresa un modelo");
					}
					
					
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
			@Override
			public boolean isCellEditable(int row, int col){
				if(col==4) return true;
				else return false;
			}
		};
//		modelBusqueda=new DefaultTableModel(datosBusqueda,cabeceraBusqueda);
		
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
					//si son 2 clicks se elimina un producto de la tabla
					for (int i = 0; i < cabeceraVentas.length; i++) {
						System.out.println(tableVentas.getValueAt(tableVentas.getSelectedRow(), i));

					}
					ReporteDia dia=new ReporteDia();
					dia.setVisible(true);
					
				}
				else{
					System.out.println("No presionaste el double click");
					//para obtener el numero de filas es getRowCount
					System.out.println("Filas:"+ tableVentas.getRowCount());
				}
			}
		});
		
		scrollVentas=new JScrollPane (tableVentas);
		
		tableVentas.setBackground(new Color(176, 224, 226));
		tableVentas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.BOTTOM, null, null));
		tableVentas.setSelectionBackground(Color.MAGENTA);
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
//				System.out.println("quitar"+" "+e.getClickCount());
				
//				JOptionPane.showMessageDialog(null, "Fila seleccionada "+tableVentas.getSelectedRow());
				if(tableVentas.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "Selecciona una fila");
				}else{
					System.out.println("Fila -> "+tableVentas.getSelectedRow());
					double total = Double.parseDouble(txtTotal.getText());
					total -= Double.parseDouble(modelVentas.getValueAt(tableVentas.getSelectedRow(), 5).toString());
					txtTotal.setText(""+total);
					
					modelVentas.removeRow(tableVentas.getSelectedRow());
//					ids_modelos.remove(tableVentas.getSelectedRow());
//					ids_ropas.remove(tableVentas.getSelectedRow());
					
				}
				
			}
		});
		
		menuTabla.add(mntmQuitar);
		
		mntmApartar= new JMenuItem("Apartar");
		mntmApartar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Apartar:"+" "+e.getClickCount());
				CambiarProducto apart=new CambiarProducto();
				apart.setVisible(true);
			}
		});
		menuTabla.add(mntmApartar);
		contentPane.add(scrollVentas, "cell 0 3,grow");
		scrollVentas.setPreferredSize(new Dimension(400, 150));
		
		JLabel lblTotal = new JLabel("Total:  $");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTotal, "flowx,cell 0 4,alignx right");
		
		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setEditable(false);
		contentPane.add(txtTotal, "cell 0 4,alignx right,aligny center");
		txtTotal.setColumns(10);
		
		JLabel lbelRecibido = new JLabel("Recibido:  $");
		lbelRecibido.setForeground(Color.WHITE);
		lbelRecibido.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbelRecibido, "flowx,cell 0 5,alignx right,aligny top");
		
		textFieldRecibido = new JTextField();
		textFieldRecibido.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(textFieldRecibido, "cell 0 5,alignx right,aligny top");
		textFieldRecibido.setColumns(10);
		
		JLabel lblCambio = new JLabel("Cambio:  $");
		lblCambio.setForeground(Color.WHITE);
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblCambio, "flowx,cell 0 6,alignx right");
		
		textFieldCambio = new JTextField();
		textFieldCambio.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(textFieldCambio, "cell 0 6,alignx right,aligny top");
		textFieldCambio.setColumns(10);

		
		JPanel panelBoton = new JPanel();
		panelBoton.setBackground(SystemColor.black);
		contentPane.add(panelBoton, "cell 0 9 1 2,growx,aligny bottom");
		panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBotonAdmin = new JButton("");
		btnBotonAdmin.setBackground(new Color(51, 0, 204));
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
		btnCambiarProd.setBackground(new Color(0, 51, 153));
		btnCambiarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambiarProducto apartado =new  CambiarProducto();
				apartado.setVisible(true);
			}
		});
		btnCambiarProd.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/cambio32.png")));
		panelBoton.add(btnCambiarProd);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setBackground(new Color(0, 51, 153));
		btnCancelar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/error32.png")));
		panelBoton.add(btnCancelar);
		
		JButton btnFinalizar = new JButton("");
		btnFinalizar.setBackground(new Color(0, 51, 153));
		btnFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Finalizar venta");
				
				
			}
		});
		btnFinalizar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/ok32.png")));
		panelBoton.add(btnFinalizar);
		
		JButton button = new JButton("");
		button.setBackground(new Color(51, 0, 204));
		button.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/apartados.png")));
		panelBoton.add(button);
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
//					JOptionPane.showMessageDialog(null, "Hay doble click en la fila: "+tableBusqueda.getSelectedRow()+", y columna: "+tableBusqueda.getSelectedColumn());
					String[] fila = new String[tableBusqueda.getColumnCount()];
					TableModel tmodel = tableBusqueda.getModel();
					renglon=tableBusqueda.getSelectedRow();
					String[] precios = controlador_ventas.obten_precios();
					int[][] ids = controlador_ventas.obten_identificadores();
					
					for (int j = 0; j < fila.length-1; j++) {
						if(j==4) fila[j]="1";
						else fila[j]=tmodel.getValueAt(renglon, j).toString();
					}
					
					int existencias = Integer.parseInt(tmodel.getValueAt(renglon, 4).toString());
					existencias -=1;
					tmodel.setValueAt(existencias, renglon, 4);
					
					fila[fila.length-1]= precios[renglon];
					ids_modelos.add(ids[renglon][0]);
					ids_ropas.add(ids[renglon][1]);
					
					modelVentas.addRow(fila);
					
					if (txtTotal.getText().isEmpty()) {
//						JOptionPane.showMessageDialog(null, "Campo total vacio");
						txtTotal.setText(precios[renglon]);
					} else {
//						JOptionPane.showMessageDialog(null, "Hay datos");
						double total = Double.parseDouble(txtTotal.getText());
						total +=Double.parseDouble(precios[renglon]);
						txtTotal.setText(""+total);
					}
					contentPane.updateUI();
				}
				else{
					System.out.println("No presionaste el double click");
					//para obtener el numero de filas es getRowCount
					System.out.println("Filas:"+ tableVentas.getRowCount());
				}
			}
		});
	}
	
}
