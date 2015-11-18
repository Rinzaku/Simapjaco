package controllers;

import instancias.*;
import models.*;
import vistas.*;

import java.util.*;

import javax.swing.JFrame;

public class Ventas_C {
	
	String[] precios;
	int[][] identificadores;
	
	public String[][] busca_modelo(String modelo){
		Modelo_model mmodel = new Modelo_model();
		Talla_model tmodel = new Talla_model();
		Color_model cmodel = new Color_model();
		Ropa_model rmodel =  new Ropa_model();
		
		ArrayList<Modelo> lista = mmodel.find_modelo(modelo);
		String[][] productos = new String[lista.size()][];
		
		precios= new String[lista.size()];
		identificadores = new int[lista.size()][2];
		
		int i = 0;
		
		for (Modelo m : lista) {
			String[] p = new String[6];
			p[0] = modelo;
			p[1] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
			p[2] = tmodel.find_talla(m.getId_talla()).getTalla();
			p[3] = cmodel.find_color(m.getId_color()).getColor();
			p[4] = ""+m.getExistencias();
			p[5] = m.getEstado();
			productos[i]= p;
			precios[i]= ""+ rmodel.find_ropa(m.getId_ropa()).getPrecio();
			identificadores[i][0]=m.getId_modelo();
			identificadores[i][1]=m.getId_ropa();
			System.out.println(rmodel.find_ropa(m.getId_ropa()).getPrecio());
			i++;
		}
		
		return productos;
	}
	
	public int creaVenta(String fecha, int no_articulos, double total) {
		int id=0;
		Ventas_model vmodel = new Ventas_model();
		Ventas venta = new Ventas();
		
		venta.setFecha(fecha);
		venta.setNo_articulos(no_articulos);
		venta.setTotal_venta(total);
		venta.setEstado("FINALIZADA");
		venta.setAbono(0);
		
		id=vmodel.insert_venta(venta);
		
		return id;
	}

	public boolean creaDetalleVenta(int id_venta,int id_modelo,int id_ropa,int cantidad, double precio) {
		Detalle_Venta_model dvmodel = new Detalle_Venta_model();
		int id_detalle=0;
		Detalle_Venta detalle = new Detalle_Venta();
		detalle.setId_venta(id_venta);
		detalle.setId_modelo(id_modelo);
		detalle.setId_ropa(id_ropa);
		detalle.setCantidad_articulos(cantidad);
		detalle.setPrecio_unitario(precio);
		detalle.setEstado("VENDIDO");
		
		id_detalle=dvmodel.insert_detalle_venta(detalle);
		
		if (id_detalle<0) {
			return false;
		} else {
			return true;
		}
		
	}

	public String[] obten_precios(){
		return precios;
	}
	
	public int[][] obten_identificadores(){
		return identificadores;
	}
	public String fecha(){
		Calendar calendario = new GregorianCalendar();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		
		return day+"/"+month+"/"+year;
	}
	
	public int folio(){
		Ventas_model vmodel = new Ventas_model();
		int folio = vmodel.ultima_fila() + 1;
		return folio;
	}
	
	public double cambio(double recibido, double total){
		return recibido-total;
	}
}

