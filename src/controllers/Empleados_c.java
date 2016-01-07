package controllers;

import java.util.ArrayList;

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
	
	
	public  String[][] Empleados(){
		modelEmpleado=new Empleados_model();

		ArrayList<Empleado> listaEmpleado;

		listaEmpleado=modelEmpleado.get_empleados();
		String [][] Empleados=new String [listaEmpleado.size()][];

		//System.out.println(listaEmpleado.size());
		int i=0;
		for (Empleado empleado : listaEmpleado) {
			String [] emp=new String [6];
			emp[0]=""+empleado.getId_empleado();
			emp[1]=empleado.getNombre();
			emp[2]=empleado.getApellidos();
			emp[3]=empleado.getTelefono();
			emp[4]=empleado.getDireccion();
			emp[5]=empleado.getFecha_inicio();
			Empleados[i]=emp;
			i++;
			
		}

		return Empleados;
	}
	
	public String [] empleado(String nombre,String apellidos){
		modelEmpleado=new Empleados_model();
		empleadoN=modelEmpleado.find_empleado(nombre, apellidos);
		String [] Empleado=new String [6];
		if (empleadoN != null){
			Empleado[5]=empleadoN.getId_empleado()+"";
			Empleado[0]=empleadoN.getNombre();
			Empleado[1]=empleadoN.getApellidos();
			Empleado[2]=empleadoN.getTelefono();
			Empleado[3]=empleadoN.getDireccion();
			Empleado[4]=empleadoN.getFecha_inicio();
			return Empleado;
		}else{

			return null;
		}
			
	}
	
	
}//Fin clase








