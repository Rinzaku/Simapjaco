package models;
import java.util.ArrayList;
import instancias.*;
import java.sql.*;
import database.*;

/**
 * Clase que se encarga de conectar con la tabla ropa en la base de datos
 * @author Gilberto Aviles (@rinzaku04)
 *
 */
public class Ropa_model {
	
	private ArrayList<Ropa> ropaLista;
	private ResultSet rs;
	private Connection connection;
	private Statement statement; 
	
	/**
	 * Constructor de la clase 
	 */
	public Ropa_model(){
		rs = null;
		connection = null;
		statement = null;
	}
	
	/**
	 * Inserta un nuevo objeto ropa en la base de datos
	 * @param ropa El objeto que se quiere agregar a la base de datos
	 * @return El numero de la nueva fila insertada
	 */
	public int insert_ropa(Ropa ropa){
		int id_ropa=-1;
		String query = "INSERT INTO ropa(nombre_prenda,descripcion, existencias,precio) VALUES ('"+ropa.getPrenda()+"','"+ropa.getDescricion()+"',"+ropa.getExistencias()+","+ropa.getPrecio()+")";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_ropa = ultima_fila();
			
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
		return id_ropa;
	}
	
	/**
	 * Obtiene todos los registros de la base de  datos
	 * @return Una lista con los registros de la be¿ase de datos 
	 */
	public  ArrayList<Ropa> get_ropa(){
		Ropa ropa = null;
		ropaLista  = new ArrayList<Ropa>();
		String query = "SELECT * FROM ropa";
		
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				 ropa = new Ropa();
				 ropa.setId_ropa(rs.getInt("id_ropa"));
				 ropa.setPrenda(rs.getString("nombre_prenda"));
				 ropa.setDescricion(rs.getString("descripcion"));
				 ropa.setExistencias(rs.getInt("existencias"));
				 ropa.setPrecio(rs.getDouble("precio"));
				 ropaLista.add(ropa);
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
		return ropaLista;
	}
	
	/**
	 * Obtiene un objeto ropa de la base de datos 
	 * @param id_ropa El identificador del objeto que se desea obtener
	 * @return El objeto ropa
	 */
	public Ropa find_ropa(int id_ropa){
		Ropa ropa = null;
		String query = "SELECT * FROM ropa WHERE id_ropa="+id_ropa;
		
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 ropa = new Ropa();
				 ropa.setId_ropa(rs.getInt("id_ropa"));
				 ropa.setPrenda(rs.getString("nombre_prenda"));
				 ropa.setDescricion(rs.getString("descripcion"));
				 ropa.setExistencias(rs.getInt("existencias"));
				 ropa.setPrecio(rs.getDouble("precio"));
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
		return ropa;
	}
	
	/**
	 * Actualiza un registro en la base de datos
	 * @param id_ropa El identificador del objeto que se desea actualizar
	 * @param existencias El numero de existencias de ropa
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_ropa(int id_ropa, int existencias){
		String query = "UPDATE ropa SET existencias="+existencias+" WHERE id_ropa="+id_ropa;
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
	 * Actualiza un registro en la base de datos
	 * @param id_ropa El identificador del objeto que se desea actualizar
	 * @param existencias El numero de existencias de ropa
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_ropa(int id_ropa,double precio){
		String query = "UPDATE ropa SET precio="+precio+" WHERE id_ropa="+id_ropa;
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
	 * elimina un registro de la base de datos
	 * @param id_ropa El identificador del objeto a eliminar
	 * @return <b>true</b> si el registro se elimino exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean delete_ropa(int id_ropa){
		String query = "DELETE FROM ropa WHERE id_ropa="+id_ropa;
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
	 * Obtiene la ultima fila en la tabla ropa
	 */
	private int ultima_fila(){
		
		String query = "SELECT max(id_ropa) FROM ropa";
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
}
