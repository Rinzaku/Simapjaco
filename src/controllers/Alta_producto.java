package controllers;


import java.util.Calendar;
import java.util.GregorianCalendar;

import models.Color_model;
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


}//fin clase




















