package controllers;

import instancias.Cuenta;
import models.Cuenta_models;

/**
 * Clase que se encarga de la logica de la cuenta *.^/
 * @author Cecilia Hernandez Vasquez
 *
 */

public class Cuenta_c {
	Cuenta_models cuentaModels;
	Cuenta cuentaInstancia;
	
	public int insertaCuenta(String fecha,double cuenta){
		cuentaInstancia=new Cuenta();
		cuentaModels=new Cuenta_models();
		int id_cuenta;
		
		cuentaInstancia.setFecha(fecha);
		cuentaInstancia.setCuenta(cuenta);
		
		if (cuentaModels.find_cuenta(fecha)==null) {
			id_cuenta=cuentaModels.insert_cuenta(cuentaInstancia);
			if (id_cuenta==-1) {
				return -1;
			}else{
				return id_cuenta;
			}
		}
		else{
			return -1;
		}
		
	}
	
	public double find_cuenta(String fecha){
		cuentaInstancia=new Cuenta();
		cuentaModels=new Cuenta_models();
		
		cuentaInstancia=cuentaModels.find_cuenta(fecha);
		if (cuentaInstancia!=null) {
			return cuentaInstancia.getCuenta();
		}else{
			return -1;
		}
		
	}
	
	public boolean updateCuenta(String fecha,double cuenta){
		cuentaModels=new Cuenta_models();
		boolean bnd =false;
		bnd=cuentaModels.update_cuenta(fecha, cuenta);
		System.out.println(cuentaModels.update_cuenta(fecha, cuenta));
		if (!bnd) {
			return false;
		}
		else{
			return bnd;
		}
		
	}
	
}











