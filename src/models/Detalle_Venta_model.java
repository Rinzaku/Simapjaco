package models;

import java.sql.*;

import instancias.*;
import database.*;

/**
 * Clase que permite conectar con la tabla detalle de venta
 * @author Gilberto Aviles (@rinzaku04)
 *
 */
public class Detalle_Venta_model {
	
//	private ArrayList<Ropa> detalles_ventas;
	private ResultSet rs;
	private Connection connection;
	private Statement statement; 
	
	/**
	 * El constructor de la clase
	 */
	public Detalle_Venta_model(){
//		detalles_ventas  = new ArrayList<Ropa>();
		rs = null;
		connection = null;
		statement = null;
	}

	/**
	 * Inserta un detalle de venta a la base de datos
	 * @param dv El detalle de venta a guardar
	 * @return El indice del registro agregado
	 */
	public int insert_detalle_venta(Detalle_Venta dv){
		int id_detalle=-1;
		String query = "INSERT INTO ropa(id_venta,id_modelo,id_ropa,cantidad_articulo,precio_unitario,estado) VALUES ("+dv.getId_venta()+","+dv.getId_ropa()+","+dv.getId_modelo()+","+dv.getCantidad_articulos()+","+dv.getPrecio_unitario()+",'"+dv.getEstado()+"')";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_detalle=ultima_fila();
			
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
		return id_detalle;
	}
	
	/**
	 * Obtiene un detalle de venta en la base de datos
	 * @param id_venta El identificador de la venta
	 * @return La venta correspondiente
	 */
	public Detalle_Venta find_detalle_venta(int id_venta){
		Detalle_Venta dv = null;
		String query = "SELECT * FROM detalle_venta WHERE id_venta="+id_venta;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				
				dv = new Detalle_Venta();
				dv.setId_detalle_venta(rs.getInt("id_detalle_venta"));
				dv.setId_venta(rs.getInt("id_venta"));
				dv.setId_modelo(rs.getInt("id_modelo"));
				dv.setId_ropa(rs.getInt("id_ropa"));
				dv.setCantidad_articulos(rs.getInt("cantidad articulo"));
				dv.setPrecio_unitario(rs.getInt("precio_unitario"));
				dv.setEstado(rs.getString("estado"));
				
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
		return dv;
	}
	
	/**
	 * Actualiza la cantidad de articulos en una venta
	 * @param id_detalle_venta El identificador del registro a modificar
	 * @param cantidad_articulos La nueva cantidad de articulos
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_detalle_venta(int id_detalle_venta, int cantidad_articulos){
		String query = "UPDATE detalle_venta SET cantidad_articulo="+cantidad_articulos+" WHERE id_detalle_venta="+id_detalle_venta;
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
	 * Actualiza el estado de una venta
	 * @param id_detalle_venta El identificador del registro a modificar
	 * @param estado La nueva cantidad de articulos
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_detalle_venta(int id_detalle_venta, String estado){
		String query = "UPDATE detalle_venta SET estado="+estado+" WHERE id_detalle_venta="+id_detalle_venta;
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
	 * Elimina un registro en la base de datos
	 * @param id_detalle_venta El identificador del registro a eliminar
	 * @return <b>true</b> si el registro se elimino exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean delete_detalle_venta(int id_detalle_venta){
		String query = "DELETE FROM detalle_venta WHERE id_detalle_venta="+id_detalle_venta;
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
		
		String query = "SELECT max(id_detalle_venta) FROM detalle_venta";
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
