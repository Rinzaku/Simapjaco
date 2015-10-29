package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
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

import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

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
	
	private String [][] datosVentas={{"holq","ceci","jhdgdhdj","ieyrhb","xcjvbj","hxcbv"},
			{"","","","","",""},
			{"","","","","",""},
			{"","","","","",""},};
	private String [] cabeceraVentas={"Modelo","Descripcion","Talla","Color","Cantidad","Precio"};
	

	private String [][] datosBusqueda={{"holq","ceci","jhdgdhdj","ieyrhb","xcjvbj","hxcbv"},
			{"","","","","",""},
			{"","","","","",""},
			{"","","","","",""},};
	private String [] cabeceraBusqueda={"Modelo","Descripcion","Talla","Color","Cantidad","Estado"};
	private JTextField txtTotal;

	
	  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_ventas frame = new Ventana_ventas();
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
	public Ventana_ventas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1265, 642);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[812px,grow]", "[71px][191px][][48.00][grow][36.00][grow][][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, "cell 0 0,alignx center,aligny bottom");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 3.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblVentasSimapjaco = new JLabel("Ventas  Simapjaco.");
		lblVentasSimapjaco.setForeground(Color.WHITE);
		lblVentasSimapjaco.setFont(new Font("Times New Roman", Font.ITALIC, 22));
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
		
		JLabel lblNewBuscar = new JLabel("");
		lblNewBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount()==1) {
					scrollBusqueda.setVisible(true);
					
				}
			}
		});
		lblNewBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewBuscar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/search48.png")));
		GridBagConstraints gbc_lblNewBuscar = new GridBagConstraints();
		gbc_lblNewBuscar.gridheight = 2;
		gbc_lblNewBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewBuscar.gridwidth = 6;
		gbc_lblNewBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewBuscar.gridx = 16;
		gbc_lblNewBuscar.gridy = 1;
		panel.add(lblNewBuscar, gbc_lblNewBuscar);
		
		modelVentas = new DefaultTableModel(datosVentas,cabeceraVentas);
		modelBusqueda=new DefaultTableModel(datosBusqueda,cabeceraBusqueda);
		
		tableVentas = new JTable(modelVentas);
		tableBusqueda=new JTable(modelBusqueda);
		

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
		
		tableVentas.setBackground(new Color(255, 192, 203));
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
				System.out.println("quitar"+" "+e.getClickCount());
				modelVentas.removeRow(tableVentas.getSelectedRow());
			}
		});
		
		menuTabla.add(mntmQuitar);
		
		mntmApartar= new JMenuItem("Apartar");
		mntmApartar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Apartar:"+" "+e.getClickCount());
				ApartarProducto apart=new ApartarProducto();
				apart.setVisible(true);
			}
		});
		menuTabla.add(mntmApartar);
		contentPane.add(scrollVentas, "cell 0 1,grow");
		scrollVentas.setPreferredSize(new Dimension(400, 150));
		
		JLabel lblTotal = new JLabel("Total:  $");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTotal, "flowx,cell 0 2,alignx right");
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		contentPane.add(txtTotal, "cell 0 2,alignx right");
		txtTotal.setColumns(10);
		
		
		tableBusqueda.setBackground(new Color(176, 224, 230));
		
		scrollBusqueda= new JScrollPane(tableBusqueda);
		scrollBusqueda.setPreferredSize(new Dimension(400, 150));
		contentPane.add(scrollBusqueda, "cell 0 4 1 2,grow");
		scrollBusqueda.setVisible(false);
		JPanel panelBoton = new JPanel();
		panelBoton.setBackground(Color.BLACK);
		contentPane.add(panelBoton, "cell 0 7 1 2,growx,aligny bottom");
		panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBotonAdmin = new JButton("");
		btnBotonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login=new Login();
				login.setVisible(true);
			}
		});
			
		btnBotonAdmin.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/Users48.png")));
		panelBoton.add(btnBotonAdmin);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/error48.png")));
		panelBoton.add(btnCancelar);
		
		JButton btnFinalizar = new JButton("");
		btnFinalizar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/ok48.png")));
		panelBoton.add(btnFinalizar);
		
		JButton btnApartado = new JButton("");
		btnApartado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApartarProducto apartado =new  ApartarProducto();
				apartado.setVisible(true);
			}
		});
		btnApartado.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/cambio64.png")));
		panelBoton.add(btnApartado);
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
	
	
}
