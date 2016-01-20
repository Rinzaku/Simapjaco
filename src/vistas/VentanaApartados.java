package vistas;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaApartados extends JFrame {

	private static final long serialVersionUID = 1L;
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
	private boolean bnd =false;
	/**
	 * Create the frame.
	 */
	public VentanaApartados() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaApartados.class.getResource("/imagenes/Shopping48.png")));
		ventanAparatdos=this;
		apat=new Apartar();
		setTitle("Apartados");
		setResizable(false);
		setAutoRequestFocus(false);
		setBounds(100, 100, 505, 309);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[57px][14px][142px][68px][18px][53px][8px][111px]", "[19px][48px][19.00px][18px][20px][18px][27px][41px]"));
		
		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBackground(Color.WHITE);
		contentPane.add(lblFecha, "cell 5 0,alignx left,aligny top");
		
		JLabel etiquetaFecha = new JLabel(apat.fecha());
		etiquetaFecha.setForeground(Color.WHITE);
		etiquetaFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(etiquetaFecha, "cell 7 0,alignx left,aligny top");
		
		JLabel lblModelo = new JLabel("Folio :");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblModelo, "cell 0 1,alignx right,aligny center");
		
		textFolio = new JTextField();
		contentPane.add(textFolio, "cell 2 1,growx,aligny center");
		textFolio.setColumns(10);
		
		labelImage = new JLabel("");
		
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		labelImage.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/search48.png")));
		contentPane.add(labelImage, "cell 3 1,growx,aligny top");
		
		JLabel lblResta = new JLabel("Resta :");
		lblResta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResta.setForeground(Color.WHITE);
		contentPane.add(lblResta, "cell 0 3,alignx right,aligny bottom");
		
		textResta = new JTextField();
		textResta.setEditable(false);
		contentPane.add(textResta, "cell 2 3,growx,aligny bottom");
		textResta.setColumns(10);
		
		JLabel lblAbono = new JLabel("Abono :");
		lblAbono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAbono.setForeground(Color.WHITE);
		contentPane.add(lblAbono, "cell 0 4,alignx right,aligny top");
		
		textAbono = new JTextField();
		contentPane.add(textAbono, "cell 2 4,growx,aligny top");
		textAbono.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setForeground(Color.WHITE);
		contentPane.add(lblTotal, "cell 0 5,alignx right,aligny top");
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		contentPane.add(textTotal, "cell 2 5,growx,aligny top");
		textTotal.setColumns(10);
		
		JLabel labelImagen = new JLabel("");
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagen.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/money215.png")));
		contentPane.add(labelImagen, "cell 5 1 3 6,growx,aligny top");
		
		buttonCancelar = new JButton("");
		buttonCancelar.setBackground(new Color(51, 0, 204));
		buttonCancelar.setForeground(new Color(51, 0, 204));
		buttonCancelar.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/error32.png")));
		contentPane.add(buttonCancelar, "cell 0 7 3 1,growx,aligny top");
		
		buttonListo = new JButton("");
		buttonListo.setBackground(new Color(51, 0, 204));
		buttonListo.setForeground(new Color(51, 0, 204));
		buttonListo.setIcon(new ImageIcon(VentanaApartados.class.getResource("/imagenes/ok32.png")));
		
		contentPane.add(buttonListo, "cell 3 7 5 1,growx,aligny top");
		
		textFolio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(!textFolio.getText().isEmpty()){
						String resta =apat.buscaModelo(Integer.parseInt(textFolio.getText()));
						if (resta!=null) {
							textResta.setText(resta);
						}else{
							JOptionPane.showMessageDialog(null, "Lo sentimos la fecha a expirado");
							if (!bnd) {
								apat.regresarProducto(Integer.parseInt(textFolio.getText()));
								textAbono.setEditable(false);
								bnd=true;							
							}

						}

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









