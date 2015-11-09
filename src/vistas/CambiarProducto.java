package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class CambiarProducto extends JFrame {
	private JTextField textModeloVenta;
	private JTextField textModeloCambio;

	/**
	 * Create the frame.
	 */
	public CambiarProducto() {
		getContentPane().setBackground(SystemColor.windowBorder);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\icon.jpg"));
		
		setTitle("Cambio de Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 424);
		getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 463, 177);
		getContentPane().add(textArea);
		
		JLabel lblModelo = new JLabel("Modelo :");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModelo.setBounds(10, 223, 68, 14);
		getContentPane().add(lblModelo);
		
		textModeloVenta = new JTextField();
		textModeloVenta.setBounds(101, 220, 86, 20);
		getContentPane().add(textModeloVenta);
		textModeloVenta.setColumns(10);
		
		textModeloCambio = new JTextField();
		textModeloCambio.setBounds(101, 266, 86, 20);
		getContentPane().add(textModeloCambio);
		textModeloCambio.setColumns(10);
		
		JLabel label = new JLabel("Modelo :");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(10, 269, 68, 14);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(653, 189, -161, -161);
		getContentPane().add(scrollPane);
	}
}

