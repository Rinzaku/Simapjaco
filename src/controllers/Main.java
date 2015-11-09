package controllers;

import java.util.ArrayList;

import models.*;
import instancias.*;

public class Main {
	
	static Ropa_model modelo;
	
	public static void main(String[] args){
		
		modelo = new Ropa_model();
		
		ArrayList<Ropa> lista = new ArrayList<Ropa>();
		lista = modelo.get_ropa();
		
		System.out.println("Lista antes del insert\n");
		for (Ropa ropa : lista) {
			System.out.println("Ropa id: ->"+ropa.toString());
		}
		
		Ropa r = new Ropa();
		r.setDescricion("nueva ropa 4");
		r.setExistencias(20);
		r.setPrenda("algo");
		
		System.out.println("id del insert-> "+modelo.insert_ropa(r));
		
		lista = modelo.get_ropa();
		
		System.out.println("Lista despues del insert\n");
		for (Ropa ropa : lista) {
			System.out.println("Ropa id: ->"+ropa.toString());
		}
		
		if (modelo.update_ropa(20, 532)) {
			System.out.println("Lista despues del update");
			
			lista = modelo.get_ropa();
			for (Ropa ropa : lista) {
				System.out.println("Ropa id: ->"+ropa.toString());
			}
		}else{
			System.out.println("No se pudo actualizar");
		}
		
		Ropa r2 = modelo.find_ropa(8);
		System.out.println("Ropa obtenida -> "+ r2);
		
		if (modelo.delete_ropa(30)) {
			lista = modelo.get_ropa();
			
			System.out.println("Lista despues del delete");
			for (Ropa ropa : lista) {
				System.out.println("Ropa id: ->"+ropa.toString());
			}
		} else {
			System.out.println("No se pudo borrar");
		}
		
		
	}
}
