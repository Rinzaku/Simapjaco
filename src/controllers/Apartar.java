package controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import models.Ventas_model;
import instancias.Detalle_Venta;
import instancias.Ventas;

public class Apartar {
	Detalle_Venta detalleVtn;
	
	private int creaVenta(String fecha,double Abono) {
		int id=0;
		Ventas_model VentasModel = new Ventas_model();
		Ventas venta = new Ventas();
		
		venta.setFecha(fecha);
		venta.setNo_articulos(1);
		venta.setAbono(Abono);
		venta.setEstado("Apartado");
		
		id=VentasModel.insert_venta(venta);
		
		return id;
	}

	public boolean apartarProducto(String modelo, String Descripcion, String Talla, String Color,String Precio,String Fecha,Double Abono){
		int id_venta=0;
		id_venta =creaVenta(Fecha,Abono);
		detalleVtn.getId_detalle_venta();
		System.out.println(detalleVtn.getId_detalle_venta());
		
		return true;
	}
	
	public String fecha(){
		Calendar calendario = new GregorianCalendar();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		
		return day+"/"+month+"/"+year;
	}
	
	public String diferencia(double precio,double Acuenta){
	
		double resta1=0.0;
		resta1=precio-Acuenta;
		
		return ""+resta1;
	}
	

}









