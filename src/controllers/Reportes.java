package controllers;

import instancias.Modelo;
import instancias.Ventas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import models.*;

/**
 * 
 * @author Gilberto Aviles <BR>
 * twitter @rinzaku04
 *
 */
public class Reportes {

	private Modelo_model mmodel;
	private Ropa_model rmodel;
	private Color_model cmodel;
	private Talla_model tmodel;
	private Ventas_model vmodel;
	private Empleados_model emodel;
	private ArrayList<Modelo> modelos;
	private ArrayList<Ventas> ventas;
	
	public String[][] get_inventario(){
		mmodel = new Modelo_model();
		rmodel = new Ropa_model();
		tmodel = new Talla_model();
		cmodel = new Color_model();
		
		modelos = mmodel.get_modelo();
		String[][] productos = new String[modelos.size()][];
		int i=0;
		for (Modelo m : modelos) {
			String[] p = new String[8];
			p[0] = m.getModelo();
			p[1] = rmodel.find_ropa(m.getId_ropa()).getPrenda();
			p[2] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
			p[3] = tmodel.find_talla(m.getId_talla()).getTalla();
			p[4] = cmodel.find_color(m.getId_color()).getColor();
			p[5] = ""+m.getExistencias();
			p[6] = ""+rmodel.find_ropa(m.getId_ropa()).getPrecio();
			p[7] = m.getEstado();
			productos[i] = p;
			i++;
		}
		return productos;
	}
	
	public String[][] get_ventas(int tipo_reporte, String mes_anio){
		vmodel = new Ventas_model();
		emodel = new Empleados_model();
		if(tipo_reporte==2){ //Ventas del dia
			ventas = vmodel.find_ventas(fecha());
		}else{ //Ventas del mes
			String fecha = mes_anio;
			ventas = vmodel.find_ventas(fecha);
		}
		
		String[][] venta = new String[ventas.size()][];
		int i=0;
		for (Ventas v : ventas) {
			String[] vt = new String[9];
			vt[0] = ""+v.getId_venta();
			vt[1] = emodel.find_empleado(v.getNo_empleado()).getNombre() +" "+emodel.find_empleado(v.getNo_empleado()).getApellidos();
			vt[2] = ""+v.getNo_articulos();
			vt[3] = ""+v.getSub_total();
			vt[4] = ""+v.getDescuento();
			vt[5] = ""+v.getTotal_venta();
			vt[6] = ""+v.getAbono();
			vt[7] = v.getEstado();
			vt[8] = v.getFecha();
			venta[i] = vt;
			i++;
		}
		return venta;
	}
	
	public boolean crearPDF(String file_name, int tipo_reporte, String mes){
		String titulo = tipo_reporte == 1 ? "INVENTARIO" : "REPORTE DE VENTAS";
		String subtitulo = "Reporte correspondiente a la fecha: "+fecha();
		String autor = "Arturo Gonzalez Hernandez";
		String desc = "";
		if (tipo_reporte == 1) {
			desc = "Este reporte muestra información correspondiente a los productos que se encuentran en la tienda";
		} else if (tipo_reporte == 2) {
			desc = "Este reporte muestra información respecto a las ventas realizadas el dia de hoy";
		} else {
			desc = "Este reporte muestra información respecto a las ventas realizadas en el mes "+get_mes(mes);
		}
		try {
			PDF pdf = new PDF(file_name);
			pdf.addMetaData(titulo, subtitulo, autor);
			pdf.addCabecera();
			pdf.addTitlePage(titulo, fecha(), autor, desc);
			if(tipo_reporte == 1) pdf.addContent_Inventario(modelos);
			else if(tipo_reporte == 2){
				pdf.addContent_Ventas_dia(ventas,mes);//Aqui agregamos lo de la cuenta
			}
			else pdf.addContent_Ventas_mes(ventas);
			pdf.cerrar_doc();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	private String fecha(){
		Calendar calendario = new GregorianCalendar();
		int d = calendario.get(Calendar.DATE);
		int m = calendario.get(Calendar.MONTH)+1;
		String day = d<=9 ? "0"+Integer.toString(d): Integer.toString(d);
		String month = m<=9 ? "0"+Integer.toString(m) : Integer.toString(m);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		
		return day+"/"+month+"/"+year;
	}
	
	private String get_mes(String mes_anio){
		String[] div = mes_anio.split("/");
		String mes = get_mes_letra(Integer.parseInt(div[1])) + "/" + div[2];
		return mes;
	}
	
	private String get_mes_letra(int mes){
		String mes_letra = "";
		switch(mes){
		case 1: mes_letra = "Enero"; break;
		case 2: mes_letra = "Febrero"; break;
		case 3: mes_letra = "Marzo"; break;
		case 4: mes_letra = "Abril"; break;
		case 5: mes_letra = "Mayo"; break;
		case 6: mes_letra = "Junio"; break;
		case 7: mes_letra = "Julio"; break;
		case 8: mes_letra = "Agosto"; break;
		case 9: mes_letra = "Septiembre"; break;
		case 10: mes_letra = "Octubre"; break;
		case 11: mes_letra = "Noviembre"; break;
		case 12: mes_letra = "Diciembre"; break;
		}
		return mes_letra;
	}
}
