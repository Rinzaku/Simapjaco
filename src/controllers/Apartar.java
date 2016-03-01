package controllers;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.itextpdf.text.log.SysoCounter;

import models.Detalle_Venta_model;
import models.Modelo_model;
import models.Talla_model;
import models.Ventas_model;
import instancias.Detalle_Venta;
import instancias.Modelo;
import instancias.Talla;
import instancias.Ventas;

public class Apartar {
	
	Detalle_Venta detalleVtn;
	private Modelo_model modelo;
	private Calendar calendario;
	private Ventas_model ventaModel;
	private Ventas ventaIns;
	private Detalle_Venta_model ventsDetallemodel;
	private Modelo modelIns;
	
	private int creaVenta(String fecha,double Abono,double precio,int empleado) {
		int id=0;
		Ventas_model VentasModel = new Ventas_model();
		Ventas venta = new Ventas();
		
		venta.setFecha(fecha);
		venta.setNo_articulos(1);
		venta.setAbono(Abono);
		venta.setEstado("APARTADO");
		venta.setTotal_venta(precio);
		venta.setNo_empleado(empleado);
		id=VentasModel.insert_venta(venta);
		
		return id;
	}
	
	public boolean regresarProducto(int Folio){
		Modelo modeloI=new Modelo();
		ventsDetallemodel=new Detalle_Venta_model();
		ventaModel =new Ventas_model();
		modelo =new Modelo_model();
		detalleVtn=ventsDetallemodel.findDetalleVenta(Folio);
		if (detalleVtn!=null) {
			ventaIns=ventaModel.find_venta(detalleVtn.getId_venta());
			modelIns=modelo.find_modelo(detalleVtn.getId_modelo());
			modelo.update_modelo(detalleVtn.getId_modelo(),modelIns.getExistencias()+1);
			ventaModel.update_venta_estado(detalleVtn.getId_venta(), "APARTADO CANCELADO");
			return true;
		}
		return false;
	}

	public boolean apartarProducto(String modelo, String Descripcion, String Talla, String Color,String Precio,String Fecha,Double Abono,int idModelo,int ropa, int empleado){
		int id_venta=0;
		System.out.println("idModelo :"+idModelo);
		System.out.println("id ropa :"+ropa);
		Detalle_Venta_model detalleModel=new Detalle_Venta_model();
		id_venta =creaVenta(Fecha,Abono,Double.parseDouble(Precio),empleado);
		if (id_venta<0) {
			return false;
		}
		else{
			detalleVtn=new Detalle_Venta();
			detalleVtn.setId_venta(id_venta);
			detalleVtn.setId_modelo(idModelo);
			detalleVtn.setId_ropa(ropa);
			detalleVtn.setCantidad_articulos(1);
			detalleVtn.setEstado("APARTADO");
			detalleVtn.setPrecio_unitario(Double.parseDouble(Precio));
			int idDetalle= detalleModel.insert_detalle_venta(detalleVtn);
			if (idDetalle<0){
				return false;
			}else{
				return true;
			}

		}
		
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
	
	public boolean abono(int folio,double abono){
		Ventas_model update =new  Ventas_model();
		
		if (update.update_venta(folio, abono)) 
			return true;

		else
			return false;
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

	  private boolean validaFecha(String fechaventa){
	    calendario = new GregorianCalendar();
	    Calendar calendarioVenta = new GregorianCalendar();
	    calendarioVenta.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fechaventa, new ParsePosition(0)));
	    if (calendario.get(Calendar.MONTH)==calendarioVenta.get(Calendar.MONTH)) {
	      int dias = calendario.get(Calendar.DATE) - calendarioVenta.get(Calendar.DATE);
	      if(dias<=20){
	        return true;
	      }
	    }else if (calendario.get(Calendar.YEAR)==calendarioVenta.get(Calendar.YEAR)){
	      if((calendario.get(Calendar.MONTH)-calendarioVenta.get(Calendar.MONTH)) == 1){
	        int sell_last_day = get_last_day_of_month(calendarioVenta.get(Calendar.MONTH)+1, calendarioVenta.get(Calendar.YEAR));
	        int day_diff = sell_last_day - calendarioVenta.get(Calendar.DATE);
	        if((day_diff + calendario.get(Calendar.DATE)) <=20){
	          return true;
	        }
	      }
	    }else {
	      if((calendario.get(Calendar.YEAR)-calendarioVenta.get(Calendar.YEAR)) == 1){
	        if((calendario.get(Calendar.MONTH)+1)== 1 && (calendarioVenta.get(Calendar.MONTH)+1)==12){
	          int sell_last_day = get_last_day_of_month(calendarioVenta.get(Calendar.MONTH)+1, calendarioVenta.get(Calendar.YEAR));
	          int day_diff = sell_last_day - calendarioVenta.get(Calendar.DATE);
	          if((day_diff + calendario.get(Calendar.DATE)) <=20){
	            return true;
	          }
	        }
	      }
	    }
	    return false;
	  }
	
	
	public String  buscaModelo (int folio){
		Ventas venta;
		Ventas_model vtnModel=new Ventas_model();
		venta =vtnModel.find_venta(folio);


		if (venta!=null) {
			if(validaFecha(venta.getFecha())){
				String abono =""+venta.getAbono();
				return (abono);
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}

	}
	
	
	

}//fin clase









