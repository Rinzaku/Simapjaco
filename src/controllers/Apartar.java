package controllers;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.itextpdf.text.log.SysoCounter;

import models.Detalle_Venta_model;
import models.Talla_model;
import models.Ventas_model;
import instancias.Detalle_Venta;
import instancias.Talla;
import instancias.Ventas;

public class Apartar {
	
	Detalle_Venta detalleVtn;
	private Calendar calendario;

	
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
	
	public boolean validaFecha(String fechaventa){
		calendario = new GregorianCalendar();
		Calendar calendarioVenta = new GregorianCalendar();
		calendarioVenta.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fechaventa, new ParsePosition(0)));
		if (calendario.get(Calendar.MONTH)==calendarioVenta.get(Calendar.MONTH)) {
			int dias = calendario.get(Calendar.DATE) - calendarioVenta.get(Calendar.DATE);
			if(dias<=1){
				return true;
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









