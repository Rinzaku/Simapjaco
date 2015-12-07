package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	
	private JTable tablaModelo;
	private DefaultTableModel model;
	
	private String[][] datos;
	private String[] cabecera = {"Modelo","Descripcion","Talla","Color","Cantidad","Precio"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
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
	public Vista() {
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
//					JOptionPane.showConfirmDialog(null, "Hecho");
					if (!textField.getText().isEmpty()) {
//						datos = 
						scrollPane = new JScrollPane();
						contentPane.add(scrollPane, "cell 0 3,grow");
					}else{
						JOptionPane.showConfirmDialog(null, "POr favor ingresa un modelos");
					}
					
				}
			}
		});
		contentPane.add(textField, "cell 0 1");
		textField.setColumns(10);
		
	}

}
