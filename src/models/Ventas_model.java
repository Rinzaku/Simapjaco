package models;

import java.sql.*;
import java.util.ArrayList;

import instancias.*;
import database.*;

/**
 * Clase que se encarga de conectar con la tabla de ventas
 * @author Gilberto Aviles (@rinzaku04)
 *
 */
public class Ventas_model {
	
	private ArrayList<Ventas> lista_ventas;
	private ResultSet rs;
	private Connection connection;
	private Statement statement;
	
	/**
	 * Constructor de la clase
	 */
	public Ventas_model(){
		
		rs = null;
		connection = null;
		statement = null;
	}
	
	/**
	 * Inserta una venta en la base de datos
	 * @param venta La venta a ingresar a la base de datos
	 * @return El indice del registro insertado
	 */
	public int insert_venta(Ventas venta){
		int id_venta = -1;
		String query = "INSERT INTO ventas(fecha,no_articulos,precio_total) VALUES ('"+venta.getFecha()+"',"+venta.getNo_articulos()+","+venta.getTotal_venta()+")";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_venta=ultima_fila();
			
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
		return id_venta;
	}
	
	/**
	 * Obtiene una lista con las ventas en a base de datos
	 * @return La lista de las ventas
	 */
	public ArrayList<Ventas> get_ventas(){
		lista_ventas = new ArrayList<Ventas>();
		Ventas ventas = null;
		String query = "SELECT * FROM ventas";
		
		try {
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				ventas = new Ventas();
				ventas.setId_venta(rs.getInt("id_ventas"));
				ventas.setFecha(rs.getString("fecha"));
				ventas.setNo_articulos(rs.getInt("no_articulos"));
				ventas.setTotal_venta(rs.getDouble("precio_total"));
				lista_ventas.add(ventas);
			}
			
		} catch (SQLException sqle) {
			System.out.println("A ocurrido un error al ejecutar la consulta a la base de datos");
		}finally{
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lista_ventas;
	}
	
	/**
	 * Obtene una venta en la base de datos
	 * @param id_venta El identificador de la venta
	 * @return La venta correspondiente
	 */
	public Ventas find_venta(int id_venta){
		Ventas venta=null;
		String query = "SELECT * FROM ventas WHERE id_venta="+id_venta;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 venta = new Ventas();
				 venta.setId_venta(rs.getInt("id_venta"));
				 venta.setFecha(rs.getString("fecha"));
				 venta.setNo_articulos(rs.getInt("no_articulos"));
				 venta.setTotal_venta(rs.getDouble("precio_total"));
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
		return venta;
	}
	
	/**
	 * Actualiza el numero de articulos y el precio de una venta
	 * @param id_venta El identificador de la venta a modificar
	 * @param articulos El nuevo numero de articulos
	 * @param precio El nuevo total de la venta
	 * @return <b>true</b> si la venta se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_venta(int id_venta, int articulos, double precio){
		String query = "UPDATE ventas SET no_articulos="+articulos+",precio="+precio+" WHERE id_color="+id_venta;
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
	 * @param id_venta El identificador del registro a eliminar
	 * @return <b>true</b> si el registro se elimino exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean delete_venta(int id_venta){
		String query = "DELETE FROM ventas WHERE id_venta="+id_venta;
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
		
		String query = "SELECT max(id_venta) FROM ventas";
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
