package controllers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import models.Color_model;
import models.Modelo_model;
import models.Talla_model;
import instancias.*;
import models.*;
/**
 * 
 * @author Cecilia Hernandez Vasquez
 *
 */
public class Productos_admin {

	Ropa ropa;
	ArrayList<Modelo> modelos;
	
	/**
	 * Busca el color en la base de datos, en caso de que se encuentre regresa el id sino lo inserta a la tabla  
	 * @param color
	 * @return id del color
	 */
	public int Color(String color){
		Color_model colorsModel=new Color_model();
		instancias.Color colorI=colorsModel.find_colorN(color);
		if(colorI == null){
			int colores=colorsModel.insert_color(color);
			return colores;
		}
		else{
			return colorI.getId_color();
		}
	}

	public int Talla(String talla){
		Talla_model tallaM=new Talla_model();
		Talla tallaT=tallaM.find_talla(talla);
		if (tallaT==null) {
			int  tallaI=tallaM.insert_talla(talla);
			return tallaI;
		}
		else {
			return tallaT.getId_talla();
		}

	}

	public int Ropa(String nombreRopa,String descripcion,int existencias,double precio){
		Ropa ropa=new Ropa();
		ropa.setDescricion(descripcion);
		ropa.setExistencias(existencias);
		ropa.setPrecio(precio);
		ropa.setPrenda(nombreRopa);
		Ropa_model ropaM=new Ropa_model();
		int insert=ropaM.insert_ropa(ropa);

		if(insert>0){
			return insert;
		}else{
			return -1;
		}



	}

	public boolean altaProducto(String modelo,String nombreP,String descripcion,String talla,String color,double existencia, double precio, String pathImagen ){

		Modelo_model modeloM=new Modelo_model();
		Ropa_model rmodel = new Ropa_model();
		int idColor=Color(color);
		int idTalla=Talla(talla);
		ArrayList<Modelo> models = modeloM.find_modelo(modelo);
		int idRopa=-1;
		int existencias =0;
		if(models.size() == 0){
			idRopa= Ropa(nombreP, descripcion,(int) existencia, precio);
		}else{
			existencias = rmodel.find_ropa(models.get(0).getId_ropa()).getExistencias() + (int)existencia;
			rmodel.update_ropa(models.get(0).getId_ropa(), existencias);
			idRopa = models.get(0).getId_ropa();
		}
//		int idRopa= Ropa(nombreP, descripcion,(int) existencia, precio);
		
		Modelo modeloI=new Modelo();
		modeloI.setExistencias((int)existencia);
		modeloI.setId_color(idColor);
		modeloI.setId_ropa(idRopa);
		modeloI.setId_talla(idTalla);
		modeloI.setModelo(modelo);
		modeloI.setEstado("ACTIVO");
		modeloI.setImagen(pathImagen);
		int model=modeloM.insert_modelo(modeloI);
		
		return model < 0 ? false : true;

	}

