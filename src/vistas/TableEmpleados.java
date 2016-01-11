package vistas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controllers.Empleados_c;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TableEmpleados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablEmpleados;
	private JScrollPane scrollEmpleados;
	private String [][] datosEmpleados;//={{"xcvfd","sdvdfgfd","xcbdf","xcbxh","vbbgfngfn","fdgdfhdf"}};
	private String [] cabeceraEmpleados={"N° Empleado","Nombre","Apellidos","Fecha de inicio","Teléfono","Dirección"};
	private JLabel lblFecha;
	private JLabel etiquetaFecha;
	Empleados_c controlEmp;
	private JLabel label;
	private JLabel lblNewLabel;
	private TableModelListener tml;
	private DefaultTableModel modelEmpleado;
	private JPopupMenu popupMenu;
	private JMenuItem mntmEliminar;


	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TableEmpleados frame = new TableEmpleados();
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
	public TableEmpleados(String [][] datos) {
		setResizable(false);
		setAutoRequestFocus(false);
		setForeground(Color.WHITE);
		controlEmp =new Empleados_c();
		datosEmpleados=datos;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 294);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		modelEmpleado = new DefaultTableModel(datosEmpleados,cabeceraEmpleados){

			@Override
			public boolean isCellEditable(int row, int col){
				if (col<4) {
					return false;

				}
				else

					return true;
			}
		};

		contentPane.setLayout(new MigLayout("", "[1px][][][][][][][][grow]", "[1px][][][][][]"));
		tablEmpleados = new JTable(modelEmpleado);	



		tablEmpleados.setBackground(new Color(176, 224, 230));
		tablEmpleados.isCellEditable(0,	0);
		tablEmpleados.setSelectionBackground(Color.cyan);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.LIGHT_GRAY);
		lblFecha.setBackground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFecha, "flowx,cell 8 1,alignx right");

		label = new JLabel("");
		label.setIcon(new ImageIcon(TableEmpleados.class.getResource("/imagenes/Users48.png")));
		contentPane.add(label, "cell 8 2,alignx center,aligny center");

		lblNewLabel = new JLabel("Empleados");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel, "cell 8 3,alignx center,aligny center");

		scrollEmpleados = new JScrollPane(tablEmpleados);
		
		scrollEmpleados.setPreferredSize(new Dimension(1000, 150));

		contentPane.add(scrollEmpleados, "cell 0 5 9 1,grow");

		etiquetaFecha = new JLabel(controlEmp.fecha());
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		etiquetaFecha.setForeground(Color.GRAY);
		contentPane.add(etiquetaFecha, "cell 8 1");
		scrollEmpleados.setVisible(true);
		
		//Aqui el TableModelListener debe ir fuera de cualquier otro metodo, ya que es un objeto aparte
		tml=new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				int col=e.getColumn();
				int file=e.getFirstRow();
				if (e.UPDATE==e.getType() ) {
					if (col==4){
						String idEmpleado=modelEmpleado.getValueAt(file,0).toString();
						String telefono=modelEmpleado.getValueAt(file,4).toString();
						controlEmp.updateEmpleado(idEmpleado,telefono,null);
						

					}
					if (col==5){
						String idEmpleado=modelEmpleado.getValueAt(file,0).toString();
						String direccion=modelEmpleado.getValueAt(file, 5).toString();
						controlEmp.updateEmpleado(idEmpleado, null, direccion);
					}
				}
			}
		};

		/*
		 * NOTA: Este escucha debe ser removido en caso de que se deba agregar una nueva fila a la tabla, y posteriormente gregado,
		 * de lo contrario te generara un error  
		 */
		modelEmpleado.addTableModelListener(tml);//Aqui ya se agrega el escucha para el modelo de la tabla


	}//constructor

}//fin clase




