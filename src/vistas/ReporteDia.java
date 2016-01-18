package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;

import controllers.PDF;
import controllers.Reportes;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReporteDia extends JFrame {

	private Reportes reporte;
	private JPanel contentPane;
	private DefaultTableModel model;
	private String [][] datos={{"","","","","","","",""},
	  						};
	private String [] cabecera;
	private JTable tableAdministrador;
	private JScrollPane ScrollAdministrador;
	private JPanel panel;
	private JLabel lblTitulo;
	private JButton btnPDF;
	private JButton btnCancelar;

	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReporteDia frame = new ReporteDia();
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
	public ReporteDia(String titulo, int tipo_reporte, String mes_anio) {
		
		reporte = new Reportes();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReporteDia.class.getResource("/imagenes/Shopping48.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1178, 497);
		setTitle("Ventana de reportes");
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[812px,grow]", "[23.00][50.00][16.00][grow][]"));
		setContentPane(contentPane);
		switch(tipo_reporte){
		case 1:
			String[] cadena1 = {"Modelo","Nombre Producto","Descripcion","Talla","Color","Existencias","Precio","Imagen","Estado"};
			cabecera =cadena1;
			datos = reporte.get_inventario();
			break;
		case 2:
			String[] cadena2 = {"No de Venta","Empleado","Total articulos","Sub_total","Descuento","Total","Abono","Estado","Fecha"};
			cabecera =cadena2;
			datos = reporte.get_ventas(2,"");
			break;
		case 3:
			String[] cadena3 = {"No de Venta","Empleado","Total articulos","Sub_total","Descuento","Total","Abono","Estado","Fecha"};
			cabecera =cadena3;
			datos = reporte.get_ventas(3,mes_anio);
			break;
		}
		
		model=new DefaultTableModel(datos,cabecera){
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		
		tableAdministrador = new JTable(model);
		
		tableAdministrador.setBackground(new Color(176, 224, 226));
		tableAdministrador.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tableAdministrador.isCellEditable(0, 4);
		
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		contentPane.add(panel, "cell 0 1,alignx center,aligny center");
		
		lblTitulo = new JLabel(titulo);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		panel.add(lblTitulo);
		ScrollAdministrador=new JScrollPane (tableAdministrador);
		contentPane.add(ScrollAdministrador, "cell 0 3,grow");
		ScrollAdministrador.setPreferredSize(new Dimension(400, 250));
		
		btnPDF = new JButton("");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int returnval = chooser.showSaveDialog(contentPane);
				if(returnval == JFileChooser.APPROVE_OPTION){
					
					reporte.crearPDF(chooser.getSelectedFile().getPath(),tipo_reporte, mes_anio);
				}
			}
		});
		btnPDF.setBackground(new Color(0, 51, 153));
		btnPDF.setToolTipText("Genera el Archivo PDF a almacenar");
		btnPDF.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/pdf_mini.png")));
		contentPane.add(btnPDF, "flowx,cell 0 4,alignx center,aligny center");
		
		btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(0, 51, 153));
		btnCancelar.setToolTipText("Regresa a la ventana administrador");
		btnCancelar.setIcon(new ImageIcon(Ventana_ventas.class.getResource("/imagenes/error32.png")));
		contentPane.add(btnCancelar, "cell 0 4");
	}

}
