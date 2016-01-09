package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
			emp[3]=empleado.getFecha_inicio();
			emp[4]=empleado.getTelefono();
			emp[5]=empleado.getDireccion();
			Empleados[i]=emp;
			i++;
			
		}

		return Empleados;
	}
	
	public boolean updateEmpleado(String telefono, String direccion){
		modelEmpleado=new Empleados_model();
		//modelEmpleado.update_direccion_empleado(id_empleado, direccion)
		
		return true;
	}
	
	public String [][] empleado(String nombre,String apellidos){
		modelEmpleado=new Empleados_model();
		empleadoN=modelEmpleado.find_empleado(nombre, apellidos);
		String [][] emp=new String [1][];
		String [] Empleado=new String [6];
		int i=0;
		if (empleadoN != null){
			Empleado[0]=empleadoN.getId_empleado()+"";
			Empleado[1]=empleadoN.getNombre();
			Empleado[2]=empleadoN.getApellidos();
			Empleado[3]=empleadoN.getFecha_inicio();
			Empleado[4]=empleadoN.getTelefono();
			Empleado[5]=empleadoN.getDireccion();
			emp[i]=Empleado;
			i++;
			return emp;
		}else{

			return null;
		}
			
	}
	
	public String fecha(){
		String dia;
		String mes;
		Calendar calendario = new GregorianCalendar();
		int day =calendario.get(Calendar.DATE);
		if (day<10) {
			 dia="0"+day;
			
		}
		else{
			dia=""+day;
		}
		int month =(calendario.get(Calendar.MONTH)+1);
		if (month<10) {
			 mes="0"+month;
			
		}
		else{
			mes=""+month;
		}
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		
		
		return dia+"/"+mes+"/"+year;
	}
	
	
	
	
}//Fin clase








