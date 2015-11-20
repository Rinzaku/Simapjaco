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
		Ventas ventas = vmodel.find_venta(id_venta);
		ArrayList<Detalle_Venta> detalles = dvmodel.find_detalle_venta(id_venta);
		String descripcion = "";
		
		descripcion = ventas.getId_venta()+" \t\t\t "+ventas.getFecha()+" \t\t "+ventas.getNo_articulos()+" \t\t\t "+ventas.getTotal_venta()+" \n";
		
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

