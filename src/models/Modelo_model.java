package models;

import instancias.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.MySQLConnection;

/**
 * Clase que se encarga de conectar con la tabla modelo en la base de datos
 * @author Gilberto Aviles (@rinzaku04)
 *
 */
public class Modelo_model {
	
	private ArrayList<Modelo> lista_modelo;
	private ResultSet rs;
	private Connection connection;
	private Statement statement;
	
	/**
	 * Constructor de la clase
	 */
	public Modelo_model(){
		rs= null;
		connection = null;
		statement = null;
	}
	
	/**
	 * Inserta un nuevo modelo en la base de datos
	 * @param modelo El nuevo modelo a insertar
	 * @return El indice del registro insertado
	 */
	public int insert_modelo(Modelo modelo){
		int id_modelo =-1;
		String query = "INSERT INTO modelo(id_ropa,id_color,id_talla,modelo,existencias,estado) VALUES("+modelo.getId_ropa()+","+modelo.getId_color()+","+modelo.getId_talla()+",'"+modelo.getModelo()+"',"+modelo.getExistencias()+",'"+modelo.getEstado()+"')";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			id_modelo=ultima_fila();
			
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
		return id_modelo;
	}
	
	/**
	 * Obtiene todos los registros de la tabla en la base de datos
	 * @return Una lista con todos los modelos
	 */
	public ArrayList<Modelo> get_modelo(){
		lista_modelo = new ArrayList<Modelo>();
		Modelo modelo= null;
		String query = "SELECT * FROM modelo";

		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				modelo = new Modelo();
				modelo.setId_modelo(rs.getInt("id_modelo"));
				modelo.setId_ropa(rs.getInt("id_ropa"));
				modelo.setId_color(rs.getInt("id_color"));
				modelo.setId_talla(rs.getInt("id_talla"));
				modelo.setModelo(rs.getString("modelo"));
				modelo.setExistencias(rs.getInt("existencias"));
				modelo.setEstado(rs.getString("estado"));
				lista_modelo.add(modelo);
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
		return lista_modelo;
	}
	
	/**
	 * Obtiene un modelo de la base de datos
	 * @param id_modelo El identificador del modelo a obtener
	 * @return El modelo correspondiente
	 */
	public Modelo find_modelo(int id_modelo){
		Modelo modelo=null;
		String query = "SELECT * FROM modelo WHERE id_modelo="+id_modelo;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 modelo = new Modelo();
				 modelo.setId_modelo(rs.getInt("id_modelo"));
				 modelo.setId_ropa(rs.getInt("id_ropa"));
				 modelo.setId_color(rs.getInt("id_color"));
				 modelo.setId_talla(rs.getInt("id_talla"));
				 modelo.setModelo(rs.getString("modelo"));
				 modelo.setExistencias(rs.getInt("existencias"));
				 modelo.setEstado(rs.getString("estado"));
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
		return modelo;
	}
	/**
	 * Obtiene el modelo dado la talla y color
	 * @param id_modelo
	 * @return
	 */
	public Modelo find_modelo(String modelo,int id_talla,int id_color){
		Modelo modeloR=null;
		String query = "SELECT * FROM modelo WHERE modelo='"+modelo+"' and id_talla="+id_talla+" and id_color="+id_color+"";
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if(rs.next()){
				 modeloR = new Modelo();
				 modeloR.setId_modelo(rs.getInt("id_modelo"));
				 modeloR.setId_ropa(rs.getInt("id_ropa"));
				 modeloR.setId_color(rs.getInt("id_color"));
				 modeloR.setId_talla(rs.getInt("id_talla"));
				 modeloR.setModelo(rs.getString("modelo"));
				 modeloR.setExistencias(rs.getInt("existencias"));
				 modeloR.setEstado(rs.getString("estado"));
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
		return modeloR;
	}
	
	/**
	 * 
	 */
	
	public ArrayList<Modelo>  find_modelo(String modelo,String talla){
		Modelo modeloR=null;
		lista_modelo=new ArrayList<Modelo>();
		String query = "SELECT * FROM modelo WHERE modelo='"+modelo+"' and id_talla="+talla;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
//			System.out.println(rs.next());
			while(rs.next()){
				 modeloR = new Modelo();
				 modeloR.setId_modelo(rs.getInt("id_modelo"));
				 modeloR.setId_ropa(rs.getInt("id_ropa"));
				 modeloR.setId_color(rs.getInt("id_color"));
				 modeloR.setId_talla(rs.getInt("id_talla"));
				 modeloR.setModelo(rs.getString("modelo"));
				 modeloR.setExistencias(rs.getInt("existencias"));
				 modeloR.setEstado(rs.getString("estado"));
				 lista_modelo.add(modeloR);
				
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
		return lista_modelo;
	}
	
	/**
	 * Obtiene una lista de modelos en la base de datos
	 * @param mod El modelo a buscar
	 * @return La lista de modelos 
	 */
	public ArrayList<Modelo> find_modelo(String mod){
		lista_modelo = new ArrayList<Modelo>();
		Modelo modelo= null;
		String query = "SELECT * FROM modelo WHERE modelo='"+mod+"'";
		
		try {
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()){
				modelo = new Modelo();
				modelo.setId_modelo(rs.getInt("id_modelo"));
				modelo.setId_ropa(rs.getInt("id_ropa"));
				modelo.setId_color(rs.getInt("id_color"));
				modelo.setId_talla(rs.getInt("id_talla"));
				modelo.setModelo(rs.getString("modelo"));
				modelo.setExistencias(rs.getInt("existencias"));
				modelo.setEstado(rs.getString("estado"));
				lista_modelo.add(modelo);
			}
			
		} catch (SQLException sqle) {
		System.out.println(sqle.getMessage());
		System.out.println(sqle.toString());
		}
		return lista_modelo;
	}
	
	/**
	 * Actualiza las existencias de un registro en la base de datos
	 * @param id_ropa El identificador del registro a modificar
	 * @param existencias El numero de nuevas existencias
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_modelo(int id_modelo, int existencias){
		String query = "UPDATE modelo SET existencias="+existencias+" WHERE id_modelo="+id_modelo;
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
	 * Actualiza el modelo de un registro en la base de datos
	 * @param id_modelo El identficador del registro a modificar
	 * @param modelo El nuevo nombre del modelo
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_modelo(int id_modelo, String modelo){
		String query = "UPDATE modelo SET modelo='"+modelo+"' WHERE id_modelo="+id_modelo;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
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
	 * Actualiza el estado de un registro en la base de datos
	 * @param id_ropa El identificador del registro a modificar
	 * @param estado El nuevo estado para el modelo
	 * @return <b>true</b> si el registro se actualizo exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean update_estado(int id_modelo, String estado){
		String query = "UPDATE modelo SET estado="+estado+" WHERE id_modelo="+id_modelo;
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
	 * Elimina un registro de l base de datos
	 * @param id_ropa El identificador del registro a eliminar
	 * @return <b>true</b> si el registro se elimino exitosamente.<br><b>false</b> en cualquier otro caso
	 */
	public boolean delete_modelo(int id_modelo){
		String query = "DELETE FROM modelo WHERE id_modelo="+id_modelo;
		try {
			
			connection = MySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
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
		
		String query = "SELECT max(id_modelo) FROM modelo";
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
