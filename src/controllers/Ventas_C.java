package controllers;

import instancias.*;
import models.*;
import vistas.*;

import java.util.*;

import javax.swing.JFrame;

public class Ventas_C {
	
	String[] precios;
	
	public String[][] busca_modelo(String modelo){
		Modelo_model mmodel = new Modelo_model();
		Talla_model tmodel = new Talla_model();
		Color_model cmodel = new Color_model();
		Ropa_model rmodel =  new Ropa_model();
		
		ArrayList<Modelo> lista = mmodel.find_modelo(modelo);
		String[][] productos = new String[lista.size()][];
		precios= new String[lista.size()];
		int i = 0;
		
		for (Modelo m : lista) {
			String[] p = new String[6];
			p[0] = modelo;
			p[1] = rmodel.find_ropa(m.getId_ropa()).getDescricion();
			p[2] = tmodel.find_talla(m.getId_talla()).getTalla();
			p[3] = cmodel.find_color(m.getId_color()).getColor();
			p[4] = ""+m.getExistencias();
			p[5] = "";
			productos[i]= p;
			precios[i]= ""+ rmodel.find_ropa(m.getId_ropa()).getPrecio();
			System.out.println(rmodel.find_ropa(m.getId_ropa()).getPrecio());
			i++;
		}
		
		return productos;
	}
	
	public String[] obten_precios(){
		return precios;
	}
}
