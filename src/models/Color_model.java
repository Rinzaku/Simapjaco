package models;

import instancias.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.MySQLConnection;

/**
 * Clase que e encarga de conectar con la tabla catalogo_color
 * @author Gilberto Aviles (@rinzaku04)
 *
 */
public class Color_model {
	
	private ArrayList<Color> lista_color;
	private ResultSet rs;
	private Connection connection;
	private Statement statement;
	
	/**
	 * El constructor de la clase
	 */
	public Color_model(){
		
		rs= null;
		connection = null;
		statement = null;
	}
	
	/**
	 * Inserta un color en la base de datos
	 * @param color El color a insertar en la base de datos
	 * @return El indice del registro insertado
	 */
	public int insert_color(Color color){
		int id_color =-1;
		String query = "INSERT INTO catalogo_color(color) VALUES('"+color.getColor()+"')";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_color=ultima_fila();
			
		} catch (SQLException sqle) {
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id_color;
	}
	
	
	public int insert_color(String color){
		int id_color =-1;
		String query = "INSERT INTO catalogo_color(color) VALUES('"+color+"')";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_color=ultima_fila();
			
		} catch (SQLException sqle) {
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id_color;
	}
	
	
	
	/**
	 * Obtiene una lista con los colores en la base de datos
	 * @return Una lista de los colores en la base de datos
	 */
	public ArrayList<Color> get_color(){
		lista_color = new ArrayList<Color>();
		Color color= null;
		String query = "SELECT * FROM catalogo_color";

		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				color = new Color();
				color.setId_color(rs.getInt("id_color"));
				color.setColor(rs.getString("color"));
				lista_color.add(color);
			}
			
		} catch (SQLException sqle) {
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lista_color;
	}
	
	/**
	 * Obtiene un color en la base de datos
	 * @param id_color El identificador del color a obtener
	 * @return El color correspondiente
	 */
	public Color find_color(int id_color){
		Color color=null;
		String query = "SELECT * FROM catalogo_color WHERE id_color="+id_color;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 color = new Color();
				 color.setId_color(rs.getInt("id_color"));
				 color.setColor(rs.getString("color"));;
			}
			
		} catch (SQLException sqle) {
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return color;
	}
	
	/**
	 * Actualiza el color de un registro en la base de datos
	 * @param id_color El identificador del registro a modifcar
	 * @param color El nuevo color del registro
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_color(int id_color, String color){
		String query = "UPDATE catalogo_color SET color='"+color+"' WHERE id_color="+id_color;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return true;
			
		} catch (SQLException sqle) {
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
	 * @param id_color El identificador del registro a eliminar
	 * @return <b>true</b> si el registro se elimino exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean delete_color(int id_color){
		String query = "DELETE FROM catalogo_color WHERE id_color="+id_color;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return true;
			
		} catch (SQLException sqle) {
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
		
		String query = "SELECT max(id_color) FROM catalogo_color";
		int max = 0;
		try{
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				max = rs.getInt(1);
			}
			
		}catch (SQLException sqle) {
		
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
	
	/**
	 * Regresa el id del color a buscar
	 * @param id_color
	 * @return
	 */
	public Color find_colorN(String colorN){
		Color color=null;
		String query = "SELECT * FROM catalogo_color WHERE color='"+colorN+"'";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 color = new Color();
				 color.setId_color(rs.getInt("id_color"));
				 color.setColor(rs.getString("color"));;
			}
			
		} catch (SQLException sqle) {
			
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return color;
	}
	
	
}












