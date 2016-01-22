package vistas;

import java.awt.EventQueue;

public class Run {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSplash window =new VentanaSplash();
					window.setBounds(0, 0, 600, 400);
					window.setLocationRelativeTo(null);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
