package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import models.*;
import instancias.*;

public class Cambio {
	
	public String obten_venta(int id_venta){
		Ventas_model vmodel = new Ventas_model();
		Detalle_Venta_model dvmodel = new Detalle_Venta_model();
		Ropa_model rmodel = new Ropa_model();
		Modelo_model mmodel = new Modelo_model();
		
		Ventas ventas = vmodel.find_venta(id_venta);
		ArrayList<Detalle_Venta> detalles = dvmodel.find_detalle_venta(id_venta);
		String descripcion = "";
		
		descripcion = ventas.getId_venta()+" \t "+ventas.getFecha()+" \t\t "+ventas.getNo_articulos()+" \t\t\t "+ventas.getTotal_venta()+" \n";
		descripcion += "\nModelo \t Descripción \t\t\t\t No. artículos \t Precio unitario \t Estado \n";
		for (Detalle_Venta detalle_venta : detalles) {
			String desc = rmodel.find_ropa(detalle_venta.getId_ropa()).getDescricion();
			if(desc.length()>=20){
				descripcion += mmodel.find_modelo(detalle_venta.getId_modelo()).getModelo()+" \t "+desc+" \t "
						+ "" + detalle_venta.getCantidad_articulos()+" \t\t\t " + detalle_venta.getPrecio_unitario() + " \t\t " + detalle_venta.getEstado() +"\n";
			}else{
				descripcion += mmodel.find_modelo(detalle_venta.getId_modelo()).getModelo()+" \t "+desc+" \t\t\t "
						+ "" + detalle_venta.getCantidad_articulos()+" \t\t\t " + detalle_venta.getPrecio_unitario() + " \t\t " + detalle_venta.getEstado() +"\n";
			}
			
			System.out.println("Cadena: "+rmodel.find_ropa(detalle_venta.getId_ropa()).getDescricion().length());
		}
		return descripcion;
	}
	
	public String fecha(){
		Calendar calendario = new GregorianCalendar();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		
		return day+"/"+month+"/"+year;
	}
}