	public String fecha(){
		Calendar calendario = new GregorianCalendar();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));

		return day+"/"+month+"/"+year;
	}

	public String [] tallas(){
		Talla_model tallaModel=new Talla_model();
		ArrayList<Talla> tallaI=tallaModel.get_tallas();
		String tallas[]=new String[tallaI.size()];

		for (int i = 0; i < tallaI.size(); i++) {
			tallas[i]=tallaI.get(i).getTalla();
		}

		return tallas;

	}

	public String[][] buscar(String modelo, String talla, String color){
		if(modelo.compareTo("")!=0 && talla.compareTo("")==0 && color.compareTo("")==0){
			return buscarModelo(modelo);
		}else if(modelo.compareTo("")!=0 && talla.compareTo("")!=0 && color.compareTo("")==0){
			return buscarModeloMT(modelo, talla);
		}else if(modelo.compareTo("")!=0 && talla.compareTo("")==0 && color.compareTo("")!=0){
			return buscarModeloMC(modelo, color);
		}else{
			return buscarModelo_MTC(modelo, talla, color);
		}
	}
	
	private String[][] buscarModelo(String modelo){
		Modelo_model mmodel = new Modelo_model();
		Ropa_model rmodel = new Ropa_model();
		modelos = mmodel.find_modelo(modelo);
		String[][] arts = new String[modelos.size()][];
		int i=0;
		for (Modelo m : modelos) {
			String[] articulo = new String[8];
			articulo[0] = m.getModelo();
			articulo[1] = rmodel.find_ropa(m.getId_ropa()).getPrenda();
			articulo[2] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
			articulo[3] = new Talla_model().find_talla(m.getId_talla()).getTalla();
			articulo[4] = new Color_model().find_color(m.getId_color()).getColor();
			articulo[5] = ""+m.getExistencias();
			articulo[6] = ""+rmodel.find_ropa(m.getId_ropa()).getPrecio();
			articulo[7] = m.getImagen();
			arts[i]=articulo;
			i++;
		}
		return arts;
	}
	
	private String [][] buscarModelo_MTC(String modelo,String talla,String color){
		Modelo_model mmodel = new Modelo_model();
		Ropa_model rmodel = new Ropa_model();
		Talla_model tmodel = new Talla_model();
		Color_model cmodel = new Color_model();
		modelos = new ArrayList<Modelo>();
		modelos.add(mmodel.find_modelo(modelo,tmodel.find_talla(talla).getId_talla(), cmodel.find_colorN(color).getId_color()));
		String[][] arts = new String[modelos.size()][];
		int i=0;
		for (Modelo m : modelos) {
			String[] articulo = new String[8];
			articulo[0] = m.getModelo();
			articulo[1] = rmodel.find_ropa(m.getId_ropa()).getPrenda();
			articulo[2] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
			articulo[3] = new Talla_model().find_talla(m.getId_talla()).getTalla();
			articulo[4] = new Color_model().find_color(m.getId_color()).getColor();
			articulo[5] = ""+m.getExistencias();
			articulo[6] = ""+rmodel.find_ropa(m.getId_ropa()).getPrecio();
			articulo[7] = m.getImagen();
			arts[i]=articulo;
			i++;
		}
		return arts;

	}

	private String [][] buscarModeloMT(String modelo, String talla){
		Modelo_model modelM=new Modelo_model();
		Ropa_model rmodel = new Ropa_model();
		Color_model cmodel = new Color_model();
		Talla_model tallaM=new Talla_model();
		
		modelos=modelM.find_modelo(modelo, ""+tallaM.find_talla(talla).getId_talla());
		System.out.println(modelos.size());
		String[][] productos = new String[modelos.size()][];
		int i=0;
		for (Modelo m : modelos) {
			Ropa r = rmodel.find_ropa(m.getId_ropa());
			String[] datos = new String[8];
			datos[0] = m.getModelo();
			datos[1] = r.getPrenda();
			datos[2] = r.getDescricion();
			datos[3] = talla;
			datos[4] = cmodel.find_color(m.getId_color()).getColor();
			datos[5] = ""+m.getExistencias();
			datos[6] = ""+r.getPrecio();
			datos[7] = m.getImagen();
			productos[i]=datos;
			i++;
		}
		return productos;
	}
	
	private String [][] buscarModeloMC(String modelo, String color){
		Modelo_model modelM=new Modelo_model();
		Ropa_model rmodel = new Ropa_model();
		Color_model cmodel = new Color_model();
		Talla_model tallaM=new Talla_model();
		
		modelos=modelM.find_modelo(modelo, ""+cmodel.find_colorN(color).getId_color());
		System.out.println(modelos.size());
		String[][] productos = new String[modelos.size()][];
		int i=0;
		for (Modelo m : modelos) {
			Ropa r = rmodel.find_ropa(m.getId_ropa());
			String[] datos = new String[8];
			datos[0] = m.getModelo();
			datos[1] = r.getPrenda();
			datos[2] = r.getDescricion();
			datos[3] = tallaM.find_talla(m.getId_talla()).getTalla();
			datos[4] = color;
			datos[5] = ""+m.getExistencias();
			datos[6] = ""+r.getPrecio();
			datos[7] = m.getImagen();
			productos[i]=datos;
			i++;
		}
		return productos;
	}
	
	public boolean update_existencias(int existencias, int pos_articulo){
		Modelo_model mmodel = new Modelo_model();
		return mmodel.update_modelo(modelos.get(pos_articulo).getId_modelo(), existencias);
	}
	
	public boolean update_precio(double precio, int pos_articulo){
		Ropa_model rmodel = new Ropa_model();
		return rmodel.update_ropa(modelos.get(pos_articulo).getId_ropa(), precio);
	}
	
	public boolean delete_producto(int pos_articulo){
		Modelo_model mmodel = new Modelo_model();
		return mmodel.update_estado(modelos.get(pos_articulo).getId_modelo(), "ELIMINADO");
	}
}//fin clase




