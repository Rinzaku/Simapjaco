package models;
import java.util.ArrayList;
import instancias.*;
import java.sql.*;
import database.*;

public class Ropa_model {
	
	private ArrayList<Ropa> ropaLista;
	private ResultSet rs;
	private Connection connection;
	private Statement statement; 
	
	public Ropa_model(){
		ropaLista  = new ArrayList<Ropa>();
		rs = null;
		connection = null;
		statement = null;
	}
	
	public  ArrayList<Ropa> get_ropa(){
		Ropa ropa = null;
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
				 ropaLista.add(ropa);
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
		return ropaLista;
	}
	
	/**
	 * 
	 * @param id_ropa
	 * @return
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
		return ropa;
	}
	
	public boolean update_ropa(int id_ropa, int existencias){
		String query = "UPDATE ropa SET existencias="+existencias+" WHERE id_ropa="+id_ropa;
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
	
	public boolean delete_ropa(int id_ropa){
		String query = "UPDATE ropa SET existencias="+" WHERE id_ropa="+id_ropa;
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
}
