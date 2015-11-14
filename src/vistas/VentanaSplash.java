package vistas;

import java.awt.AWTEvent;
import java.awt.Color;

import javax.print.attribute.AttributeSetUtilities;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeListener;

public class VentanaSplash extends JFrame{
	JProgressBar progressBar;
	Progress hilo;
	JFrame vn;
	public VentanaSplash(){
		vn=this;
		JPanel p=new JPanel();
		p.setBorder(new LineBorder(new Color(0, 0, 205), 1, true));
		p.setLayout(null);
		
				progressBar = new JProgressBar();
				progressBar.setBackground(Color.WHITE);
				progressBar.setForeground(new Color(95, 158, 160));
				progressBar.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						if(progressBar.getValue()==100){
							Ventana_ventas ventas=new Ventana_ventas();
							ventas.setVisible(true);
							ventas.setLocationRelativeTo(null);
							vn.dispose();
						}
						
					}
				});
				progressBar.setStringPainted(true);
				progressBar.setBounds(33, 377, 553, 14);
				p.add(progressBar);
		JLabel labelFont = new JLabel("");
		labelFont.setForeground(new Color(0, 0, 0));
		labelFont.setBackground(Color.WHITE);
		labelFont.setBounds(0, 0, 617, 413);
		labelFont.setIcon(new ImageIcon(VentanaSplash.class.getResource("/imagenes/SIMAPF.png")));
		p.add(labelFont);
		getContentPane().add(p);
		iniciar();
	}
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	public void iniciar(){
		setUndecorated(true);
		hilo=new Progress(getProgressBar());
		hilo.start();
	}

}
