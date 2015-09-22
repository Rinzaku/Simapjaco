//package controllers;
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import database.*;
//
//public class Main {
//
//	public static void main(String[] args) {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Introduce el id de la ropa");
//		
//		int id_ropa;
//		try {
//			
//			id_ropa = Integer.parseInt(br.readLine());
//			Main demo = new Main();
//			Ropa ropa = demo.getRopa(id_ropa);
//			System.out.println(ropa);
//			
//		} catch (NumberFormatException e) {
//			System.out.println("Por favor introduce un numero");
//		} catch (IOException e) {
//			System.out.println("A ocurrido un error x.x");
//		}
//
//	}
//
//	private Ropa getRopa(int id_ropa) {
//		ResultSet rs = null;
//        Connection connection = null;
//        Statement statement = null; 
//        
//        Ropa ropa = null;
//        String query = "SELECT * FROM ropa WHERE id_ropa="+id_ropa;
//        try {
//        	connection = MySQLConnection.getConnection();
//            statement = connection.createStatement();
//            rs = statement.executeQuery(query);
//            if (rs.next()) {
//                ropa = new Ropa();
//                ropa.setId_ropa(rs.getInt("id_ropa"));
//                ropa.setPrenda(rs.getString("nombre_prenda"));
//                ropa.setDescricion(rs.getString("descripcion"));
//                ropa.setExistencias(rs.getInt("existencias"));
//            }
//		} catch (SQLException e) {
//			System.out.println("A ocurrido un error al ejecutar sentencia SQL");
//		}finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//		return ropa;
//	}
//
//}
