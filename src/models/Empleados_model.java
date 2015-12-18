package models;

import instancias.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.MySQLConnection;

public class Empleados_model {
	
	private ArrayList<Empleado> lista_empleados;
	private ResultSet rs;
	private Connection connection;
	private Statement statement;
	
	public Empleados_model(){
		rs= null;
		connection = null;
		statement = null;
	}
	
	public int insert_empleado(Empleado empleado){
		int id_empleado=-1;
		String query = "INSERT INTO empleados(nombre,apellidos,fecha_inicio,direccion,telefono) VALUES('"+empleado.getNombre()+"','"+empleado.getApellidos()+"','"+empleado.getFecha_inicio()+"','"+empleado.getDireccion()+"','"+empleado.getTelefono()+"')";
		try {

			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_empleado=ultima_fila();

		} catch (SQLException sqle) {
			System.out.println("A ocurrido un error al ejecutar el query a la base de datos");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id_empleado;
	}
	
	public ArrayList<Empleado> get_empleados(){
		lista_empleados = new ArrayList<Empleado>();
		Empleado empleado = null;
		String query = "SELECT * FROM empleados";
		try {

			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				empleado =new Empleado();
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellidos(rs.getString("apellidos"));
				empleado.setFecha_inicio(rs.getString("fecha_inicio"));
				empleado.setDireccion(rs.getString("direccion"));
				empleado.setTelefono(rs.getString("telefono"));
				lista_empleados.add(empleado);
			}

		} catch (SQLException sqle) {
			System.out.println("A ocurrido un error al ejecutar el query a la base de datos");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lista_empleados;
	}
	
	public Empleado find_empleado(int id_empleado){
		Empleado empleado = null;
		String query = "SELECT * FROM empleados WHERE id_empleado ="+id_empleado;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				empleado =new Empleado();
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellidos(rs.getString("apellidos"));
				empleado.setFecha_inicio(rs.getString("fecha_inicio"));
				empleado.setDireccion(rs.getString("direccion"));
				empleado.setTelefono(rs.getString("telefono")); 
			}
			
		} catch (SQLException sqle) {
			System.out.println("A ocurrido un error al ejecutar el query a la base de datos");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return empleado;
	}
	
	public boolean update_empleado(){
		return false;
	}
	
	public boolean delete_empleado(){
		return false;
		
	}
	/*
	 * Obtiene la ultima fila en la tabla modelo
	 */
	private int ultima_fila(){
		
		String query = "SELECT max(id_empleado) FROM empleados";
		int max = 0;
		try{
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				max = rs.getInt(1);
			}
			
		}catch (SQLException sqle) {
			System.out.println("A ocurrido un error al ejecutar el query a la base de datos");
			System.out.println(sqle.getMessage());
			System.out.println(sqle.toString());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return max;
	}
}
