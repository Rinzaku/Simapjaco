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
public class Alta_producto {

	Ropa ropa;

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

	public boolean altaProducto(String modelo,String nombreP,String descripcion,String talla,String color,double existencia, double precio ){
		int idColor=Color(color);
		int idTalla=Talla(talla);
		int idRopa=Ropa(nombreP, descripcion,(int) existencia, precio);
		Modelo modeloI=new Modelo();
		modeloI.setExistencias((int)existencia);
		modeloI.setId_color(idColor);
		modeloI.setId_ropa(idRopa);
		modeloI.setId_talla(idTalla);
		modeloI.setModelo(modelo);
		modeloI.setEstado("ACTIVO");

		Modelo_model modeloM=new Modelo_model();
		int model=modeloM.insert_modelo(modeloI);
		if (model<0) {
			return false;

		}else{
			return true;
		}

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

	public String [][] buscarModel(String modelo,String talla,String color){
		String [] models=new String[7];
		String [][] model=new String [1][];
		Modelo modelI;
		Modelo_model modelM=new Modelo_model();
		Talla_model tallaM=new Talla_model();
		Talla tallaI=tallaM.find_talla(talla);

		Color_model colorM=new Color_model();
		Color colorI=colorM.find_colorN(color);

		modelI=modelM.find_modelo(modelo, tallaI.getId_talla(), colorI.getId_color());
		System.out.println(modelI);
		Ropa_model ropaM=new Ropa_model();

		Ropa ropaI;
		ropaI=ropaM.find_ropa(modelI.getId_ropa());


		double precio=1000;
		ropaM.update_ropa(modelI.getId_ropa(),precio );

		models[0]=modelo;
		models[1]=ropaI.getPrenda();
		models[2]=ropaI.getDescricion();
		models[3]=tallaI.getTalla();
		models[4]=colorI.getColor();
		models[5]=""+ropaI.getExistencias();
		models[6]=""+ropaI.getPrecio();
		model[0]=models;
		return model;

	}

	public String [][] buscarModeloMT(String modelo, String talla){
		Modelo_model modelM=new Modelo_model();
		Ropa_model rmodel = new Ropa_model();
		Color_model cmodel = new Color_model();
		Modelo modelI;
		ArrayList<Modelo> arrModel;
		Talla_model tallaM=new Talla_model();
		Talla tallaI=tallaM.find_talla(talla);
		arrModel=modelM.find_modelo(modelo, ""+tallaI.getId_talla());
		System.out.println(arrModel.size());
		String[][] productos = new String[arrModel.size()][];
		int i=0;
		for (Modelo modelo2 : arrModel) {
			Ropa r = rmodel.find_ropa(modelo2.getId_ropa());
			String[] datos = new String[7];
			datos[0] = modelo2.getModelo();
			datos[1] = r.getPrenda();
			datos[2] = r.getDescricion();
			datos[3] = talla;
			datos[4] = cmodel.find_color(modelo2.getId_color()).getColor();
			datos[5] = ""+modelo2.getExistencias();
			datos[6] = ""+r.getPrecio();
			productos[i]=datos;
			i++;
		}
		return productos;
	}


}//fin clase




