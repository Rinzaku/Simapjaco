package models;

import instancias.Ropa;

import java.sql.*;
import java.util.ArrayList;
import instancias.*;
import database.*;

/**
 * 
 * @author Tatzuya Rinzaku
 *
 */
public class Detalle_Venta_model {
	
	private ArrayList<Ropa> detalles_ventas;
	private ResultSet rs;
	private Connection connection;
	private Statement statement; 
	
	/**
	 * 
	 */
	public Detalle_Venta_model(){
		detalles_ventas  = new ArrayList<Ropa>();
		rs = null;
		connection = null;
		statement = null;
	}

	/**
	 * 
	 * @param dv
	 * @return
	 */
	public int insert_detalle_venta(Detalle_Venta dv){
		int id_detalle;
		String query = "INSERT INTO ropa(int_venta,int_ropa,cantidad_articulo,precio_unitario) VALUES ('";
		return 0;
	}
}
