package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controllers.Empleados_c;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;


public class Empleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDialog windowEmpleado;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldTelefono;
	private JTextArea txtDireccion;
	private Empleados_c controlEmpleado;
	private JDateChooser dateChooser;
	private JButton buttonBuscar;
	private JButton buttonAlta;

	/**
	 * Create the frame.
	 */
	public Empleados() {
		windowEmpleado=this;
		controlEmpleado=new Empleados_c();
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Empleados.class.getResource("/imagenes/SIMAP.png")));
		setTitle("EMPLEADOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 657, 418);
		contentPane = new JPanel();
		contentPane.setBackground(new Color (176, 224, 226));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(28, 33, 64, 19);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setForeground(new Color (0,51,153));
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(107, 32, 186, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(18, 85, 74, 19);
		lblApellidos.setForeground(new Color (0,51,153));
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblApellidos);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(107, 84, 186, 20);
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setBounds(12, 240, 80, 19);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDireccion.setForeground(new Color (0,51,153));
		contentPane.add(lblDireccion);
		
		txtDireccion = new JTextArea();
		txtDireccion.setBounds(107, 240, 186, 48);
		contentPane.add(txtDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(22, 137, 70, 19);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setForeground(new Color (0,51,153));
		contentPane.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(107, 136, 186, 20);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel labelImage = new JLabel("");
		labelImage.setBounds(349, 32, 280, 256);
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		labelImage.setIcon(new ImageIcon(Empleados.class.getResource("/imagenes/Empleado.png")));
		contentPane.add(labelImage);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(43, 188, 49, 19);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setForeground(new Color (0,51,153));
		contentPane.add(lblFecha);
		
		buttonBuscar = new JButton("");
		buttonBuscar.setBackground(new Color(0, 51, 153));
		buttonBuscar.setBounds(12, 311, 187, 57);
		buttonBuscar.setIcon(new ImageIcon(Empleados.class.getResource("/imagenes/search48.png")));
		contentPane.add(buttonBuscar);
		
	    buttonAlta = new JButton("");
	    buttonAlta.setBackground(new Color(0, 51, 153));
	    
		buttonAlta.setBounds(229, 311, 187, 57);
		buttonAlta.setIcon(new ImageIcon(Empleados.class.getResource("/imagenes/ok48.png")));
		contentPane.add(buttonAlta);
		
		JButton buttonBaja = new JButton("");
		buttonBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldApellido.getText().isEmpty() && textFieldNombre.getText().isEmpty() && textFieldTelefono.getText().isEmpty()){
					windowEmpleado.dispose();
					
				}else
					limpiaCampos();
			}
		});
		buttonBaja.setBackground(new Color(0, 51, 153));
		buttonBaja.setBounds(442, 311, 187, 57);
		buttonBaja.setIcon(new ImageIcon(Empleados.class.getResource("/imagenes/error48.png")));
		contentPane.add(buttonBaja);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(107, 187, 186, 20);
		contentPane.add(dateChooser);
		
		buttonAlta.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
                
	    		if(!textFieldNombre.getText().isEmpty() && !textFieldApellido.getText().isEmpty() && !textFieldTelefono.getText().isEmpty() && !txtDireccion.getText().isEmpty()){
		    		int dia=dateChooser.getCalendar().get(Calendar.DATE);
		    		String day;
		    		if (dia<10) {
		    			day="0"+dia;
						
					}
		    		else
		    			day=""+dia;
		    		int mes=(dateChooser.getCalendar().get(Calendar.MONTH))+1;
		    		String month;
		    		if (mes<10) {
		    			 month="0"+mes;
						
					}
		    		else
		    			month=""+mes;
		    		int anio=dateChooser.getCalendar().get(Calendar.YEAR);
		    		if (formatoTel(textFieldTelefono.getText())) {
		    			int clave=controlEmpleado.insertEmpleado(textFieldNombre.getText(),textFieldApellido.getText(),txtDireccion.getText(),textFieldTelefono.getText(),""+day+"/"+month+"/"+anio);  
		    			if (clave>-1) {
		    				JOptionPane.showMessageDialog(null, "empleado registrado con el identificador :"+clave);
		    				limpiaCampos();
		    			}else{
		    				JOptionPane.showMessageDialog(null, "Error al insertar empleado");
		    				limpiaCampos();
		    			}

		    		}else{
		    			JOptionPane.showMessageDialog(null, "El formato del telefono es incorrecto ");

		    		}  			

	    		}else{
	    			JOptionPane.showMessageDialog(null, "Llena todos los campos");
	    		}
	    	}
	    });
		
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ejecutaBusqueda();
			}
		});
	
	}
	
	
	public void limpiaCampos(){
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldTelefono.setText("");
		txtDireccion.setText("");
		dateChooser.setDateFormatString("");
			
	}
	
	public boolean formatoTel(String telefono){
		char []numero= telefono.toCharArray();
		boolean bandera=false;		
		if (numero.length ==8 || numero.length==10) {
			for (int i = 0; i < numero.length; i++) {
				if (numero[i]<58) {
					bandera=true;
				}
				else
					bandera= false;
			}
		}
		
		return bandera;
	}
	
	public void ejecutaBusqueda(){
		if (txtDireccion.getText().isEmpty() && textFieldApellido.getText().isEmpty() && textFieldNombre.getText().isEmpty() && textFieldTelefono.getText().isEmpty()) {
			TableEmpleados tabla=new TableEmpleados(controlEmpleado.Empleados());
			tabla.setVisible(true);
			tabla.setLocationRelativeTo(null);
		}
		
		if (!textFieldNombre.getText().isEmpty() && !textFieldApellido.getText().isEmpty()){
			String [][] empleado=controlEmpleado.empleado(textFieldNombre.getText(),textFieldApellido.getText());
			TableEmpleados tabla=new TableEmpleados(empleado);
			tabla.setVisible(true);
			tabla.setLocationRelativeTo(null);
		}
	}
}//fin clase







