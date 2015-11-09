package models;

import instancias.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.MySQLConnection;

/**
 * Clase que se encarga de conectar con la tabla catalogo_talla en la base de datos
 * @author Gilberto Aviles (@rinzaku04)
 *
 */
public class Talla_model {
	
	private ArrayList<Talla> lista_tallas;
	private ResultSet rs;
	private Connection connection;
	private Statement statement;
	
	/**
	 * Constructor de la clase 
	 */
	public Talla_model(){
		rs= null;
		connection = null;
		statement = null;
	}
	
	/**
	 * Inserta una nueva talla en la base de datos
	 * @param talla La nueva talla a ingresar
	 * @return El indice de la talla ingresada
	 */
	public int insert_talla(Talla talla){
		int id_talla =-1;
		String query = "INSERT INTO catalogo_talla(talla) VALUES('"+talla.getTalla()+"')";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_talla=ultima_fila();
			
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
		return id_talla;
	}
	
	/**
	 * Obtiene una lista con las tallas en la base de datos
	 * @return Una lista con las tallas del catalogo
	 */
	public ArrayList<Talla> get_tallas(){
		lista_tallas = new ArrayList<Talla>();
		Talla talla= null;
		String query = "SELECT * FROM catalogo_talla";

		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				talla = new Talla();
				talla.setId_talla(rs.getInt("id_talla"));
				talla.setTalla(rs.getString("talla"));
				lista_tallas.add(talla);
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
		return lista_tallas;
	}
	
	/**
	 * Obtiene una talla en el catalogo
	 * @param id_talla El identificador de la talla a obtener
	 * @return La talla correspondiente
	 */
	public Talla find_talla(int id_talla){
		Talla talla=null;
		String query = "SELECT * FROM catalogo_talla WHERE id_talla="+id_talla;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 talla = new Talla();
				 talla.setId_talla(rs.getInt("id_talla"));
				 talla.setTalla(rs.getString("talla"));;
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
		return talla;
	}
	
	/**
	 * Actualiza la talla de un registro en a base de datos
	 * @param id_talla El identificador de la talla a modificar
	 * @param talla El valor de la nueva talla
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_talla(int id_talla, String talla){
		String query = "UPDATE catalogo_talla SET talla='"+talla+"' WHERE id_talla="+id_talla;
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
	 * Elimina un registro de la base de datos
	 * @param id_talla El identificador del registro a eliminar
	 * @return <b>true</b> si el registro se elimino exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean delete_talla(int id_talla){
		String query = "DELETE FROM catalogo_talla WHERE id_talla="+id_talla;
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
	
	/*
	 * Obtiene la ultima fila en la tabla modelo
	 */
	private int ultima_fila(){
		
		String query = "SELECT max(id_talla) FROM catalogo_talla";
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
