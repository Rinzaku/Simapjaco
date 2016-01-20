package vistas;

import javax.swing.JProgressBar;

public class Progress extends Thread{
	JProgressBar progreso;
	
	public  Progress (JProgressBar prog){
		progreso=prog;
	}
	
		public void run(){
		for (int i = 0;i < 101; i++) {
			progreso.setValue(i);
			pausa(10);
		}
	}
	
	
	public void pausa(int seg){
		try{
			Thread.sleep(seg);
			
		}catch(Exception e){
			
		}
	}
	
	

}
