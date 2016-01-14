package controllers;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private Calendar calendario;
	
	public String obten_venta(int id_venta){
		vmodel = new Ventas_model();
		dvmodel = new Detalle_Venta_model();
		rmodel = new Ropa_model();
		mmodel = new Modelo_model();
		
		ventas = vmodel.find_venta(id_venta);
		detalles = dvmodel.find_detalle_venta(id_venta);
		
		String fechaVenta = ventas.getFecha();
		if (!validaFecha(fechaVenta)) {
			return null;
		}
		String descripcion = "";
		
		descripcion = ventas.getId_venta()+" \t "+ventas.getFecha()+" \t "+ventas.getNo_articulos()+" \t\t"+ventas.getSub_total()+" \t "+ventas.getDescuento()+" \t "+ventas.getTotal_venta()+" \n";
		
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
	
	public String[][] busca_modelo(String modelo){
		mmodel = new Modelo_model();
		Talla_model tmodel = new Talla_model();
		Color_model cmodel = new Color_model();
		rmodel =  new Ropa_model();
		
		ArrayList<Modelo> lista = mmodel.find_modelo(modelo);
		String[][] productos = new String[lista.size()][];
		
		String[] precios= new String[lista.size()];
		int[][] identificadores = new int[lista.size()][2];
		
		int i = 0;
		
		for (Modelo m : lista) {
			String[] p = new String[6];
			p[0] = modelo;
			p[1] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
			p[2] = tmodel.find_talla(m.getId_talla()).getTalla();
			p[3] = cmodel.find_color(m.getId_color()).getColor();
			p[4] = ""+m.getExistencias();
			p[5] = ""+ rmodel.find_ropa(m.getId_ropa()).getPrecio();
			productos[i]= p;
			i++;
		}
		
		return productos;
	}
	
	/**
	 * 
	 * @param modelo
	 * @return
	 */
	public boolean elimina_modelo_en_venta(int pos_art,String modelo,String talla, String color, int no_articulos,double diferencia){//Pasamos como parametros datos del nuevo articulo
		mmodel = new Modelo_model();
		rmodel = new Ropa_model();
		Detalle_Venta dv = detalles.get(pos_art);
		Modelo m = mmodel.find_modelo(modelo, new Talla_model().find_talla(talla).getId_talla(), new Color_model().find_colorN(color).getId_color());
		Ropa r = rmodel.find_ropa(m.getId_ropa());
		double total = ventas.getTotal_venta()+diferencia;
		int total_arts = ventas.getNo_articulos() + (no_articulos-1);
//		System.out.println("Modelo: "+m);
		return dvmodel.update_detalle_venta(dv.getId_detalle_venta(), no_articulos, m.getId_modelo(), m.getId_ropa(),r.getPrecio(), "CAMBIADO") && actualizaExistenciasModeloDevuelto(dv.getId_modelo(),1,m.getId_ropa())
				&& actualizaExistenciasModeloCambiado(m.getId_modelo(),no_articulos,m.getId_ropa()) && vmodel.update_venta_total(ventas.getId_venta(), total)
				&& vmodel.update_venta(ventas.getId_venta(), total_arts);
		
	}
	
	public boolean actualiza_venta(int pos_art, int no_articulos,double diferencia){//Pasamos como parametros datos del nuevo articulo
		mmodel = new Modelo_model();
		rmodel = new Ropa_model();
		vmodel = new Ventas_model();
		Detalle_Venta dv = detalles.get(pos_art);
		Ventas v = vmodel.find_venta(dv.getId_venta());
		double total = v.getTotal_venta()+diferencia;
		int total_arts = v.getNo_articulos()-1;
//		System.out.println("Modelo: "+m);
		return dvmodel.update_detalle_venta(dv.getId_detalle_venta(), dv.getId_modelo(), no_articulos, "VENDIDO") && actualizaExistenciasModeloDevuelto(dv.getId_modelo(),1,dv.getId_ropa())
				&& vmodel.update_venta_total(v.getId_venta(), total) && vmodel.update_venta(v.getId_venta(), total_arts);
		
	}
	
	public boolean agrega_a_venta(String modelo, String talla, String color,int no_articulos) {
		mmodel = new Modelo_model();
		rmodel = new Ropa_model();
		Modelo m = mmodel.find_modelo(modelo, new Talla_model().find_talla(talla).getId_talla(), new Color_model().find_colorN(color).getId_color());
		Ropa r = rmodel.find_ropa(m.getId_ropa());
		ventas = vmodel.find_venta(detalles.get(0).getId_venta());
		int total_arts = ventas.getNo_articulos() + no_articulos;
		Detalle_Venta dv = new Detalle_Venta();
		dv.setCantidad_articulos(no_articulos);
		dv.setEstado("VENDIDO");
		dv.setId_modelo(m.getId_modelo());
		dv.setId_ropa(m.getId_ropa());
		dv.setId_venta(ventas.getId_venta());
		dv.setPrecio_unitario(r.getPrecio());
		int id = dvmodel.insert_detalle_venta(dv);
		actualizaExistenciasModeloCambiado(m.getId_modelo(), no_articulos, m.getId_ropa());
		vmodel.update_venta(ventas.getId_venta(), total_arts);
		return id==-1 ? false : true;
	}
	
	public String fecha(){
		Calendar calendario = new GregorianCalendar();
		int d = calendario.get(Calendar.DATE);
		
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String day = d<=9 ? "0"+Integer.toString(d): Integer.toString(d);
		
		return day+"/"+month+"/"+year;
	}

	private boolean validaFecha(String fechaventa){
		calendario = new GregorianCalendar();
		Calendar calendarioVenta = new GregorianCalendar();
		calendarioVenta.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fechaventa, new ParsePosition(0)));
		if (calendario.get(Calendar.MONTH)==calendarioVenta.get(Calendar.MONTH)) {
			int dias = calendario.get(Calendar.DATE) - calendarioVenta.get(Calendar.DATE);
			if(dias<=7){
				return true;
			}
		}else if (calendario.get(Calendar.YEAR)==calendarioVenta.get(Calendar.YEAR)){
			if((calendario.get(Calendar.MONTH)-calendarioVenta.get(Calendar.MONTH)) == 1){
				int sell_last_day = get_last_day_of_month(calendarioVenta.get(Calendar.MONTH)+1, calendarioVenta.get(Calendar.YEAR));
				int day_diff = sell_last_day - calendarioVenta.get(Calendar.DATE);
				if((day_diff + calendario.get(Calendar.DATE)) <=7){
					return true;
				}
			}
		}else {
			if((calendario.get(Calendar.YEAR)-calendarioVenta.get(Calendar.YEAR)) == 1){
				if((calendario.get(Calendar.MONTH)+1)== 1 && (calendarioVenta.get(Calendar.MONTH)+1)==12){
					int sell_last_day = get_last_day_of_month(calendarioVenta.get(Calendar.MONTH)+1, calendarioVenta.get(Calendar.YEAR));
					int day_diff = sell_last_day - calendarioVenta.get(Calendar.DATE);
					if((day_diff + calendario.get(Calendar.DATE)) <=7){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean actualizaExistenciasModeloDevuelto(int id_modelo, int devueltos, int id_ropa) {
		// TODO Auto-generated method stub
		Modelo m = mmodel.find_modelo(id_modelo);
		Ropa r = rmodel.find_ropa(id_ropa);
		int existencias_modelo = m.getExistencias()+devueltos;
		int existencias_ropa = r.getExistencias()+devueltos;
		return mmodel.update_modelo(id_modelo, existencias_modelo) && rmodel.update_ropa(id_ropa, existencias_ropa);
		
	}

	private boolean actualizaExistenciasModeloCambiado(int id_modelo,int no_articulos, int id_ropa) {
		// TODO Auto-generated method stub
		Modelo m = mmodel.find_modelo(id_modelo);
		Ropa r = rmodel.find_ropa(id_ropa);
		int existencias_modelo = m.getExistencias()-no_articulos;
		int existencias_ropa = r.getExistencias()-no_articulos;
		return mmodel.update_modelo(id_modelo, existencias_modelo) && rmodel.update_ropa(id_ropa, existencias_ropa);
	}

	private static int get_last_day_of_month(int month, int year){
		System.out.println("Mes -> " + month);
		int last_day = 0;
		switch(month){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			last_day = 31;
			break;
		case 2:
			last_day = new GregorianCalendar().isLeapYear(year) ? 29 : 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			last_day = 30;
			break;
		}
		return last_day;
	}
	
}

