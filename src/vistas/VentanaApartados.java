package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import controllers.Apartar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaApartados extends JFrame {

	private JPanel contentPane;
	private JTextField textFolio;
	private JTextField textResta;
	private JTextField textAbono;
	private JTextField textTotal;
	private JButton buttonListo;
	private JButton buttonCancelar;
	private JLabel labelImage;
	private Apartar apat;
	private JFrame ventanAparatdos;

	/**
	 * Create the frame.
	 */
	public VentanaApartados() {
		ventanAparatdos=this;
		apat=new Apartar();
		setTitle("Apartados");
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 309);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setBounds(315, 12, 53, 19);
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBackground(Color.WHITE);
		contentPane.add(lblFecha);
		
		JLabel etiquetaFecha = new JLabel(apat.fecha());
		etiquetaFecha.setBounds(376, 12, 98, 19);
		etiquetaFecha.setForeground(Color.WHITE);
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(etiquetaFecha);
		
		JLabel lblModelo = new JLabel("Folio :");
		lblModelo.setBounds(23, 48, 46, 19);
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblModelo);
		
		textFolio = new JTextField();
		textFolio.setBounds(83, 47, 142, 20);
		contentPane.add(textFolio);
		textFolio.setColumns(10);
		
		labelImage = new JLabel("");
		labelImage.setBounds(235, 35, 62, 48);
		
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		labelImage.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/search48.png")));
		contentPane.add(labelImage);
		
		JLabel lblResta = new JLabel("Resta :");
		lblResta.setBounds(17, 108, 52, 19);
		lblResta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResta.setForeground(Color.WHITE);
		contentPane.add(lblResta);
		
		textResta = new JTextField();
		textResta.setEditable(false);
		textResta.setBounds(83, 108, 142, 20);
		contentPane.add(textResta);
		textResta.setColumns(10);
		
		JLabel lblAbono = new JLabel("Abono :");
		lblAbono.setBounds(14, 146, 55, 19);
		lblAbono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAbono.setForeground(Color.WHITE);
		contentPane.add(lblAbono);
		
		textAbono = new JTextField();
		textAbono.setBounds(83, 146, 142, 20);
		contentPane.add(textAbono);
		textAbono.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(22, 184, 47, 19);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setForeground(Color.WHITE);
		contentPane.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(83, 184, 142, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);
		
		JLabel labelImagen = new JLabel("");
		labelImagen.setBounds(315, 83, 172, 128);
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagen.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/money215.png")));
		contentPane.add(labelImagen);
		
		buttonCancelar = new JButton("");
		buttonCancelar.setBounds(12, 228, 213, 41);
		buttonCancelar.setBackground(new Color(51, 0, 204));
		buttonCancelar.setForeground(new Color(51, 0, 204));
		buttonCancelar.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/error32.png")));
		contentPane.add(buttonCancelar);
		
		buttonListo = new JButton("");
		buttonListo.setBounds(229, 228, 258, 41);
		buttonListo.setBackground(new Color(51, 0, 204));
		buttonListo.setForeground(new Color(51, 0, 204));
		buttonListo.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/ok32.png")));
		
		contentPane.add(buttonListo);
		
		textFolio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(!textFolio.getText().isEmpty()){
						String resta =apat.buscaModelo(Integer.parseInt(textFolio.getText()));
						textResta.setText(resta);
					}
				}
				
			}
		});
		
		textAbono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(!textAbono.getText().isEmpty() && !textResta.getText().isEmpty()){
						double total =Double.parseDouble(textResta.getText())-Double.parseDouble(textAbono.getText());
						textTotal.setText(""+total);
						
					}
					
				}
			}
		});
		
		labelImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount()==1) {
					if(!textFolio.getText().isEmpty()){
						String resta =apat.buscaModelo(Integer.parseInt(textFolio.getText()));
						textResta.setText(resta);
					}
					else{
						JOptionPane.showMessageDialog(null, "Ingrese un folio");
					}
					
				}
			}
		});
		
		buttonListo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean bnd;
				if (!textFolio.getText().isEmpty()){
					bnd=apat.abono(Integer.parseInt(textFolio.getText()),Double.parseDouble(textTotal.getText()));

					if (bnd) {
						JOptionPane.showMessageDialog(null, "Abono actualizado");
						ventanAparatdos.dispose();

					}
					else{
						JOptionPane.showMessageDialog(null, "Error en la actualizacion");
						ventanAparatdos.dispose();

					}
				}else{
					JOptionPane.showMessageDialog(null, "Ingrese el folio");
				}

			}
		});
		
		buttonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textFolio.getText().isEmpty() && textResta.getText().isEmpty() && textAbono.getText().isEmpty() && textTotal.getText().isEmpty()){
					ventanAparatdos.dispose();
				}
				else{
					textAbono.setText("");
					textFolio.setText("");
					textResta.setText("");
					textTotal.setText("");
				}
				
			}
		});
		
	}
	
}









