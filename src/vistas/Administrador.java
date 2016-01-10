package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;












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

import controllers.Productos_admin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 480);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmInventario = new JMenuItem("Inventario");
		mntmInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteDia reporteDia=new ReporteDia();
				reporteDia.setVisible(true);
			}
		});
		
		mnMenu.add(mntmInventario);
		
		JMenuItem mntmReporteDelDia = new JMenuItem("Reporte del dia");
		mnMenu.add(mntmReporteDelDia);
		
		JMenuItem mntmReporteDelMes = new JMenuItem("Reporte del mes");
		mnMenu.add(mntmReporteDelMes);
		JMenuItem mntmEmpleados = new JMenuItem("Empleados");
		mntmEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empleados Empleados=new Empleados();
				Empleados.setVisible(true);
			}
		});
		mnMenu.add(mntmEmpleados);
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
				switch(col){
				case 3: // update talla 
					break;
				case 4: // update color  
					break;
				case 5: // update existencias
					String modelo = tableAdministrador.getValueAt(row, 0).toString();
					int existencias = Integer.parseInt(tableAdministrador.getValueAt(row, col).toString());
					String msj = "";
					boolean hecho = productos.update_existencias(modelo, existencias, row);
					msj = hecho ? "Existencias actualizadas del modelo "+modelo : "A ocurrido un error";
					JOptionPane.showMessageDialog(contentPane, msj);
					break;
				case 6: // update precio
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
		
		JButton btnUpdate = new JButton("");
		btnUpdate.setBackground(new Color(0,51, 153));
		btnUpdate.setIcon(new ImageIcon(Administrador.class.getResource("/imagenes/update.png")));
		contentPane.add(btnUpdate, "flowx,cell 0 10,alignx center");
		
				
		JButton btnAltaProd = new JButton("");
		btnAltaProd.setBackground(new Color(0, 51, 153));
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
		btnSelectImage.setIcon(new ImageIcon(Administrador.class.getResource("/imagenes/selectImage.png")));
		btnSelectImage.setBackground(new Color(0, 51, 153));
		contentPane.add(btnSelectImage, "cell 0 10,alignx center");
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(contentPane, "este boton debe limpiar la tabla");
			}
		});
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
					hecho = productos.altaProducto(modelo, nombreP, descripcion, talla, color, existencia, precio, imagen);
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
			 
			 contentPane.updateUI();
		}else{
			JOptionPane.showMessageDialog(contentPane, "Ingresa el modelo a buscar","Sin modelo",JOptionPane.ERROR_MESSAGE);
			
		}
	}
}









