package controllers;

import models.Empleados_model;
import instancias.Empleado;

public class Empleados_c {
	Empleado empleadoN;
	Empleados_model modelEmpleado;
	
	public int insertEmpleado(String nombre,String Apellido,String direccion,String tel,String fecha){
		int clave=0;
		empleadoN=new Empleado();
		modelEmpleado=new Empleados_model();
		empleadoN.setNombre(nombre);
		empleadoN.setApellidos(Apellido);
		empleadoN.setDireccion(direccion);
		empleadoN.setTelefono(tel);
		empleadoN.setFecha_inicio(fecha);
		
		clave=modelEmpleado.insert_empleado(empleadoN);
		if (clave>-1) {
			System.out.println("la clave del if es :"+clave);
			return clave;
		}
		else{
			System.out.println("else no se efectuo la insercion");
			return -1;
		}
		
		
	}
	
}
