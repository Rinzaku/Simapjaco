package database;

/*Interfaces a usar de las librerias SQL de Java*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	/*Referencia estatica de esta clase a si misma*/
	private static MySQLConnection instance =  new MySQLConnection();
	public static final String URL = "jdbc:mysql://localhost/Simapjaco";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    private MySQLConnection(){
    	try {
            /*Cargamos el driver de Java de MySQL*/
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Crea una conexion a la BD para posteriormente ser usada por alguna otra clse en el proyecto
     */
    private Connection createConnection() {
    	 
        Connection connection = null;
        try {
            /*Establecemos la conexion Java MySQL*/
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Incapas de conectar con la BD.");
        }
        return connection;
    }   
     
    /**
     * Metodo que me permite crear una conexion con la base de datos
     * @return Una conexion con la BD
     */
    public static Connection getConnection() {
        return instance.createConnection();
    }
}
