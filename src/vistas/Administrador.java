package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;



















import net.miginfocom.swing.MigLayout;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;

import javax.swing.JSplitPane;

import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controllers.Cuenta_c;
import controllers.Productos_admin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JComboBox;

import java.awt.Toolkit;

public class Administrador extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldModelo;
	private JTextField textFieldColor;
	private JTextField textFieldTalla;
	private JTable tableAdministrador;
	private JScrollPane ScrollAdministrador;
	private JLabel lblNewBuscar;
	DefaultTableModel model;
	private Productos_admin productos;
	private String [][] datos={{"","","","","","","",""},
							  };
//	private String [][] datos;
	private String [] cabecera={"Modelo","Nombre Producto","Descripcion","Talla","Color","Cantidad","Precio","Imagen"};
	
	private TableModelListener tml;
	private TableModelListener tml_precio;
	private boolean bandera=false;
	
	private JMenuItem mnEliminar;
	
	private String cuentaDia = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrador frame = new Administrador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Administrador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Administrador.class.getResource("/imagenes/Shopping48.png")));
		productos=new Productos_admin();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1027, 480);
		setTitle("Ventana Administrador");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmInventario = new JMenuItem("Inventario");
		mntmInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteDia inventario=new ReporteDia("Inventario",1,"");
				inventario.setVisible(true);
			}
		});
		
		mnMenu.add(mntmInventario);
		
		JMenuItem mntmReporteDelDia = new JMenuItem("Reporte del dia");
		mntmReporteDelDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteDia reporteDia=new ReporteDia("Reporte del dia",2,cuentaDia);//Usamos el campo del mes(que este reporte no usa) para ingresar lo que se deja a cuenta
				reporteDia.setVisible(true);
			}
		});
		mnMenu.add(mntmReporteDelDia);
		
		JMenuItem mntmReporteDelMes = new JMenuItem("Reporte del mes");
		mntmReporteDelMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
				int mes_actual= productos.get_mes();
				String mes =(String)JOptionPane.showInputDialog(contentPane, "Reporte del mes ..", "Elige un mes", JOptionPane.PLAIN_MESSAGE, null, options, options[mes_actual]);
				if(mes!=null){
					int anio_actual = productos.get_anio();
//					String año=JOptionPane.showInputDialog(contentPane, "Introduce el año");
					String anio = (String) JOptionPane.showInputDialog(contentPane, "Introduce el año","Elige año",JOptionPane.PLAIN_MESSAGE,null,null, ""+anio_actual);
					String mes_anio = "%/"+obten_mes(mes)+"/"+anio;
					ReporteDia reporteMes=new ReporteDia("Reporte del mes",3,mes_anio);
					reporteMes.setVisible(true);
				}
			}
		});
		mnMenu.add(mntmReporteDelMes);
		
		JMenuItem mntmEmpleados = new JMenuItem("Empleados");
		mntmEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleados Empleados=new Empleados();
				Empleados.setVisible(true);
			}
		});
		mnMenu.add(mntmEmpleados);
		
		JMenuItem mntmCuenta = new JMenuItem("Cuenta");
		mntmCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentaDia = (String) JOptionPane.showInputDialog(contentPane, "Introduce la cuenta para el dia de hoy", "Cuenta",JOptionPane.PLAIN_MESSAGE,null,null,"");
				boolean listo = new Cuenta_c().insertaCuenta(productos.fecha(), Double.parseDouble(cuentaDia))== -1 ? false : true;
				String msj = listo ? "Cuenta almacenada en la base de datos" : "A ocurrido un error";
				JOptionPane.showMessageDialog(contentPane, msj, "Hecho", JOptionPane.INFORMATION_MESSAGE);
				mntmCuenta.setEnabled(false);
			}
		});
		mnMenu.add(mntmCuenta);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[812px,grow]", "[][0.00][71px][-5.00][8.00][][][][][][][][grow]"));
		
		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblFecha, "flowx,cell 0 0,alignx right");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 153));
		contentPane.add(panel, "cell 0 2,alignx center,aligny bottom");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 3.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblVentasSimapjaco = new JLabel("Administrador");
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
		gbc_lblModelo.gridwidth = 2;
		gbc_lblModelo.gridheight = 2;
		gbc_lblModelo.insets = new Insets(0, 0, 0, 5);
		gbc_lblModelo.gridx = 0;
		gbc_lblModelo.gridy = 1;
		panel.add(lblModelo, gbc_lblModelo);
		
		textFieldModelo = new JTextField();
		GridBagConstraints gbc_textFieldModelo = new GridBagConstraints();
		gbc_textFieldModelo.gridheight = 2;
		gbc_textFieldModelo.gridwidth = 2;
		gbc_textFieldModelo.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldModelo.gridx = 2;
		gbc_textFieldModelo.gridy = 1;
		panel.add(textFieldModelo, gbc_textFieldModelo);
		textFieldModelo.setColumns(10);
		textFieldModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == '\n'){
					buscar();
				}
			}
		});
		JLabel lblColor = new JLabel("Color :");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblColor.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblColor = new GridBagConstraints();
		gbc_lblColor.gridheight = 2;
		gbc_lblColor.insets = new Insets(0, 0, 0, 5);
		gbc_lblColor.gridx = 5;
		gbc_lblColor.gridy = 1;
		panel.add(lblColor, gbc_lblColor);
		
		textFieldColor = new JTextField();
		textFieldColor.setColumns(10);
		textFieldColor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldColor = new GridBagConstraints();
		gbc_textFieldColor.gridheight = 2;
		gbc_textFieldColor.gridwidth = 3;
		gbc_textFieldColor.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldColor.gridx = 6;
		gbc_textFieldColor.gridy = 1;
		panel.add(textFieldColor, gbc_textFieldColor);
		
		textFieldColor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == '\n'){
					buscar();
				}
			}
		});
		
		JLabel lblTalla = new JLabel("Talla :");
		lblTalla.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTalla.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTalla = new GridBagConstraints();
		gbc_lblTalla.fill = GridBagConstraints.BOTH;
		gbc_lblTalla.gridheight = 2;
		gbc_lblTalla.insets = new Insets(0, 0, 0, 5);
		gbc_lblTalla.gridx = 10;
		gbc_lblTalla.gridy = 1;
		panel.add(lblTalla, gbc_lblTalla);
		
		textFieldTalla = new JTextField();
		textFieldTalla.setColumns(10);
		textFieldTalla.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldTalla = new GridBagConstraints();
		gbc_textFieldTalla.gridheight = 2;
		gbc_textFieldTalla.gridwidth = 4;
		gbc_textFieldTalla.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldTalla.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTalla.gridx = 11;
		gbc_textFieldTalla.gridy = 1;
		panel.add(textFieldTalla, gbc_textFieldTalla);
		
		textFieldTalla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == '\n'){
					buscar();
				}
			}
		});
		
		lblNewBuscar = new JLabel("");
		
		lblNewBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewBuscar.setIcon(new ImageIcon(Administrador.class.getResource("/imagenes/search64.png")));
		GridBagConstraints gbc_lblNewBuscar = new GridBagConstraints();
		gbc_lblNewBuscar.gridheight = 2;
		gbc_lblNewBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewBuscar.gridwidth = 6;
		gbc_lblNewBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewBuscar.gridx = 16;
		gbc_lblNewBuscar.gridy = 1;
		panel.add(lblNewBuscar, gbc_lblNewBuscar);

		lblNewBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buscar();
			}
		});
		
		JLabel etiquetaFecha = new JLabel(productos.fecha());
		etiquetaFecha.setEnabled(false);
		etiquetaFecha.setForeground(Color.WHITE);
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(etiquetaFecha, "cell 0 0");
		
		model=new DefaultTableModel(datos,cabecera){
			@Override
			public boolean isCellEditable(int row, int col){
				return col==7 ? false : true;
			}
		};
		
		tableAdministrador = new JTable(model);
		
		tableAdministrador.setBackground(new Color(176, 224, 226));
		tableAdministrador.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tableAdministrador.isCellEditable(0, 4);
		ScrollAdministrador=new JScrollPane (tableAdministrador);
		contentPane.add(ScrollAdministrador, "cell 0 5,grow");
		ScrollAdministrador.setPreferredSize(new Dimension(400, 250));
		
		tml = new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
				
				if(row == 0) return;
				if(col == 2 || col == 1 || col == 6) return;
				if(col == 0){
					String mod = tableAdministrador.getValueAt(row, 0).toString();
					if(mod.compareTo(tableAdministrador.getValueAt(row - 1, 0).toString())==0){
						model.setValueAt(tableAdministrador.getValueAt(row-1, 1), row, 1);
						model.setValueAt(tableAdministrador.getValueAt(row-1, 2), row, 2);
						model.setValueAt(tableAdministrador.getValueAt(row-1, 6), row, 6);
					}
				}
				
				
			}
		};
		
		model.addTableModelListener(tml);
		
		tml_precio = new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
				String modelo=tableAdministrador.getValueAt(row, 0).toString();
				String msj = "";
				boolean hecho = false;
				switch(col){
				case 5: // update existencias
					int existencias = Integer.parseInt(tableAdministrador.getValueAt(row, col).toString());
					hecho = productos.update_existencias(existencias, row);
					msj = hecho ? "Existencias actualizadas del modelo "+modelo : "A ocurrido un error";
					JOptionPane.showMessageDialog(contentPane, msj);
					break;
				case 6: // update precio
					double precio = Double.parseDouble(tableAdministrador.getValueAt(row, col).toString());
					hecho = productos.update_precio(precio, row);
					msj = hecho ? "Precio actualizado del modelo "+modelo : "A ocurrido un error";
					JOptionPane.showMessageDialog(contentPane, msj);
					break;
				}


			}
		};
		
		JComboBox comboBox = new JComboBox(productos.tallas());
		comboBox.setBackground(Color.darkGray);
		comboBox.setEditable(true);
		comboBox.setForeground(Color.white);
		DefaultCellEditor defaultCellEditor=new DefaultCellEditor(comboBox);
		tableAdministrador.getColumnModel().getColumn(3).setCellEditor(defaultCellEditor);
				
		JButton btnAltaProd = new JButton("");
		btnAltaProd.setBackground(new Color(0, 51, 153));
		btnAltaProd.setToolTipText("Agrega a la base de datos el(los) producto(s)");
		btnAltaProd.setIcon(new ImageIcon(Administrador.class.getResource("/imagenes/addProducto.png")));
		contentPane.add(btnAltaProd, "cell 0 10,alignx center");
		
		JButton btnSelectImage = new JButton("");
		btnSelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableAdministrador.getSelectedRow()!=-1){
					JFileChooser chooser = new JFileChooser();
					int returnval = chooser.showOpenDialog(contentPane);
					if(returnval == JFileChooser.APPROVE_OPTION){
						model.setValueAt(chooser.getSelectedFile(), tableAdministrador.getSelectedRow(), 7);
						System.out.println("Name: "+ chooser.getSelectedFile().getName());
						System.out.println("Path: "+ chooser.getSelectedFile());
					}
				}else{
					JOptionPane.showMessageDialog(contentPane, "Por favor selecciona el articulo al que le\nasignaras una imagen","Articulo no seleccionado",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSelectImage.setToolTipText("Agrega una imagen al(los) producto(s)");
		btnSelectImage.setIcon(new ImageIcon(Administrador.class.getResource("/imagenes/selectImage.png")));
		btnSelectImage.setBackground(new Color(0, 51, 153));
		contentPane.add(btnSelectImage, "cell 0 10,alignx center");
		
		JButton btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				JOptionPane.showMessageDialog(contentPane, "este boton debe limpiar la tabla");
				int filas = tableAdministrador.getRowCount();
				model.removeTableModelListener(tml_precio);
				for (int i = filas; i > 0; i--) {
					model.removeRow(i-1);
				}
				String[] d = {"","","","","","","",""};
				model.addRow(d);
				model.addTableModelListener(tml);
			}
		});
		btnLimpiar.setToolTipText("Limpia la tabla para agregar un(os) nuevo(s) producto(s)");
		btnLimpiar.setIcon(new ImageIcon(Administrador.class.getResource("/imagenes/limpia.png")));
		btnLimpiar.setBackground(new Color(0, 51, 153));
		contentPane.add(btnLimpiar, "cell 0 10,alignx center");
		
		
		btnAltaProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean hecho = false;
				int filas=tableAdministrador.getRowCount();
				String modelo="",nombreP="",descripcion="",talla="",color="", imagen="";
				int existencia=0;
				double precio=0.0;
				for (int i = 0; i < filas; i++) {
					if(tableAdministrador.getValueAt(i,0).toString().compareTo("")==0) break; // Corregir
					modelo=(String) tableAdministrador.getValueAt(i,0);
					nombreP=(String) tableAdministrador.getValueAt(i,1);
					descripcion=(String)tableAdministrador.getValueAt(i,2);
					talla=(String)tableAdministrador.getValueAt(i,3);
					color=tableAdministrador.getValueAt(i,4).toString().toUpperCase();
					existencia= Integer.parseInt((String) tableAdministrador.getValueAt(i,5));
					precio=Double.parseDouble((String)tableAdministrador.getValueAt(i,6));
					imagen = tableAdministrador.getValueAt(i, 7)==null ? "" : tableAdministrador.getValueAt(i, 7).toString();
					System.out.println(imagen);
					hecho = productos.altaProducto(modelo.toUpperCase(), nombreP.toUpperCase(), descripcion.toUpperCase(), talla.toUpperCase(), color.toUpperCase(), existencia, precio,rutaCorregida(imagen, "\\", "\\\\") );
					if(!hecho) return;
				}
				
				String msj = hecho ? "Los modelos han sido almacenados correctamente" : "A ocurrido un error al almacenar\n"+modelo+","+descripcion+","+talla+","+color+",existencias="+existencia;
				JOptionPane.showMessageDialog(contentPane, msj,"Informacion",JOptionPane.INFORMATION_MESSAGE);
				model.removeTableModelListener(tml);
				for (int i = filas; i > 0; i--) {
					model.removeRow(i-1);
				}
				String[] d = {"","","","","","","",""};
				model.addRow(d);
				model.addTableModelListener(tml);
			}
		});

		tableAdministrador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if ( key.getKeyCode() == KeyEvent.VK_ENTER) {
					model.removeTableModelListener(tml);
					model.addRow(new String[]{"","","","","",""});
					tableAdministrador.setModel(model);
					model.addTableModelListener(tml);
				}
				
				if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					if(tableAdministrador.getSelectedRow()!=0){
						model.removeTableModelListener(tml);
						model.removeRow(tableAdministrador.getSelectedRow());
						model.addTableModelListener(tml);
					}
				}
			}
		});

	}//fin constructor
	
	private void buscar(){
		if (!textFieldModelo.getText().isEmpty()){
			contentPane.remove(ScrollAdministrador);
			
			String modelo = textFieldModelo.getText();
			String talla = textFieldTalla.getText().isEmpty() ? "" : textFieldTalla.getText();
			String color = textFieldColor.getText().isEmpty() ? "" : textFieldColor.getText();

			datos = productos.buscar(modelo, talla, color);
			
			if(datos.length == 0){
				JOptionPane.showMessageDialog(contentPane, "Este modelo no existe", "Modelo error",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			model=new DefaultTableModel(datos,cabecera){
				 @Override
				 public boolean isCellEditable(int row, int col){
					 return col==7 ? false : true;
				 }
			 };

			 tableAdministrador = new JTable(model);
			 tableAdministrador.setBackground(new Color(176, 224, 226));
			 tableAdministrador.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			 tableAdministrador.isCellEditable(0, 4);
			 ScrollAdministrador=new JScrollPane (tableAdministrador);
			 contentPane.add(ScrollAdministrador, "cell 0 5,grow");
			 ScrollAdministrador.setPreferredSize(new Dimension(400, 250));
			 
			 model.addTableModelListener(tml_precio);
			 agregaMenu();
			 
			 contentPane.updateUI();
		}else{
			JOptionPane.showMessageDialog(contentPane, "Ingresa el modelo a buscar","Sin modelo",JOptionPane.ERROR_MESSAGE);
			
		}
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
	
	private void agregaMenu(){
		JPopupMenu menuTabla = new JPopupMenu();
		menuTabla.setBackground(SystemColor.inactiveCaptionBorder);
		menuTabla.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuTabla.setForeground(Color.BLACK);
		addPopup(tableAdministrador, menuTabla);
		
		mnEliminar = new JMenuItem("Eliminar producto");
		mnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int fila = tableAdministrador.getSelectedRow();
				if(fila==-1){
					JOptionPane.showMessageDialog(null, "Selecciona una fila");
				}else{
					String modelo = tableAdministrador.getValueAt(fila, 0).toString();
					String msj = "";
					boolean hecho = productos.delete_producto(tableAdministrador.getSelectedRow());
					msj = hecho ? "Este producto a sido eliminado exitosamente" : "A ocurrido un error";
					JOptionPane.showMessageDialog(contentPane, msj);
				}
				
			}
		});
		
		menuTabla.add(mnEliminar);
	}
	
	public String rutaCorregida(String ruta,String slash,String slashNueva){
		StringTokenizer tokens=new StringTokenizer(ruta, slash);
		
		String rutaCorregida = new String();
		
		int noTokens = tokens.countTokens();
		int i = 1;
		do
		{      //Agregar el nuevo separador
			rutaCorregida += tokens.nextToken()+slashNueva;
			i++;
		}while(i<noTokens);
		rutaCorregida += tokens.nextToken();       
		
		return rutaCorregida;
	}
	
	private String obten_mes(String mes_letra){
		String mes_numero="";
		if(mes_letra.compareTo("Enero")==0){
			mes_numero = "01";
		}else if(mes_letra.compareTo("Febrero")==0){
			mes_numero = "02";
		}else if(mes_letra.compareTo("Marzo")==0){
			mes_numero = "03";
		}else if(mes_letra.compareTo("Abril")==0){
			mes_numero = "04";
		}else if(mes_letra.compareTo("Mayo")==0){
			mes_numero = "05";
		}else if(mes_letra.compareTo("Junio")==0){
			mes_numero = "06";
		}else if(mes_letra.compareTo("Julio")==0){
			mes_numero = "07";
		}else if(mes_letra.compareTo("Agosto")==0){
			mes_numero = "08";
		}else if(mes_letra.compareTo("Septiembre")==0){
			mes_numero = "09";
		}else if(mes_letra.compareTo("Octubre")==0){
			mes_numero = "10";
		}else if(mes_letra.compareTo("Noviembre")==0){
			mes_numero = "11";
		}else if(mes_letra.compareTo("Diciembre")==0){
			mes_numero = "12";
		}
		return mes_numero;
	}
}









