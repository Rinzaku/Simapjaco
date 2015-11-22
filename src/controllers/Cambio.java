package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import models.*;
import instancias.*;

public class Cambio {
	
	private Ventas_model vmodel; 
	private Detalle_Venta_model dvmodel;
	private Ropa_model rmodel;
	private Modelo_model mmodel;
	private Ventas ventas;
	private ArrayList<Detalle_Venta> detalles;
	
	public String obten_venta(int id_venta){
		vmodel = new Ventas_model();
		dvmodel = new Detalle_Venta_model();
		rmodel = new Ropa_model();
		mmodel = new Modelo_model();
		
		ventas = vmodel.find_venta(id_venta);
		detalles = dvmodel.find_detalle_venta(id_venta);
		String descripcion = "";
		
		descripcion = ventas.getId_venta()+" \t "+ventas.getFecha()+" \t "+ventas.getNo_articulos()+" \t\t "+ventas.getTotal_venta()+" \n";
//		descripcion += "\nModelo \t Descripción \t\t\t\t No. artículos \t Precio unitario \t Estado \n";
//		for (Detalle_Venta detalle_venta : detalles) {
//			String desc = rmodel.find_ropa(detalle_venta.getId_ropa()).getDescricion();
//			if (desc.length()<=15) {
//				descripcion += mmodel.find_modelo(detalle_venta.getId_modelo()).getModelo()+" \t "+desc+" \t\t\t "
//						+ "" + detalle_venta.getCantidad_articulos()+" \t\t\t " + detalle_venta.getPrecio_unitario() + " \t\t " + detalle_venta.getEstado() +"\n";
//			}else if(desc.length()<=25){
//				descripcion += mmodel.find_modelo(detalle_venta.getId_modelo()).getModelo()+" \t "+desc+" \t\t "
//						+ "" + detalle_venta.getCantidad_articulos()+" \t\t\t " + detalle_venta.getPrecio_unitario() + " \t\t " + detalle_venta.getEstado() +"\n";
//			}else{
//				descripcion += mmodel.find_modelo(detalle_venta.getId_modelo()).getModelo()+" \t "+desc+" \t "
//						+ "" + detalle_venta.getCantidad_articulos()+" \t\t\t " + detalle_venta.getPrecio_unitario() + " \t\t " + detalle_venta.getEstado() +"\n";
//			}
//			
//			System.out.println("Cadena: "+rmodel.find_ropa(detalle_venta.getId_ropa()).getDescricion().length());
//		}
		return descripcion;
	}
	
	public String[][] obten_detalles_venta(int id_venta){
		String[][] datos = new String[detalles.size()][];
		int i=0;
		for (Detalle_Venta detalle : detalles) {
			String[] dato = new String[5];
			dato[0] = mmodel.find_modelo(detalle.getId_modelo()).getModelo();
			dato[1] = rmodel.find_ropa(detalle.getId_ropa()).getDescricion();
			dato[2] = ""+detalle.getCantidad_articulos();
			dato[3] = ""+detalle.getPrecio_unitario();
			dato[4] = detalle.getEstado();
			datos[i] = dato;
			i++;
			
		}
		return datos;
	}
//	public String obten_precio(){
//		
//	}
	public String fecha(){
		Calendar calendario = new GregorianCalendar();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		
		return day+"/"+month+"/"+year;
	}
}

