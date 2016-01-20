package models;

import instancias.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;

import database.MySQLConnection;
/**
 * Clase que se encarga de conectar a la base de datos de la tabla cuenta
 * @author Cecilia Hernandez Vasquez
 *
 */
public class Cuenta_models {

//	private ArrayList<Cuenta_models> lista_cuentas;
	private ResultSet rs;
	private Connection connection;
	private Statement statement;
	
	/**
	 * Constructor de la clase cuenta
	 */
	public Cuenta_models(){
		rs= null;
		connection = null;
		statement = null;
	}
	
	public int insert_cuenta(Cuenta cuenta){
		int id_cuenta =-1;
		String query = "INSERT INTO Cuenta(fecha,cuenta) VALUES('"+cuenta.getFecha()+"',"+cuenta.getCuenta()+")";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_cuenta=ultima_fila();
			
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
		return id_cuenta;
	}
	
	/**
	 * Actualiza la cuenta de un registro en la base de datos
	 * @param id_cuenta El identificador de la cuenta a modificar
	 * @param fecha fecha de la cuenta
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_cuenta(String fecha, double cuenta){
		String query = "UPDATE Cuenta SET cuenta="+cuenta+" WHERE fecha='"+fecha+"'";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return true;
			
		} catch (SQLException sqle) {
			System.out.println("A ocurrido un error al ejecutar el query a la base de datos");
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	
	/**
	 * Metodo para buscar una cuenta en la base de datos.
	 * @param fecha
	 * @return cuenta
	 */
	public Cuenta find_cuenta(String fecha){
		Cuenta cuenta=null;
		String query = "SELECT * FROM Cuenta WHERE fecha='"+fecha+"'";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 cuenta = new Cuenta();
				 cuenta.setIdCuenta(rs.getInt("id_cuenta"));
				 cuenta.setFecha(rs.getString("fecha"));
				 cuenta.setCuenta(rs.getDouble("cuenta"));
				 
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
		return cuenta;
	}
	
	/*
	 * Obtiene la ultima fila en la tabla modelo
	 */
	private int ultima_fila(){
		
		String query = "SELECT max(id_cuenta) FROM Cuenta";
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
