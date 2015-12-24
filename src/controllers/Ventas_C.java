package controllers;

import instancias.*;
import models.*;
import vistas.*;

import java.util.*;

import javax.swing.JFrame;

public class Ventas_C {
	
	Modelo_model mmodel;
	Talla_model tmodel;
	Color_model cmodel;
	Ropa_model rmodel;
	
	String[] precios;
	int[][] identificadores;
	ArrayList<Modelo> lista;
	String[][] productos;
	
	public String[][] busca_modelo(String modelo){
		mmodel = new Modelo_model();
		tmodel = new Talla_model();
		cmodel = new Color_model();
		rmodel =  new Ropa_model();
		
		lista = mmodel.find_modelo(modelo);
		productos = new String[lista.size()][];
		
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
//			System.out.println(rmodel.find_ropa(m.getId_ropa()).getPrecio());
			i++;
		}
		
		return productos;
	}
	
	public String[][] busca_modelo(String modelo, String talla, String color){
		mmodel = new Modelo_model();
		tmodel = new Talla_model();
		cmodel = new Color_model();
		rmodel =  new Ropa_model();
		
		productos = null;
		if (talla.compareTo("")==0) {
			productos = busca_modelo_color(modelo, color);
		}else if (color.compareTo("")==0) {
			productos = busca_modelo_talla(modelo,talla);
		}else {
			productos = busca_modelo_talla_color(modelo,talla,color);
		}
		return productos;
	}

	public int creaVenta(String fecha, int no_articulos, double subtotal, int numero_empleado, double desc, double total) {
		Ventas_model vmodel = new Ventas_model();
		Ventas venta = new Ventas();
		
		venta.setFecha(fecha);
		venta.setNo_articulos(no_articulos);
		venta.setSub_total(subtotal);
		venta.setDescuento(desc);
		venta.setTotal_venta(total);
		venta.setEstado("FINALIZADA");
		venta.setAbono(0);
		venta.setNo_empleado(numero_empleado);
		
		return vmodel.insert_venta(venta);
	}

	public boolean creaDetalleVenta(int id_venta,int id_modelo,int id_ropa,int cantidad, double precio) {
		Detalle_Venta_model dvmodel = new Detalle_Venta_model();
		Modelo_model mmodel = new Modelo_model();
		Ropa_model rmodel = new Ropa_model();
		
		Modelo modelo = mmodel.find_modelo(id_modelo);
		Ropa ropa = rmodel.find_ropa(id_ropa);
		
		int existencias_ropa = ropa.getExistencias();
		int existencias_modelo = modelo.getExistencias();
		
		int id_detalle=0;
		Detalle_Venta detalle = new Detalle_Venta();
		detalle.setId_venta(id_venta);
		detalle.setId_modelo(id_modelo);
		detalle.setId_ropa(id_ropa);
		detalle.setCantidad_articulos(cantidad);
		detalle.setPrecio_unitario(precio/cantidad);
		detalle.setEstado("VENDIDO");
		
		id_detalle=dvmodel.insert_detalle_venta(detalle);
		mmodel.update_modelo(id_modelo, existencias_modelo-cantidad);
		rmodel.update_ropa(id_ropa, existencias_ropa-cantidad);
		
		if (id_detalle<0) {
			return false;
		} else {
			return true;
		}
		
	}

	public boolean hayExistencias(int id_modelo,int cantidad){
		
		int existencias_total = new Modelo_model().find_modelo(id_modelo).getExistencias() - cantidad;
		
		return existencias_total < 0? false : true;
	}
	
	public String dameModelo(int id_modelo){
		Modelo m = new Modelo_model().find_modelo(id_modelo);
		String msj = m.getModelo()+", "+(new Ropa_model().find_ropa(m.getId_ropa()).getDescricion())+", "+(new Color_model().find_color(m.getId_color()).getColor())+
				", "+(new Talla_model().find_talla(m.getId_talla()).getTalla())+", existencias: "+m.getExistencias();
		return msj;
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
	
	public boolean exite_empleado(int id_empleado){
		ArrayList<Empleado> empleados = new Empleados_model().get_empleados();
		for (Empleado e : empleados) {
			if(e.getId_empleado()==id_empleado) return true;
		}
		return false;
	}
	
	private String[][] busca_modelo_color(String modelo, String color) {
		lista = mmodel.find_modelo(modelo);
		
		precios= new String[lista.size()];
		identificadores = new int[lista.size()][2];
		
		int i = 0;
		for (Modelo m : lista) {
			String color_model = cmodel.find_color(m.getId_color()).getColor();
			if (color.compareTo(color_model)==0) {
				i++;
			}
		}
		
		String[][] productos = new String[i][];
		i=0;
		for (Modelo m : lista) {
			String color_model = cmodel.find_color(m.getId_color()).getColor();
			if (color.compareTo(color_model)==0) {
				String[] p = new String[6];
				p[0] = modelo;
				p[1] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
				p[2] = tmodel.find_talla(m.getId_talla()).getTalla();
				p[3] = color_model;
				p[4] = ""+m.getExistencias();
				p[5] = m.getEstado();
				productos[i]= p;
				precios[i]= ""+ rmodel.find_ropa(m.getId_ropa()).getPrecio();
				identificadores[i][0]=m.getId_modelo();
				identificadores[i][1]=m.getId_ropa();
//				System.out.println(rmodel.find_ropa(m.getId_ropa()).getPrecio());
				i++;
			}
		}
		
		return productos;
	}

	private String[][] busca_modelo_talla(String modelo, String talla) {
		lista = mmodel.find_modelo(modelo);
		
		precios= new String[lista.size()];
		identificadores = new int[lista.size()][2];
		
		int i = 0;
		for (Modelo m : lista) {
			String talla_model = tmodel.find_talla(m.getId_talla()).getTalla();
			if (talla.compareTo(talla_model)==0) {
				i++;
			}
		}
		
		String[][] productos = new String[i][];
		i=0;
		
		for (Modelo m : lista) {
			String talla_model = tmodel.find_talla(m.getId_talla()).getTalla();
			if (talla.compareTo(talla_model)==0) {
				String[] p = new String[6];
				p[0] = modelo;
				p[1] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
				p[2] = talla_model;
				p[3] = cmodel.find_color(m.getId_color()).getColor();
				p[4] = ""+m.getExistencias();
				p[5] = m.getEstado();
				productos[i]= p;
				precios[i]= ""+ rmodel.find_ropa(m.getId_ropa()).getPrecio();
				identificadores[i][0]=m.getId_modelo();
				identificadores[i][1]=m.getId_ropa();
//				System.out.println(rmodel.find_ropa(m.getId_ropa()).getPrecio());
				i++;
			}
		}
		
		return productos;
	}

	private String[][] busca_modelo_talla_color(String modelo, String talla,
			String color) {
		lista = mmodel.find_modelo(modelo);
		
		
		
		precios= new String[lista.size()];
		identificadores = new int[lista.size()][2];
		
		int i = 0;
		for (Modelo m : lista) {
			String color_model = cmodel.find_color(m.getId_color()).getColor();
			String talla_model = tmodel.find_talla(m.getId_talla()).getTalla();
			if (color.compareTo(color_model)==0 && talla.compareTo(talla_model)==0) {
				i++;
			}
		}
		
		String[][] productos = new String[i][];
		i=0;
		for (Modelo m : lista) {
			String color_model = cmodel.find_color(m.getId_color()).getColor();
			String talla_model = tmodel.find_talla(m.getId_talla()).getTalla();
			if (color.compareTo(color_model)==0 && talla.compareTo(talla_model)==0) {
				String[] p = new String[6];
				p[0] = modelo;
				p[1] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
				p[2] = talla_model;
				p[3] = color_model;
				p[4] = ""+m.getExistencias();
				p[5] = m.getEstado();
				productos[i]= p;
				precios[i]= ""+ rmodel.find_ropa(m.getId_ropa()).getPrecio();
				identificadores[i][0]=m.getId_modelo();
				identificadores[i][1]=m.getId_ropa();
//				System.out.println(rmodel.find_ropa(m.getId_ropa()).getPrecio());
				i++;
			}
			
		}
		
		return productos;
	}
}

